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


import java.util.ArrayList;
import java.util.List;

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
     * Через инструкцию:
     *         bookForUpdate.setId(1);
     *         entityManager.merge(bookForUpdate);
     * изменения сохраняются и через метод bookRepositoryJpa.getBookById(1) сравниваются две сущности в assertThat
     */
    @DisplayName("update a new book")
    @Test
    void updateBookById() {
        Book bookForUpdate = new Book("New_title", new Author("New_Author"), new Genre("New_Genre"));
        bookRepositoryJpa.updateBookById(1, bookForUpdate);
        bookForUpdate.setId(1);
        entityManager.merge(bookForUpdate);
        Book actualBook = bookRepositoryJpa.getBookById(1);
        assertThat(actualBook.getTitle() + " " + actualBook.getAuthor().getFullName() + " " + actualBook.getGenre().getName())
                .isEqualTo(bookForUpdate.getTitle() + " " + bookForUpdate.getAuthor().getFullName() + " " + bookForUpdate.getGenre().getName());
    }

    /**
     * Метод shouldDeleteBookById тестирует метод deleteBookById
     */
    @DisplayName("deletes the book")
    @Test
    void shouldDeleteBookById() {
        Book bookForDelete = new Book("New_title", new Author("New_Author"), new Genre("New_Genre"));
        long bookForDeleteId = bookRepositoryJpa.createBook(bookForDelete);
        boolean result = bookRepositoryJpa.deleteBookById(bookForDeleteId);
        assertThat(result).isEqualTo(true);
    }

    /**
     * Метод shouldGetBookById выполняет тестирование метода getBookById
     * При создании БД под id=1 'The Pilgrim’s Progress' 'John Bunyan' 'History'
     * Инструкция entityManager.merge(expectedBook); обновляет сущность
     */
    @DisplayName("get the author by id")
    @Test
    void shouldGetBookById() {
        Book expectedBook = new Book(1, "The Pilgrim’s Progress",
                new Author(1, "John Bunyan"),
                new Genre(1, "History"));
        entityManager.merge(expectedBook);
        Book actualBook = bookRepositoryJpa.getBookById(1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    /**
     * Метод shouldGetIdByBook тестирует getIdByBook
     */
    @DisplayName("get the book's id")
    @Test
    void getIdByBook() {
        Book expectedBook = new Book(1, "The Pilgrim’s Progress",
                new Author(1, "John Bunyan"),
                new Genre(1, "History"));
        long actualBookId = bookRepositoryJpa.getIdByBook(expectedBook);
        assertThat(actualBookId).isEqualTo(1);
    }

    /**
     * Метод shouldGetAllBooks тестирует getAllBooks
     */
    @DisplayName("get a list of books")
    @Test
    void getAllBooks() {
        List<Book> expectedBooksList = new ArrayList();
        expectedBooksList.add(new Book(1, "The Pilgrim’s Progress", new Author(1, "John Bunyan"), new Genre(1, "History")));
        expectedBooksList.add(new Book(2, "Robinson Crusoe", new Author(2, "Daniel Defoe"), new Genre(2, "Classic")));
        expectedBooksList.add(new Book(3, "The Holy War", new Author(1, "John Bunyan"), new Genre(1, "History")));
        expectedBooksList.add(new Book(4, "The Farther Adventures of Robinson Crusoe", new Author(2, "Daniel Defoe"), new Genre(2, "Classic")));
        List<Book> actualBooksList = bookRepositoryJpa.getAllBooks();
        assertThat(actualBooksList.size()).isEqualTo(expectedBooksList.size());
    }

    /**
     * Метод shouldGetCountOfBooks тестирует getCountOfBooks
     * В константе EXPECTED_BOOKS_COUNT записано число книг в библиотеке с которым происходит сравнение
     */
    @DisplayName("get a count of books")
    @Test
    void getCountOfBooks() {
        long actualBooksCount = bookRepositoryJpa.getCountOfBooks();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }
}