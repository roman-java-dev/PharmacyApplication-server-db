package com.example.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.pharmacy")
public class PharmacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyApplication.class, args);
	}
}
