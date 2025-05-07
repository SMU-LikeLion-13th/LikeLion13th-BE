package com.project.likelion13thbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LikeLion13thBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LikeLion13thBeApplication.class, args);
    }

}
