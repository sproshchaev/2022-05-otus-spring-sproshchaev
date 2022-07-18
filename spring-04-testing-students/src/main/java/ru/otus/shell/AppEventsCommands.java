package ru.otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.TestsForStudents;
import ru.otus.service.TextToConsole;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 *
 */
@ShellComponent
public class AppEventsCommands {

    @Autowired
    private ApplicationContext context;

    /**
     * Метод runTest получает в качестве параметра (через аннотацию @ShellOption) имя студента и запускает
     * процесс тестирования
     * Строка "r --name Имя_студента"
     * Spring Shell зависимость подтягивается milestones (сформирована через https://start.spring.io/), а
     * в нем была зафиксирована ошибка при которой параметры в строке Spring Shell корректно принимаются
     * только если задается "--параметр"
     * (см. https://github.com/spring-projects/spring-shell/commit/5ba8e185bca0390d702b52d715d5561d98645ac2)
     * @return
     */
    @ShellMethod(value = "Run tests", key = {"r", "run"})
    public String runTest(@ShellOption(defaultValue = "AnyUser") String name) {

        TestsForStudents testsForStudents = context.getBean(TestsForStudents.class);
        testsForStudents.runTest();

        return String.format("Good luck %s", name);
    }

}
