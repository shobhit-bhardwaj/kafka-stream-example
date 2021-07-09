package com.shobhit;

import java.util.function.Consumer;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceConfiguration {

	@Bean
	public Consumer<KStream<String, Domain>> domainService() {
		return kStream -> kStream.foreach((key, domain) -> {
			System.out.println("Domain Consumed - " + domain.getDomain() + ", Status(isDead) - " + domain.isDead());
		});
	}
}