package ru.otus.spring03testingstudentsnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring03testingstudentsnew.service.ProcessService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Main.class, args);
		context.getBean(ProcessService.class).runProcess();
	}

}
