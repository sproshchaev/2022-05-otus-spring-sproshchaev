package ru.otus.spring14books.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring14books.nosql.domain.Author;
import ru.otus.spring14books.nosql.domain.Book;
import ru.otus.spring14books.nosql.domain.Comment;
import ru.otus.spring14books.nosql.domain.Genre;
import ru.otus.spring14books.services.*;

/**
 * Класс BatchConfig содержит конфигурацию Spring Batch
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    /**
     * Метод libraryMigration() запускает процесс миграции вссех сущностей библиотеки
     *
     * @return
     */
    @Bean
    public Job libraryMigration(Step authorsMigration, Step genresMigration, Step booksMigration,
                                Step commentsMigration) {
        return jobBuilderFactory.get("libraryMigration")
                .start(authorsMigration)
                .next(genresMigration)
                .next(booksMigration)
                .next(commentsMigration)
                .build();
    }

    /**
     * Метод authorsMigration() выполняет миграцию авторов
     *
     * @return
     */
    @Bean
    public Step authorsMigration(AuthorReader authorReader,  AuthorProcessor authorProcessor,
                                 MongoItemWriter<Author> writerAuthorMongo) {
        return stepBuilderFactory.get("authorsMigration")
                .<ru.otus.spring14books.sql.domain.Author, ru.otus.spring14books.sql.domain.Author>chunk(10)
                .reader(authorReader)
                .processor(authorProcessor)
                .writer(writerAuthorMongo)
                .build();
    }

    /**
     * Метод genresMigration() выполняет миграцию жанров
     *
     * @return
     */
    @Bean
    public Step genresMigration(GenreReader genreReader, GenreProcessor genreProcessor,
                                MongoItemWriter<Genre> genreWriterMongo) {
        return stepBuilderFactory.get("genresMigration")
                .<ru.otus.spring14books.sql.domain.Author, ru.otus.spring14books.sql.domain.Author>chunk(10)
                .reader(genreReader)
                .processor(genreProcessor)
                .writer(genreWriterMongo)
                .build();
    }

    /**
     * Метод booksMigration() выполняет миграцию книг
     *
     * @return
     */
    @Bean
    public Step booksMigration(BookReader bookReader, BookProcessor bookProcessor,
                               MongoItemWriter<Book> bookWriterMongo) {
        return stepBuilderFactory.get("booksMigration")
                .<ru.otus.spring14books.sql.domain.Author, ru.otus.spring14books.sql.domain.Author>chunk(10)
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(bookWriterMongo)
                .build();
    }

    /**
     * Метод commentsMigration() выполняет миграцию книг
     *
     * @return
     */
    @Bean
    public Step commentsMigration(CommentReader commentReader, CommentProcessor commentProcessor,
                                  MongoItemWriter<Comment> commentWriterMongo) {
        return stepBuilderFactory.get("commentsMigration")
                .<ru.otus.spring14books.sql.domain.Author, ru.otus.spring14books.sql.domain.Author>chunk(10)
                .reader(commentReader)
                .processor(commentProcessor)
                .writer(commentWriterMongo)
                .build();
    }

    /**
     * Метод authorWriterMongo() использует класс MongoItemWriter
     * @param mongoTemplate
     * @return
     */
    @Bean
    public MongoItemWriter<Author> authorWriterMongo(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Author>().template(mongoTemplate).collection("author")
                .build();
    }

    /**
     * Метод genreWriterMongo() использует класс MongoItemWriter
     * @param mongoTemplate
     * @return
     */
    @Bean
    public MongoItemWriter<Genre> genreWriterMongo(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Genre>().template(mongoTemplate).collection("genre")
                .build();
    }

    /**
     * Метод bookWriterMongo() использует класс MongoItemWriter
     * @param mongoTemplate
     * @return
     */
    @Bean
    public MongoItemWriter<Book> bookWriterMongo(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Book>().template(mongoTemplate).collection("book")
                .build();
    }

    /**
     * Метод commentWriterMongo() использует класс MongoItemWriter
     * @param mongoTemplate
     * @return
     */
    @Bean
    public MongoItemWriter<Comment> commentWriterMongo(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Comment>().template(mongoTemplate).collection("comment")
                .build();
    }
}
