package com.divijish.StandaloneElasticSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.divij.elasticsearch")
@SpringBootApplication
public class StandaloneElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(StandaloneElasticSearchApplication.class, args);
	}
}
