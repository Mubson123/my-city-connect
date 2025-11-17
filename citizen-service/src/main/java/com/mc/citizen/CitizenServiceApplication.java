package com.mc.citizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CitizenServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CitizenServiceApplication.class, args);
  }
}
