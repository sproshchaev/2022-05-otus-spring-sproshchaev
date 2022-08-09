package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.service.TestsForStudents;

/**
 * Приложение по проведению тестирования студентов - вывод вопросов и получение вариантов ответа
 * В ресурсах хранятся вопросы и различные ответы к ним в виде CSV файла (5 вопросов)
 * В application.properties содержаться:
 * - имя CSV файла (fileCsvName)
 * - число успешных ответов, необходимых для прохождения теста (numberCorrectAnswers)
 */
@ComponentScan(basePackages = "ru.otus")
@PropertySource("classpath:application.properties")
public class Main {

    /**
     * "Точка входа в приложение"
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestsForStudents testsForStudents = context.getBean(TestsForStudents.class);
        testsForStudents.runTest();
    }

}
