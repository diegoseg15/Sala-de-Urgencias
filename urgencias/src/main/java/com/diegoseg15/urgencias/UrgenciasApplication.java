package com.diegoseg15.urgencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// @EnableJpaRepositories(basePackages = "com.diegoseg15.urgencias.repositories")
public class UrgenciasApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrgenciasApplication.class, args);
    }
}