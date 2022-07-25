package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Author;
import ru.otus.spring05books.domain.Book;
import ru.otus.spring05books.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Класс AuthorDaoJdbc реализует интерфейс AuthorDao для JDBC
 */
@Repository
public class AuthorDaoJdbc2 implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    /**
     * Конструктор класса
     *
     * @param jdbc
     */
    public AuthorDaoJdbc2(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Метод createAuthor создает нового автора в библиотеке
     * Если такой автор (fullName) уже есть, то возвращается его id
     *
     * @param author
     * @return
     */
    @Override
    public long createAuthor(Author author) {
        long authorId = getIdByAuthor(author);
        if (authorId == 0) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("fullname", author.getFullName());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update("insert into author (fullname) values (:fullname)",
                    params, keyHolder);
            return keyHolder.getKey().longValue();
        } else {
            return authorId;
        }
    }

    /**
     * Метод updateAuthor обновляет сведения об авторе в библиотеке
     *
     * @param author
     * @return
     */
    @Override
    public boolean updateAuthor(Author author) {
        return false;
    }

    /**
     * Метод deleteAuthor удаляет сведения об авторе из библиотеки
     *
     * @param author
     * @return
     */
    @Override
    public boolean deleteAuthor(Author author) {
        return false;
    }

    /**
     * Метод getAuthorById получает сведения об авторе по Id
     *
     * @param id
     * @return
     */
    @Override
    public Author getAuthorById(long id) {
        return null;
    }

    /**
     * Метод getIdByAuthor получает id автора
     * Метод query использует jdbc.query, так как возможно получение как 0, так и 1, а jdbc.queryForObject выбрасывает
     * исключение если результат 0
     *
     * @param author
     * @return
     */
    @Override
    public long getIdByAuthor(Author author) {
        List<Author> authorList = jdbc.query("select id, fullname " +
                        "from author " +
                        "where fullname = :fullname",
                Map.of("fullname", author.getFullName()),
                new AuthorMapper());
        return authorList.size() == 0 ? 0 : authorList.get(0).getId();
    }

    /**
     * Метод getAllAuthors получает сведения по всем авторам из библиотеки
     *
     * @return
     */
    @Override
    public List<Author> getAllAuthors() {
        return null;
    }

    /**
     * Метод getCountOfAuthors получает число авторов, чьи сведения есть в библиотеке
     *
     * @return
     */
    @Override
    public int getCountOfAuthors() {
/*
        Integer count = jdbc.queryForObject("select count(*) from author", Integer.class);
        return count == null? 0: count;
*/
    return 0;
    }

    /**
     * AuthorMapper - результирующий запрос для Автора: id, fullname
     */
    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("fullname"));
        }
    }


    //!! Удалить
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
