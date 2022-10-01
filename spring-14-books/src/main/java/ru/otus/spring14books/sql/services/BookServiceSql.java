package ru.otus.spring14books.sql.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring14books.sql.domain.Author;
import ru.otus.spring14books.sql.domain.Book;
import ru.otus.spring14books.sql.domain.Genre;
import ru.otus.spring14books.sql.repositories.BookRepositorySource;

import java.util.List;
import java.util.Optional;

/**
 * Класс BookServiceSql содержит методы для работы с репозиторием книг библиотеки
 *
 * @see ru.otus.spring14books.sql.repositories.BookRepositorySource
 */
@Service
public class BookServiceSql implements BookService {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookRepositorySource bookRepositorySource;

    public BookServiceSql(AuthorService authorService, GenreServiceSql genreService, BookRepositorySource bookRepositorySource) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookRepositorySource = bookRepositorySource;
    }

    /**
     * Метод createBook (Crud)
     * Метод выполняет проверку на наличие книги в таблице book для исключения дубликатов.
     * В результат, возвращаемый методом, добавляется информация - данная книга была создан, или же он уже есть
     * в библиотеке
     * Метод изменяет данные
     *
     * @param title          (book.title)
     * @param authorFullName (author.fullName)
     * @param genreName      (genre.name)
     * @return
     */
    @Transactional
    @Override
    public Book createNewBook(String title, String authorFullName, String genreName) {
        Author author = authorService.getFirstAuthorByFullName(authorFullName);
        Genre genre = genreService.getFirstGenreByName(genreName);
        List<Book> listBook = bookRepositorySource.findBookByTitleAndAuthorAndGenre(title, author, genre);
        return (listBook.size() == 0) ? bookRepositorySource.save(new Book(title, author, genre)) : listBook.get(0);
    }

    /**
     * Метод getBookById возвращает книгу по ее id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Book getBookById(long id) {
        return bookRepositorySource.findBookById(id).orElse(null);
    }

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBook() {
        return bookRepositorySource.findAll();
    }

    /**
     * Метод возвращает все книги автора (cRud)
     * Метод не изменяет данные
     *
     * @param authorFullName
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBookByAuthor(String authorFullName) {
        Author author = authorService.getFirstAuthorByFullName(authorFullName);
        return bookRepositorySource.findBookByAuthor(author);
    }

    /**
     * Метод updateBookById обновляет данные по книге: название, автора, жанр (crUd)
     * Перед изменением данных проверяется автор и жанр на наличие в справочниках для исключения дубликатов
     * Если id книги не найден, возвращается сообщение о неуспешном обновлении.
     * Метод изменяет данные
     *
     * @param id
     * @param title
     * @param authorFullName
     * @param genreName
     * @return
     */
    @Transactional
    @Override
    public int updateBookById(long id, String title, String authorFullName, String genreName) {
        Optional<Book> book = bookRepositorySource.findById(id);
        if (book.isPresent()) {
            Author author = authorService.getFirstAuthorByFullName(authorFullName);
            Genre genre = genreService.getFirstGenreByName(genreName);
            return bookRepositorySource.updateBook(id, title, author, genre);
        } else {
            return 0;
        }
    }

    /**
     * Метод deleteBookById (cruD)
     * Перед удалением выполняется проверка на наличие книги с данным id в библиотеке,
     * если книга есть, то производится ее удаление, если нет - возвращается сообщение,
     * что книга не найдена.
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public String deleteBookById(long id) {
        if (bookRepositorySource.findById(id).isPresent()) {
            bookRepositorySource.deleteById(id);
            return "The book id=" + id + " has been deleted";
        } else {
            return "Book id=" + id + " not found";
        }
    }

    /**
     * Число книг в библиотеке
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public long countBooks() {
        return bookRepositorySource.count();
    }

}
