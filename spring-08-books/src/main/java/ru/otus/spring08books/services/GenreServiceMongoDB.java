package ru.otus.spring08books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring08books.entities.Genre;
import ru.otus.spring08books.repositories.GenreRepository;

import java.util.List;

/**
 * Класс GenreServiceMongoDB содержит методы для работы с репозиторием жанров библиотеки
 *
 * @see ru.otus.spring08books.repositories.GenreRepository
 */
@Service
public class GenreServiceMongoDB implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceMongoDB(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * Метод createGenre создает новый жанр в библиотеке (Crud)
     * Метод выполняет проверку на наличие добавляемого жанра в таблице genre для исключения дубликатов.
     * В результат, возвращаемый методом, добавляется информация - данный жанр был создан, или же он уже есть
     * в справочнике жанров библиотеки
     * Метод изменяет данные
     *
     * @param name
     * @return
     */
    public String createGenre(String name) {
        List<Genre> listGenre = genreRepository.findGenreByName(name);
        String genreId = listGenre.size() == 0 ? "" : listGenre.get(0).getId();
        if (genreId.equals("")) {
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
    @Override
    public String getIdByGenre(String name) {
        return null;
    }

    /**
     * Метод getGenreById получает данные о жанре по его id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Override
    public String getGenreById(String id) {
        return null;
    }

    /**
     * Метод getAllGenres получает список всех жанров из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Override
    public String getAllGenres() {
        return null;
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
    @Override
    public String updateGenre(long id, String name) {
        return null;
    }

    /**
     * Метод deleteGenreById удаляет данные о жанре из библиотеки (cruD)
     * Перед удалением выполняется проверка существования жанра с таким id в таблице genre
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    @Override
    public String deleteGenreById(long id) {
        return null;
    }

    /**
     * Метод getFirstGenreByName возвращает первый жанр из списка с одинаковым значением поля name
     *
     * @param genreName
     * @return
     */
    @Override
    public Genre getFirstGenreByName(String genreName) {
        return null;
    }

}
