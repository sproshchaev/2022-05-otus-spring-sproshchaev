package ru.otus.spring14books.nosql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.nosql.domain.Book;
import ru.otus.spring14books.nosql.domain.Genre;
import ru.otus.spring14books.nosql.repositories.BookRepositoryDest;
import ru.otus.spring14books.nosql.repositories.GenreRepositoryDest;

import java.util.List;
import java.util.Optional;

/**
 * Класс GenreServiceNoSql содержит методы для работы с репозиторием жанров библиотеки
 *
 * @see GenreRepositoryDest
 */
@Service
public class GenreServiceNoSql implements GenreService {
    private final GenreRepositoryDest genreRepositoryDest;
    private final BookRepositoryDest bookRepositoryDest;

    @Autowired
    public GenreServiceNoSql(GenreRepositoryDest genreRepositoryDest, BookRepositoryDest bookRepositoryDest) {
        this.genreRepositoryDest = genreRepositoryDest;
        this.bookRepositoryDest = bookRepositoryDest;
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
    public String createGenreByName(String name) {
        List<Genre> listGenre = genreRepositoryDest.findAllGenreByName(name);
        if (listGenre.size() == 0) {
            Genre genre = genreRepositoryDest.save(new Genre(name));
            return "New genre (" + genre.getId() + ") '" + genre.getName() + "' has been successfully created!";
        } else {
            return "Genre '" + name + "' is already in the library, his id =" + listGenre.get(0).getId();
        }
    }

    /**
     * Метод createGenreByName создает новый жанр в библиотеке (Crud)
     *
     * @param genre
     */
    @Override
    public void createGenre(Genre genre) {
        List<Genre> genreList = genreRepositoryDest.findAllGenreByName(genre.getName());
        if (genreList.size() == 0) {
            genreRepositoryDest.save(genre);
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
        List<Genre> listGenre = genreRepositoryDest.findAllGenreByName(name);
        return listGenre.size() == 0 ? "Genre '" + name + "' not found in the library!"
                : "Genre '" + name + "' has an id=" + listGenre.get(0).getId();
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
        Optional<Genre> genre = genreRepositoryDest.findById(id);
        return genre.isEmpty() ? "Genre id=" + id + " not found!"
                : "Genre with id=" + id + ": '" + genre.get().getName() + "'";
    }

    /**
     * Метод getAllGenres получает список всех жанров из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Override
    public String getAllGenres() {
        List<Genre> genreList = genreRepositoryDest.findAll();
        String genresString = "Genres in the library:\n ";
        for (int i = 0; i < genreList.size(); i++) {
            genresString = genresString + (i + 1) + ") " + genreList.get(i).getName()
                    + " id=" + genreList.get(i).getId()
                    + (i < (genreList.size() - 1) ? ";\n " : ".");
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
    @Override
    public String updateGenre(String id, String name) {
        if (genreRepositoryDest.findById(id).isPresent()) {
            Genre newGenre = genreRepositoryDest.save(new Genre(id, name));
            return "Information about the genre (" + "id=" + id + " " + name + ") has been updated: "
                    + "id=" + newGenre.getId() + " " + newGenre.getName() + "!";
        } else {
            return "Update genre error: genre id=" + id + " not found!";
        }
    }

    /**
     * Метод deleteGenreById удаляет данные о жанре из библиотеки (cruD)
     * Перед удалением выполняется:
     * 1) проверка существования жанра с таким id в таблице genre
     * 2) наличие книг у которых указан данный жанр, если книги есть - удаление жанра невозможно
     * Метод изменяет данные
     * Для реализации метода используется MongoTemplate
     *
     * @param id
     * @return
     */
    @Override
    public String deleteGenreById(String id) {
        Optional<Genre> genreForDelete = genreRepositoryDest.findById(id);
        if (genreForDelete.isPresent()) {
            List<Book> bookList = bookRepositoryDest.findBookByGenre(genreForDelete.get());
            if (bookList.size() == 0) {
                genreRepositoryDest.delete(genreForDelete.get());
                return "Genre (id=" + id + " " + genreForDelete.get().getName() + ") removed from the library";
            } else {
                return "Delete error: genre id=" + id + " " + genreForDelete.get().getName() + " is present in "
                        + bookList.size() + " books!";
            }
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
    @Override
    public Genre getFirstGenreByName(String genreName) {
        List<Genre> genreList = genreRepositoryDest.findAllGenreByName(genreName);
        return (genreList.size() == 0) ? genreRepositoryDest.save(new Genre(genreName)) : genreList.get(0);
    }

    /**
     * Метод countGenres возвращает число жанров
     *
     * @return
     */
    @Override
    public Long countGenres() {
        return genreRepositoryDest.count();
    }
}
