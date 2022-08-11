package ru.otus.spring07books.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring07books.entities.Author;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс AuthorRepositoryTest тестирует не стандартные методы интерфейса AuthorRepository
 * Аннотация replace = AutoConfigureTestDatabase.Replace.NONE определяет тестирование на базе приложения
 * Аннотация @Transactional(propagation = Propagation.NOT_SUPPORTED) не используется
 */
@DisplayName("Repository for working with authors ")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("must get the author's id by full name")
    @Test
    void shouldGetAuthorIdByFullName() {
        String authorFullNameForTest = "New_author_for_test";
        Author expectedAuthor = entityManager.persist(new Author(authorFullNameForTest));
        List<Author> authorList = authorRepository.getAuthorByFullName(authorFullNameForTest);
        assertThat(authorList.get(0).getId()).usingRecursiveComparison().isEqualTo(expectedAuthor.getId());
    }

    @DisplayName("must change the full name of the author by his id")
    @Test
    void shouldUpdateAuthor() {
        long idAuthor = 1;
        String authorFullNameForTest = "New_author_for_test";
        authorRepository.updateAuthor(idAuthor, authorFullNameForTest);
        Author updatedAuthor = entityManager.find(Author.class, idAuthor);
        assertThat(updatedAuthor.getFullName()).isEqualTo(authorFullNameForTest);
    }
}