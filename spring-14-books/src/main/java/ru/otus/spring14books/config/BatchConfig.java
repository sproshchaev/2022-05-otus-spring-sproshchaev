package ru.otus.spring14books.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring14books.services.AuthorProcessor;
import ru.otus.spring14books.services.AuthorReader;
import ru.otus.spring14books.services.AuthorWriter;
import ru.otus.spring14books.sql.domain.Author;

/**
 * Класс BatchConfig содержит конфигурацию Spring Batch
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final AuthorReader authorReader;

    private final AuthorProcessor authorProcessor;

    private final AuthorWriter authorWriter;

    @Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                       AuthorReader authorReader, AuthorProcessor authorProcessor, AuthorWriter authorWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.authorReader = authorReader;
        this.authorProcessor = authorProcessor;
        this.authorWriter = authorWriter;
    }

    /**
     * Метод libraryMigration() запускает процесс миграции вссех сущностей библиотеки
     * @return
     */
    @Bean
    public Job libraryMigration() {
        return jobBuilderFactory.get("job")
                .start(authorsMigration())
                .build();
    }

    /**
     * Метод authorsMigration() выполняет миграцию авторов
     * @return
     */
    @Bean
    public Step authorsMigration() {
        return stepBuilderFactory.get("stepOne")
                .<Author, Author>chunk(10)
                .reader(authorReader)
                .processor(authorProcessor)
                .writer(authorWriter)
                .build();
    }

}
