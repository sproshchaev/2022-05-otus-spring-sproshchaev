package ru.otus.spring14books;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Миграция данных приложения "Библиотека" из реляционной СУБД H2 в NoSQL СУБД MongoDB с использованием Spring Shell
 * и Spring Batch
 *
 * @author Sergey Proshchaev
 * @version 1.0
 */
@EnableMongock
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
