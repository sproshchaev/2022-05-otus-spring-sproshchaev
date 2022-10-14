package ru.otus.spring17books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Приложение "Библиотека" c Spring Boot Actuator, Spring AOP на Spring Security, Spring Web, Thymeleaf, Spring Data JPA,
 * PostgreSQL, Flyway
 * (authentication and authorization)
 *
 * @author Sergey Proshchaev
 * @version 1.0
 */
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
