package ru.otus.spring14books;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Миграция данных приложения "Библиотека" из реляционной СУБД MS SQL Server в NoSQL СУБД MongoDB
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
