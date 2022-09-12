package ru.otus.spring11books.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.otus.spring11books.domain.Author;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void shouldGetAllAuthors() {
        Flux<Author> authors = authorRepository.findAll();
        StepVerifier
                .create(authors)
                .assertNext(author -> assertNotNull(author.getFullName()))
                .assertNext(author -> assertNotNull(author.getFullName()))
                .assertNext(author -> assertNotNull(author.getFullName()))
                .expectComplete()
                .verify();
    }
}