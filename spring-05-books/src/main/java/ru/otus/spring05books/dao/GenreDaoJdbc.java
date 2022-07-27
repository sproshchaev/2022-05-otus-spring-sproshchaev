package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Класс GenreDaoJdbc реализует интерфейс GenreDao для JDBC
 */
@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    /**
     * Конструктор класса
     *
     * @param jdbc
     */
    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
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
        long genreId = getIdByGenre(genre);
        if (genreId == 0) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("name", genre.getName());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update("insert into genre (name) values (:name)", params, keyHolder);
            return keyHolder.getKey().longValue();
        } else {
            return genreId;
        }
    }

    /**
     * Метод updateGenre обновляет сведения о жанре в библиотеке
     *
     * @param genre
     * @return
     */
    @Override
    public boolean updateGenre(Genre genre) {
        int result = jdbc.update("update genre set name = :name where id = :id",
                Map.of("id", genre.getId(), "name", genre.getName()));
        return result == 1 ? true : false;
    }

    /**
     * Метод deleteGenre удаляет сведения об жанре из библиотеки
     *
     * @param genre
     * @return
     */
    @Override
    public boolean deleteGenre(Genre genre) {
        int result = jdbc.update("delete from genre where id = :id", Map.of("id", genre.getId()));
        return result == 1 ? true : false;
    }

    /**
     * Метод getGenreById формирует сведения о жанре по id
     *
     * @param id
     * @return
     */
    @Override
    public Genre getGenreById(long id) {
        List<Genre> genreList = jdbc.query("select id, name from genre where id = :id", Map.of("id", id),
                new GenreMapper());
        return genreList.size() == 0 ? null : genreList.get(0);
    }

    /**
     * Метод getIdByGenre возвращает id передаваемого жанра
     * Метод query использует jdbc.query, так как возможно получение как 0, так и 1, а jdbc.queryForObject выбрасывает
     * исключение если результат 0
     *
     * @param genre
     * @return
     */
    @Override
    public long getIdByGenre(Genre genre) {
        List<Genre> genreList = jdbc.query("select id, name " + "from genre " + "where name = :name",
                Map.of("name", genre.getName()), new GenreMapper());
        return genreList.size() == 0 ? 0 : genreList.get(0).getId();
    }

    /**
     * Метод getAllGenres формирует сведения по всем жанрам из библиотеки
     *
     * @return
     */
    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genreList = jdbc.query("select id, name from genre", Map.of("", ""), new GenreMapper());
        return genreList;
    }

    /**
     * Метод getCountOfGenres возвращает число жанров, которые есть в библиотеке
     *
     * @return
     */
    @Override
    public int getCountOfGenres() {
        Integer count = jdbc.getJdbcOperations().queryForObject("select count(*) from genre", Integer.class);
        return count == null ? 0 : count;
    }

    /**
     * GenreMapper - результирующий запрос для Жанра: id, fullname
     */
    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("id"), rs.getString("name"));
        }
    }

}
