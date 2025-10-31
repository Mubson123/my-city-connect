package com.mc.extend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ExtendResidencePermitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtendResidencePermitServiceApplication.class, args);
	}

}
