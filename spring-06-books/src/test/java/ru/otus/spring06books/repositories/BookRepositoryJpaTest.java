package ru.otus.spring06books.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring06books.entities.Author;
import ru.otus.spring06books.entities.Book;
import ru.otus.spring06books.entities.Genre;
import ru.otus.spring06books.entities.Comment;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс BookRepositoryJpaTest выполняет тестирование методов класса BookRepositoryJpa
 * Аннотация @DataJpaTest кусок контекста слоя persist, TestEntityManager, транзакцию в начале каждого теста
 * тесты из JUnit5 (org.junit.jupiter)
 */
@DisplayName("Repository for working with books ")
@DataJpaTest
@Import({AuthorRepositoryJpa.class, GenreRepositoryJpa.class, CommentRepositoryJpa.class, BookRepositoryJpa.class})
class BookRepositoryJpaTest {
    /**
     * Внедрение зависимости тестируемого класса
     */
    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private TestEntityManager entityManager;

    /**
     * Поле ожидаемое количества книг в базе
     */
    private static final int EXPECTED_BOOKS_COUNT = 4;

    /**
     * Метод shouldCreateNewBook тестирует метод createBook
     * В созданной БД на начальном этапе EXPECTED_BOOKS_COUNT книг.
     * Метод создает нового автора и через getBookById() получает его по id
     */
    @DisplayName("create a new book")
    @Test
    void createBook() {
        Book expectedBook = new Book("New_title", new Author("New_Author"), new Genre("New_Genre"));
        bookRepositoryJpa.createBook(expectedBook);
        long idExpectedBook = bookRepositoryJpa.getIdByBook(expectedBook);
        Book actualBook = bookRepositoryJpa.getBookById(idExpectedBook);
        assertThat(actualBook.getTitle() + " " + actualBook.getAuthor().getFullName() + " " + actualBook.getGenre().getName())
                .isEqualTo(expectedBook.getTitle() + " " + expectedBook.getAuthor().getFullName() + " " + expectedBook.getGenre().getName());
    }

    /**
     * Метод shouldUpdateBookById тестирует updateBookById
     */
    @DisplayName("update a new book")
    @Test
    void updateBookById() {
        Book bookForUpdate = new Book("New_title", new Author("New_Author"), new Genre("New_Genre"));
        bookRepositoryJpa.updateBookById(1, bookForUpdate);
        Book actualBook = bookRepositoryJpa.getBookById(1);
        assertThat(actualBook.getTitle() + " " + actualBook.getAuthor().getFullName() + " " + actualBook.getGenre().getName())
                .isEqualTo(bookForUpdate.getTitle() + " " + bookForUpdate.getAuthor().getFullName() + " " + bookForUpdate.getGenre().getName());
    }

    @Test
    void deleteBookById() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void getIdByBook() {
    }

    @Test
    void getAllBooks() {
    }

    @Test
    void getCountOfBooks() {
    }
}