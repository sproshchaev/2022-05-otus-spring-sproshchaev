package ru.otus.spring14books.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring14books.services.*;
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

    private final GenreReader genreReader;

    private final GenreProcessor genreProcessor;

    private final GenreWriter genreWriter;

    private final BookReader bookReader;

    private final BookProcessor bookProcessor;

    private final BookWriter bookWriter;


    @Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                       AuthorReader authorReader, AuthorProcessor authorProcessor, AuthorWriter authorWriter,
                       GenreReader genreReader, GenreProcessor genreProcessor, GenreWriter genreWriter, BookReader bookReader, BookProcessor bookProcessor, BookWriter bookWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.authorReader = authorReader;
        this.authorProcessor = authorProcessor;
        this.authorWriter = authorWriter;
        this.genreReader = genreReader;
        this.genreProcessor = genreProcessor;
        this.genreWriter = genreWriter;
        this.bookReader = bookReader;
        this.bookProcessor = bookProcessor;
        this.bookWriter = bookWriter;
    }

    /**
     * Метод libraryMigration() запускает процесс миграции вссех сущностей библиотеки
     * @return
     */
    @Bean
    public Job libraryMigration() {
        return jobBuilderFactory.get("libraryMigration")
                .start(authorsMigration())
                .next(genresMigration())
                .next(booksMigration())
                .build();
    }

    /**
     * Метод authorsMigration() выполняет миграцию авторов
     * @return
     */
    @Bean
    public Step authorsMigration() {
        return stepBuilderFactory.get("authorsMigration")
                .<Author, Author>chunk(10)
                .reader(authorReader)
                .processor(authorProcessor)
                .writer(authorWriter)
                .build();
    }

    /**
     * Метод genresMigration() выполняет миграцию жанров
     * @return
     */
    @Bean
    public Step genresMigration() {
        return stepBuilderFactory.get("genresMigration")
                .<Author, Author>chunk(10)
                .reader(genreReader)
                .processor(genreProcessor)
                .writer(genreWriter)
                .build();
    }

    /**
     * Метод booksMigration() выполняет миграцию книг
     * @return
     */
    @Bean
    public Step booksMigration() {
        return stepBuilderFactory.get("booksMigration")
                .<Author, Author>chunk(10)
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(bookWriter)
                .build();
    }

}
