package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.service.TestsForStudents;

/**
 * Приложение по проведению тестирования студентов - вывод вопросов и получение вариантов ответа
 * В ресурсах хранятся вопросы и различные ответы к ним в виде CSV файла (5 вопросов)
 * В application.properties содержаться:
 * - имя CSV файла (fileCsvName)
 * - число успешных ответов, необходимых для прохождения теста (numberCorrectAnswers)
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        TestsForStudents testsForStudents = context.getBean(TestsForStudents.class);
        testsForStudents.runTest();
    }

}
