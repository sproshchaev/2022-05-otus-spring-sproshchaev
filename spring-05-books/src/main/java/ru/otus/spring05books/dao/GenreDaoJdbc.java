package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс GenreDaoJdbc реализует интерфейс GenreDao для JDBC
 */
@Repository
public class GenreDaoJdbc implements GenreDao {
    private final JdbcOperations jdbc;

    /**
     * Конструктор класса
     *
     * @param jdbc
     */
    public GenreDaoJdbc(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Метод createGenre создает новый жанр в библиотеке
     *
     * @param genre
     * @return
     */
    @Override
    public long createGenre(Genre genre) {
        Long id = getIdByGenre(genre);
        if (id == 0) {
            jdbc.update("insert into genre(name) values(?)", genre.getName());
            id = getIdByGenre(genre);
        }
        return id;
    }

    /**
     * Метод updateGenre обновляет сведения о жанре в библиотеке
     *
     * @param genre
     * @return
     */
    @Override
    public boolean updateGenre(Genre genre) {
        return false;
    }

    /**
     * Метод deleteGenre удаляет сведения об жанре из библиотеки
     *
     * @param genre
     * @return
     */
    @Override
    public boolean deleteGenre(Genre genre) {
        return false;
    }

    /**
     * Метод getGenreById формирует сведения о жанре по id
     *
     * @param id
     * @return
     */
    @Override
    public Genre getGenreById(long id) {

        return null;
    }

    /**
     * Метод getIdByGenre возвращает id передаваемого жанра
     * Метод query использует jdbc.query, так как возможно получение как 0, так и 1, а jdbc.queryForObject выбрасывает
     * исключение если результат 0
     * @param genre
     * @return
     */
    @Override
    public long getIdByGenre(Genre genre) {
        List<Long> listId;
        listId = jdbc.query("select id from genre where name = ?", new IdMapper(), genre.getName());
        return listId.size() == 0 ? 0 : listId.get(0);
    }

    /**
     * Метод getAllGenres формирует сведения по всем жанрам из библиотеки
     *
     * @return
     */
    @Override
    public List<Genre> getAllGenres() {
        return null;
    }

    /**
     * Метод getCountOfGenres возвращает число жанров, которые есть в библиотеке
     *
     * @return
     */
    @Override
    public long getCountOfGenres() {
        return 0;
    }

    /**
     * Класс IdMapper формирует набор для получаемого результата из jdbc.query
     */
    private static class IdMapper implements RowMapper<Long> {
        @Override
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Long(rs.getLong("id"));
        }
    }
}
