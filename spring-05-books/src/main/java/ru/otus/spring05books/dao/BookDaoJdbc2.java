package ru.otus.spring05books.dao;

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
 * Класс BookDaoJdbc реализует интерфейс BookDao для JDBC
 */
@Repository
public class BookDaoJdbc2 implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final GenreDaoJdbc genreDaoJdbc;
    private final AuthorDaoJdbc authorDaoJdbc;

    /**
     * Конструктор класса
     *
     * @param jdbc
     * @param genreDaoJdbc
     * @param authorDaoJdbc
     */
    public BookDaoJdbc2(NamedParameterJdbcOperations jdbc, GenreDaoJdbc genreDaoJdbc, AuthorDaoJdbc authorDaoJdbc) {
        this.jdbc = jdbc;
        this.genreDaoJdbc = genreDaoJdbc;
        this.authorDaoJdbc = authorDaoJdbc;
    }

    /**
     * Метод createBook создает новую книгу
     * Если такая книга уже есть, то возвращается ее id
     *
     * @param book
     * @return
     * @see ru.otus.spring05books.dao.BookDaoJdbc2#getIdByBook
     */
    @Override
    public long createBook(Book book) {
        long bookId = getIdByBook(book);
        if (bookId == 0) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("title", book.getTitle());
            params.addValue("author_id", authorDaoJdbc.createAuthor(book.getAuthor()));
            params.addValue("genre_id", genreDaoJdbc.createGenre(book.getGenre()));
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update("insert into book (title, author_id, genre_id) values (:title, :author_id, :genre_id)",
                    params, keyHolder);
            return keyHolder.getKey().longValue();
        } else {
            return bookId;
        }
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
        long authorId = authorDaoJdbc.createAuthor(book.getAuthor());
        long genreId = genreDaoJdbc.createGenre(book.getGenre());
        int result = jdbc.update("update book set title = :title, author_id = :author_id, genre_id = :genre_id " +
                        "where id = :id",
                Map.of("id", id, "title", book.getTitle(), "author_id", authorId, "genre_id", genreId));
        return result == 1 ? true : false;
    }

    /**
     * Метод deleteBookById удаляет сведения о книге из библиотеки
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteBookById(long id) {
        int result = jdbc.update("delete from book where id = :id", Map.of("id", id));
        return result == 1 ? true : false;
    }

    /**
     * Метод getBookById возвращает сведения о книге по ее id
     * SQL: select book.id, book.title, book.author_id, author.fullname, book.genre_id, genre.name from book, author, genre where book.id = 1 and book.author_id = author.id and book.genre_id = genre.id
     *
     * @param id
     * @return
     */
    @Override
    public Book getBookById(long id) {
        List<Book> bookList = jdbc.query("select book.id, book.title, book.author_id, author.fullname, book.genre_id, genre.name " +
                        "from book, author, genre " +
                        "where book.id = :id and book.author_id = author.id and book.genre_id = genre.id",
                Map.of("id", id),
                new BookMapper2());
        return bookList.size() == 0 ? null : bookList.get(0);
    }

    /**
     * Метод getIdByBook возвращает id переданной ему книги, проверяя по названию книги, автору (полное имя), жанру
     * <p>
     * SQL select book.id, book.title, book.author_id, author.fullname, book.genre_id, genre.name from book, author, genre where book.title = 'The Pilgrim’s Progress' and author.fullname = 'John Bunyan' and genre.name = 'History'
     * <p>
     * Примечание: метод query использует jdbc.query, так как возможно получение как 0, так и 1, а jdbc.queryForObject выбрасывает
     * исключение если результат 0
     *
     * @param book
     * @return
     */
    @Override
    public long getIdByBook(Book book) {
        List<Book> bookList = jdbc.query("select book.id, book.title, book.author_id, author.fullname, book.genre_id, genre.name " +
                        "from book, author, genre " +
                        "where book.title = :title and author.fullname = :fullname and genre.name = :name",
                Map.of("title", book.getTitle(), "fullname", book.getAuthor().getFullName(), "name", book.getGenre().getName()),
                new BookMapper2());
        return bookList.size() == 0 ? 0 : bookList.get(0).getId();
    }


    /**
     * Метод getAllBooks возвращает коллекцию из всех книг, имеющиеся в библиотеке
     *
     * @return
     */
    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = jdbc.query("select book.id, book.title, book.author_id, author.fullname, book.genre_id, genre.name " +
                        "from book, author, genre " +
                        "where book.author_id = author.id and book.genre_id = genre.id",
                Map.of("", ""),
                new BookMapper2());
        return bookList;
    }

    /**
     * Метод getCountOfBooks возвращает число всех книг, имеющихся в библиотеке
     * В методе используется конструкция jdbc.getJdbcOperations().queryForObject() для исключения внедрения поля класса
     * private final JdbcOperations jdbc;
     *
     * @return
     */
    @Override
    public int getCountOfBooks() {
        Integer count = jdbc.getJdbcOperations().queryForObject("select count(*) from book", Integer.class);
        return count == null ? 0 : count;
    }

    /**
     * BookMapper2 - результирующий запрос для Книги: id, title, author_id, fullname, genre_id, name
     */
    private static class BookMapper2 implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getLong("id"), rs.getString("title"),
                    new Author(rs.getLong("author_id"), rs.getString("fullname")),
                    new Genre(rs.getLong("genre_id"), rs.getString("name")));
        }
    }

    // Удалить!!!

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

    // Удалить!!!

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
