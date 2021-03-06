package com.tw.csc.nge.backend.basicbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BasicBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicBackendApplication.class, args);
    }

}
