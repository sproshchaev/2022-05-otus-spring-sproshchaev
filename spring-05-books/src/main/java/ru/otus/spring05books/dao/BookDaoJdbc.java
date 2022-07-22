package ru.otus.spring05books.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring05books.domain.Author;
import ru.otus.spring05books.domain.Book;
import ru.otus.spring05books.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс BookDaoJdbc реализует интерфейс BookDao для JDBC
 */
@Repository
public class BookDaoJdbc implements BookDao {
    private final JdbcOperations jdbc;
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
        // Проверить - есть ли такая книга
        long id = getIdByBook(book);

        // Если нет - то вставляем
        if (id == 0) {
            jdbc.update("insert into book(title, author_id, genre_id) values (?, ?, ?)",
                    book.getTitle(),
                    authorDaoJdbc.createAuthor(book.getAuthor()),
                    genreDaoJdbc.createGenre(book.getGenre()));
            // Запрашиваем id
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
        return false;
    }

    /**
     * Метод getBookById возвращает сведения о книге по ее id
     *
     * @param id
     * @return
     */
    @Override
    public Book getBookById(long id) {
        return null;
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
//        long id = jdbc.queryForObject("select id from book where (title = ?) and (author_id = ?) and (genre_id = ?) ", Long.class,
//                book.getTitle(),
//                0 /* authorDaoJdbc.getIdByAuthor(book.getAuthor()) */,
//                0 /* genreDaoJdbc.getIdByGenre(book.getGenre()) */ );
//        return id;

        // добавить поиск по автору и жанру
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
    public long getCountOfBooks() {
        return 0;
    }


    /**
     *
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
