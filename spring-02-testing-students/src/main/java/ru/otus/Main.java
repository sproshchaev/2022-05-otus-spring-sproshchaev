package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.service2.ProcessService;
import ru.otus.service2.ProcessServiceImpl;

/**
 * Приложение "Тестирование студентов"
 *
 * @author Sergey Proshchaev
 * @version 1.0
 */
@ComponentScan(basePackages = "ru.otus")
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ProcessService process = context.getBean(ProcessService.class);
        process.runProcess();
    }
}
