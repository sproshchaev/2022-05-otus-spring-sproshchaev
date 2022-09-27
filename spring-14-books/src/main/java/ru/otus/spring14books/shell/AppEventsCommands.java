package ru.otus.spring14books.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Класс ApplicationEventsCommands содержит набор методов-команд (аннотация @ShellComponent)
 */
@ShellComponent
public class AppEventsCommands {

/*
    private final JobLauncher jobLauncher;

    private final Job job;

    @Autowired
    public AppEventsCommands(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }
*/

    /**
     * Метод about выводит информацию о приложении
     * Сокращенный вызов: "a", "about"
     *
     * @return
     */
    @ShellMethod(value = "Information about the application", key = {"a", "about"})
    public String aboutApplication() {
        return "Welcome to Spring Batch Application!";
    }

/*
    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(job, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
    }
*/

}
