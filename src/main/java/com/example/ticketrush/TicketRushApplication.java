package com.example.ticketrush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TicketRushApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketRushApplication.class, args);
    }

}
