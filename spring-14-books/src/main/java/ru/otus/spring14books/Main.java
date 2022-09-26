package ru.otus.spring14books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Миграция данных приложения "Библиотека" из реляционной СУБД MS SQL Server в NoSQL СУБД MongoDB
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
