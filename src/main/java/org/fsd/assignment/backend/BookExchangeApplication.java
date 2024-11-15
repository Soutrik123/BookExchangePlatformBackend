package org.fsd.assignment.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.fsd.assignment.backend")
public class BookExchangeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookExchangeApplication.class, args);
    }
}
