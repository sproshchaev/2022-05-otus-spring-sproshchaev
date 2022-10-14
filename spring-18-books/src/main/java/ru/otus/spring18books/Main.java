package ru.otus.spring18books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * Приложение "Библиотека" c Spring Boot Actuator, Spring AOP на Spring Security, Spring Web, Thymeleaf, Spring Data JPA,
 * PostgreSQL, Flyway
 * (authentication and authorization)
 *
 * @author Sergey Proshchaev
 * @version 1.0
 */
@EnableCircuitBreaker
// @EnableHystrixDashboard pom.xml?
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
