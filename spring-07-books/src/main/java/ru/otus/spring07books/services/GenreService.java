package ru.otus.spring07books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring07books.entities.Author;
import ru.otus.spring07books.entities.Genre;
import ru.otus.spring07books.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

/**
 * Класс GenreService содержит методы для работы с репозиторием жанров библиотеки
 *
 * @see ru.otus.spring07books.repositories.GenreRepository
 */
@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Метод createGenre (Crud)
     * Метод выполняет проверку на наличие добавляемого жанра в таблице genre для исключения дубликатов.
     * В результат, возвращаемый методом, добавляется информация - данный жанр был создан, или же он уже есть
     * в справочнике жанров библиотеки
     * Метод изменяет данные
     *
     * @param name
     * @return
     */
    @Transactional
    public String createGenre(String name) {
        List<Long> listIdGenre = genreRepository.getGenreIdByName(name);
        long genreId = listIdGenre.size() == 0 ? 0 : listIdGenre.get(0);
        if (genreId == 0) {
            genreId = genreRepository.save(new Genre(name)).getId();
            return "New genre (" + genreId + ") '" + name + "' has been successfully created!";
        } else {
            return "Genre '" + name + "' is already in the library, his id =" + genreId;
        }
    }

    /**
     * Метод getIdByGenre возвращает информацию о результатах поиска
     * жанра с данным id в библиотеке
     * Метод не изменяет данные
     *
     * @param name
     * @return
     */
    @Transactional(readOnly = true)
    public String getIdByGenre(String name) {
        List<Long> listIdGenre = genreRepository.getGenreIdByName(name);
        return listIdGenre.size() == 0 ? "Genre '" + name + "' not found in the library!" : "Author '"
                + name + "' has an id=" + listIdGenre.get(0);
    }

    /**
     * Метод getGenreById получает данные о жанре по его id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public String getGenreById(long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.isEmpty() ? "Genre not found!" : "Genre with id=" + id + ": '" + genre.get().getName() + "'";
    }

    /**
     * Метод getAllGenres получает список всех жанров из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    public String getAllGenres() {
        List<Genre> genreList = genreRepository.findAll();
        String genresString = "Genres in the library: ";
        for (int i = 0; i < genreList.size(); i++) {
            genresString = genresString + " " + genreList.get(i).getName() + (i < (genreList.size() - 1) ? ", " : ".");
        }
        return genreList.size() == 0 ? "Genres not found!" : genresString;
    }

    /**
     * Метод updateGenre обновляет данные о жанре в библиотеке (crUd)
     * Обновлению подлежит поле name для передаваемого в качестве аргумента id.
     * Перед выполнением запроса на изменение данных об авторе проверяется наличие жанра с таким id,
     * если id был найден в таблице genre, то изменяется поле name, иначе возвращается сообщение,
     * что жанр не был обновлен
     * Метод изменяет данные
     *
     * @param id
     * @param name
     * @return
     */
    @Transactional
    public String updateGenre(long id, String name) {
        if (genreRepository.findById(id).isPresent()) {
            int countGenresUpdated = genreRepository.updateGenre(id, name);
            return "Information about the genre (" + "id=" + id + " " + name + ") has been updated for "
                    + countGenresUpdated + " records!";
        } else {
            return "Update genre error: genre id=" + id + " not found!";
        }
    }

    /**
     * Метод deleteGenreById удаляет данные о жанре из библиотеки (cruD)
     * Перед удалением выполняется проверка существования жанра с таким id в таблице genre
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional
    public String deleteGenreById(long id) {
        Optional<Genre> genreForDelete = genreRepository.findById(id);
        if (genreForDelete.isPresent()) {
            genreRepository.deleteById(id);
            return "Genre (id=" + id + " " + genreForDelete.get().getName() + ") removed from the library";
        } else {
            return "Delete error: genre id=" + id + " not found!";
        }
    }

    /**
     * Метод getFirstGenreByName возвращает первый жанр из списка с одинаковым значением поля name
     *
     * @param genreName
     * @return
     */
    public Genre getFirstGenreByName(String genreName) {
        List<Genre> authorList = genreRepository.getGenreByName(genreName);
        return (authorList.size() == 0) ? genreRepository.save(new Genre(genreName)) : authorList.get(0);
    }

}
