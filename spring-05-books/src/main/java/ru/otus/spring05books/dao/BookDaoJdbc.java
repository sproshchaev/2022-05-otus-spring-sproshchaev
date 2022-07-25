package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Author;
import ru.otus.spring05books.domain.Book;
import ru.otus.spring05books.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Класс BookDaoJdbc реализует интерфейс BookDao для JDBC
 */
@Repository
public class BookDaoJdbc implements BookDao {
    // Это первый вариант
    private final JdbcOperations jdbc;

    // Второй вариант
    // private final NamedParameterJdbcOperations jdbc2;

    private final GenreDaoJdbc genreDaoJdbc;
    private final AuthorDaoJdbc authorDaoJdbc;

    /**
     * Конструктор класса
     *
     * @param jdbc
     * @param genreDaoJdbc
     * @param authorDaoJdbc
     */
    public BookDaoJdbc(JdbcOperations jdbc, GenreDaoJdbc genreDaoJdbc, AuthorDaoJdbc authorDaoJdbc) {
        this.jdbc = jdbc;
        this.genreDaoJdbc = genreDaoJdbc;
        this.authorDaoJdbc = authorDaoJdbc;
    }

    /**
     * Метод createBook создает новую книгу
     *
     * @param book
     * @return
     */
    @Override
    public long createBook(Book book) {
        long id = getIdByBook(book);
        if (id == 0) {
            jdbc.update("insert into book(title, author_id, genre_id) values (?, ?, ?)",
                    book.getTitle(),
                    authorDaoJdbc.createAuthor(book.getAuthor()),
                    genreDaoJdbc.createGenre(book.getGenre()));
            id = getIdByBook(book);
        }
        return id;
    }

    /**
     * Метод updateBookById обновляет сведения о книге в библиотеке
     *
     * @param id
     * @param book
     * @return
     */
    @Override
    public boolean updateBookById(long id, Book book) {

        return false;
    }

    /**
     * Метод deleteBookById удаляет сведения о книге из библиотеки
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteBookById(long id) {
        jdbc.update("delete from book where id = :id", Map.of(":id", id));
        return true; // result == 1 ? true : false;
    }

    /**
     * Метод getBookById возвращает сведения о книге по ее id
     *
     * @param id
     * @return
     */
    @Override
    public Book getBookById(long id) {
        // jdbc.query("select id from book where title = ?", new IdMapper(), book.getTitle());
        return null; // jdbc.query("select id, title,  from book where id = :id", new BookMapper(), Map.of("id", id));
    }

    /**
     * Метод getIdByBook возвращает id переданной ему книги
     * Метод query использует jdbc.query, так как возможно получение как 0, так и 1, а jdbc.queryForObject выбрасывает
     * исключение если результат 0
     * @param book
     * @return
     */
    @Override
    public long getIdByBook(Book book) {
        List<Long> listId;
        listId = jdbc.query("select id from book where title = ?", new IdMapper(), book.getTitle());
        return listId.size() == 0 ? 0 : listId.get(0);
    }


    /**
     * Метод getAllBooks возвращает коллекцию из всех книг, имеющиеся в библиотеке
     *
     * @return
     */
    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    /**
     * Метод getCountOfBooks возвращает число всех книг, имеющихся в библиотеке
     *
     * @return
     */
    @Override
    public int getCountOfBooks() {
        Integer count = jdbc.queryForObject("select count(*) from book", Integer.class);
        return count == null? 0: count;
    }

    /**
     * BookMapper - результирующий запрос для Книги: id, title, author, genre
     */
    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getLong("id"), rs.getString("title"),
                    new Author(rs.getString("author")),
                    new Genre(rs.getString("genre")));
        }
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
