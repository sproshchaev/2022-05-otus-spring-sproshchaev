package ru.otus.spring05books.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring05books.domain.Author;
import ru.otus.spring05books.domain.Book;
import ru.otus.spring05books.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс BookDaoJdbcTest тестирует методы класса BookDaoJdbc
 * тесты из JUnit5 (org.junit.jupiter)
 */
@DisplayName("To work with the list of books, the DAO must")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {

    /**
     * Внедрение зависимости тестируемого класса
     */
    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    /**
     * Поле ожидаемое количества книг в базе
     */
    private static final int EXPECTED_BOOKS_COUNT = 4;

    /**
     * Метод shouldCreateNewBook тестирует метод createBook
     * В созданной БД на начальном этапе EXPECTED_BOOKS_COUNT автора.
     * Метод создает нового автора и через getBookById() получает его по id
     */
    @DisplayName("create a new book")
    @Test
    void shouldCreateNewBook() {
        Book expectedBook = new Book("New_title", new Author("New_Author"), new Genre("New_Genre"));
        bookDaoJdbc.createBook(expectedBook);
        long idExpectedBook = bookDaoJdbc.getIdByBook(expectedBook);
        Book actualBook = bookDaoJdbc.getBookById(idExpectedBook);
        assertThat(actualBook.getTitle() + " " + actualBook.getAuthor().getFullName() + " " + actualBook.getGenre().getName())
                .isEqualTo(expectedBook.getTitle() + " " + expectedBook.getAuthor().getFullName() + " " + expectedBook.getGenre().getName());
    }

    /**
     * Метод shouldUpdateBookById тестирует updateBookById
     */
    @DisplayName("update a new book")
    @Test
    void shouldUpdateBookById() {
        Book bookForUpdate = new Book("New_title", new Author("New_Author"), new Genre("New_Genre"));
        bookDaoJdbc.updateBookById(1, bookForUpdate);
        Book actualBook = bookDaoJdbc.getBookById(1);
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
        long bookForDeleteId = bookDaoJdbc.createBook(bookForDelete);
        boolean result = bookDaoJdbc.deleteBookById(bookForDeleteId);
        assertThat(result).isEqualTo(true);
    }

    /**
     * Метод shouldGetBookById выполняет тестирование метода getBookById
     * При создании БД под id=1 'The Pilgrim’s Progress' 'John Bunyan' 'History'
     */
    @DisplayName("get the author by id")
    @Test
    void shouldGetBookById() {
        Book expectedBook = new Book(1, "The Pilgrim’s Progress",
                new Author(1, "John Bunyan"),
                new Genre(1, "History"));
        Book actualBook = bookDaoJdbc.getBookById(1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    /**
     * Метод shouldGetIdByBook тестирует getIdByBook
     */
    @DisplayName("get the book's id")
    @Test
    void shouldGetIdByBook() {
        Book expectedBook = new Book(1, "The Pilgrim’s Progress",
                new Author(1, "John Bunyan"),
                new Genre(1, "History"));
        long actualBookId = bookDaoJdbc.getIdByBook(expectedBook);
        assertThat(actualBookId).isEqualTo(1);
    }

    /**
     * Метод shouldGetAllBooks тестирует getAllBooks
     */
    @DisplayName("get a list of books")
    @Test
    void shouldGetAllBooks() {
        List<Book> expectedBooksList = new ArrayList();
        expectedBooksList.add(new Book(1, "The Pilgrim’s Progress", new Author(1, "John Bunyan"), new Genre(1, "History")));
        expectedBooksList.add(new Book(2, "Robinson Crusoe", new Author(2, "Daniel Defoe"), new Genre(2, "Classic")));
        expectedBooksList.add(new Book(3, "The Holy War", new Author(1, "John Bunyan"), new Genre(1, "History")));
        expectedBooksList.add(new Book(4, "The Farther Adventures of Robinson Crusoe", new Author(2, "Daniel Defoe"), new Genre(2, "Classic")));
        List<Book> actualBooksList = bookDaoJdbc.getAllBooks();
        assertThat(actualBooksList.toString()).isEqualTo(expectedBooksList.toString());
    }

    /**
     * Метод shouldGetCountOfBooks тестирует getCountOfBooks
     */
    @Test
    void shouldGetCountOfBooks() {
        long actualBooksCount = bookDaoJdbc.getCountOfBooks();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }
}