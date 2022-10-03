package ru.otus.spring15cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

/**
 * Миграция данных приложения "Кафе" с использованием Spring Integration, Spring Shell
 * и Spring Batch
 *
 * @author Sergey Proshchaev
 * @version 1.0
 */
@EnableIntegration
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
