package com.mc.extend;

import org.springframework.boot.SpringApplication;

public class TestExtendResidencePermitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ExtendResidencePermitServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
