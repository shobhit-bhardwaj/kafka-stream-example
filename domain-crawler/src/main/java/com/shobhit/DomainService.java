package com.shobhit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class DomainService {

	@Value("${kafka.topic.name}")
	private String topicName;

	@Autowired
	KafkaTemplate<String, Domain> kafkaTemplate;

	public void fetchDomain(String domainName) {
		Mono<DomainList> domainListMono = WebClient.create()
			.get()
			.uri("https://api.domainsdb.info/v1/domains/search?domain=" + domainName + "&zone=com")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(DomainList.class);

		domainListMono.subscribe(domainList -> {
			domainList.getDomains().forEach(domain -> {
				kafkaTemplate.send(topicName, domain);
				System.out.println("Publish Domain - " + domain.getDomain() + " to Kafka Topic.");
			});
		});
	}
}