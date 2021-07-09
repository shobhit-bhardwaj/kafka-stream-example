package com.shobhit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain")
public class DomainController {

	@Autowired
	private DomainService domainService;

	@GetMapping("/fetch/{domainName}")
	public String fetchDomain(@PathVariable final String domainName) {
		domainService.fetchDomain(domainName);

		return "Domain Crawler has Scrapped the data for Domain - " + domainName;
	}
}