package ru.otus.spring07books.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void getAuthorIdByFullName() {
        // Вычитать через энтити менеджер
        // Сравнить с тем, что возвращает
    }

    @Test
    void updateAuthor() {
    }
}