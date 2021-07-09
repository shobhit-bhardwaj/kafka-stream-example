package com.shobhit;

import java.util.function.Function;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainProcessorConfiguration {

	@Bean
	public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {
		return kStream -> kStream.filter((key, domain) -> {
			if(domain.isDead())
				System.out.println("Inactive Domain - " + domain.getDomain());
			else
				System.out.println("Active Domain - " + domain.getDomain());

			return !domain.isDead();
		});
	}
}