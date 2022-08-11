package ru.otus.spring07books.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring07books.entities.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс GenreRepositoryTest тестирует не стандартные методы интерфейса GenreRepository
 * Аннотация replace = AutoConfigureTestDatabase.Replace.NONE определяет тестирование на базе приложения
 * Аннотация @Transactional(propagation = Propagation.NOT_SUPPORTED) не используется
 */
@DisplayName("Repository for working with genres ")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GenreRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("must get the genre id by name")
    @Test
    void shouldGetGenreIdByName() {
        String genreNameForTest = "New_genre_for_test";
        Genre expectedGenre = entityManager.persist(new Genre(genreNameForTest));
        List<Genre> genreList = genreRepository.getGenreByName(genreNameForTest);
        assertThat(genreList.get(0).getId()).usingRecursiveComparison().isEqualTo(expectedGenre.getId());
    }

    @DisplayName("must update the genre name")
    @Test
    void shouldUpdateGenre() {
        long idGenre = 1;
        String genreNameForTest = "New_genre_for_test";
        genreRepository.updateGenre(idGenre, genreNameForTest);
        Genre updatedGenre = entityManager.find(Genre.class, idGenre);
        assertThat(updatedGenre.getName()).isEqualTo(genreNameForTest);
    }

}