package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс AuthorDaoJdbc реализует интерфейс AuthorDao для JDBC
 */
@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final JdbcOperations jdbc;

    /**
     * Конструктор класса
     *
     * @param jdbc
     */
    public AuthorDaoJdbc(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Метод createAuthor создает нового автора в библиотеке
     *
     * @param author
     * @return
     */
    @Override
    public long createAuthor(Author author) {
        long id = getIdByAuthor(author);
        if (id == 0) {
            jdbc.update("insert into author (fullname) values (?)", author.getFullName());
            id = getIdByAuthor(author);
        }
        return id;
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
        List<Long> listId;
        listId = jdbc.query("select id from author where fullname = ?", new IdMapper(), author.getFullName());
        return listId.size() == 0 ? 0 : listId.get(0);
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
    public long getCountOfAuthors() {
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
