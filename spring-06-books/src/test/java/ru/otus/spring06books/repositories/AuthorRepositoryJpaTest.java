package ru.otus.spring06books.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring06books.entities.Author;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс AuthorRepositoryJpaTest выполняет тестирование методов класса AuthorRepositoryJpa
 * Аннотация @DataJpaTest создает кусок контекста слоя persist, TestEntityManager, транзакцию в начале каждого теста
 * тесты из JUnit5 (org.junit.jupiter)
 */
@DisplayName("Repository for working with authors ")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    /**
     * Внедрение зависимости тестируемого класса
     */
    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    private TestEntityManager entityManager;

    /**
     * Поле ожидаемое количество авторов в базе
     */
    private static final int EXPECTED_AUTHORS_COUNT = 3;

    /**
     * Метод shouldCreateNewAuthor тестирует метод createAuthor
     * В созданной БД на начальном этапе EXPECTED_AUTHORS_COUNT автора. Метод создает нового автора и через getAuthorById() получает его по id
     */
    @DisplayName("must create an author")
    @Test
    void shouldCreateAuthor() {
        Author expectedAuthor = new Author("New_author");
        long idExpectedAuthor = authorRepositoryJpa.createAuthor(expectedAuthor).getId();
        Author actualAuthor = entityManager.find(Author.class, idExpectedAuthor);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    /**
     * Метод shouldGetAuthorById выполняет тестирование метода getAuthorById
     * При создании БД под id=1 "John Bunyan"
     */
    @DisplayName("get the author by id")
    @Test
    void shouldGetAuthorById() {
        Author expectedAuthor = new Author(1, "John Bunyan");
        Author actualAuthor = authorRepositoryJpa.getAuthorById(1);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    /**
     * Метод shouldGetIdByAuthor тестирует getIdByAuthor
     */
    @DisplayName("get the author's id")
    @Test
    void getIdByAuthor() {
        Author authorForGet = new Author(1, "John Bunyan");
        long authorId = authorRepositoryJpa.getIdByAuthor(authorForGet);
        assertThat(authorId).usingRecursiveComparison().isEqualTo(authorForGet.getId());
    }

    /**
     * Метод shouldUpdateAuthor тестирует updateAuthor
     */
    @DisplayName("update a new author")
    @Test
    void shouldUpdateAuthor() {
        Author authorForUpdate = new Author(1, "New_author");
        authorRepositoryJpa.updateAuthor(authorForUpdate);
        assertThat(authorRepositoryJpa.getAuthorById(1)).usingRecursiveComparison().isEqualTo(authorForUpdate);
    }

    /**
     * Метод shouldDeleteAuthor тестирует метод deleteAuthor
     */
    @DisplayName("deletes the author")
    @Test
    void shouldDeleteAuthor() {
        long newAuthorId = authorRepositoryJpa.createAuthor(new Author("New_Author")).getId();
        Author authorForDelete = new Author(newAuthorId, "New_Author");
        boolean result = authorRepositoryJpa.deleteAuthor(authorForDelete);
        assertThat(result).isEqualTo(true);
    }

    /**
     * Метод shouldGetAllAuthors тестирует getAllAuthors
     */
    @DisplayName("get a list of authors")
    @Test
    void shouldGetAllAuthors() {
        List<Author> expectedAuthorsList = new ArrayList();
        expectedAuthorsList.add(new Author(1, "John Bunyan"));
        expectedAuthorsList.add(new Author(2, "Daniel Defoe"));
        expectedAuthorsList.add(new Author(3, "Gianni Rodari"));
        List<Author> actualAuthorsList = authorRepositoryJpa.getAllAuthors();
        assertThat(actualAuthorsList.size()).isEqualTo(expectedAuthorsList.size());
    }

    /**
     * Метод shouldGetCountOfAuthors тестирует getCountOfAuthors
     * В константе EXPECTED_AUTHORS_COUNT записано число авторов в библиотеке с которым происходит сравнение
     */
    @DisplayName("return the expected number of authors from the library")
    @Test
    void getCountOfAuthors() {
        long actualAuthorsCount = authorRepositoryJpa.getCountOfAuthors();
        assertThat(actualAuthorsCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }
}