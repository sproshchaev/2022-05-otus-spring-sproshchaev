package ru.otus.spring07books.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring07books.entities.Author;
import ru.otus.spring07books.entities.Book;
import ru.otus.spring07books.entities.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс BookRepositoryTest тестирует не стандартные методы интерфейса BookRepository
 * Аннотация replace = AutoConfigureTestDatabase.Replace.NONE определяет тестирование на базе приложения
 * Аннотация @Transactional(propagation = Propagation.NOT_SUPPORTED) не используется
 */
@DisplayName("Repository for working with books ")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookRepository bookRepository;

    @DisplayName("must get the id of the book according to its data")
    @Test
    void shouldGetIdByBook() {
        String titleBookForTest = "New_title";
        String authorFullNameForTest = "New_author_for_test";
        String genreNameForTest = "New_genre_for_test";
        Book expectedBook = entityManager.persist(new Book(titleBookForTest, new Author(authorFullNameForTest), new Genre(genreNameForTest)));
        List<Long> idBookList = bookRepository.getIdByBook(titleBookForTest, authorFullNameForTest, genreNameForTest);
        assertThat(idBookList.get(0)).usingRecursiveComparison().isEqualTo(expectedBook.getId());
    }

    @DisplayName("must edit book data")
    @Test
    void shouldUpdateBook() {
        long idBook = 1;
        String titleBookForTest = "New_title";
        String authorFullNameForTest = "New_author_for_test";
        String genreNameForTest = "New_genre_for_test";
        Author expectedAuthor = entityManager.persist(new Author(authorFullNameForTest));
        Genre expectedGenre = entityManager.persist(new Genre(genreNameForTest));
        bookRepository.updateBook(idBook, titleBookForTest, expectedAuthor, expectedGenre);
        Book updatedBook = entityManager.find(Book.class, idBook);
        assertThat(updatedBook.getTitle()).isEqualTo(titleBookForTest);
        assertThat(updatedBook.getAuthor().getFullName()).isEqualTo(authorFullNameForTest);
        assertThat(updatedBook.getGenre().getName()).isEqualTo(genreNameForTest);
    }

}