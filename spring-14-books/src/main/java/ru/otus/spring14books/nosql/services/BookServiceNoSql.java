package ru.otus.spring14books.nosql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.nosql.domain.Author;
import ru.otus.spring14books.nosql.domain.Book;
import ru.otus.spring14books.nosql.domain.Genre;
import ru.otus.spring14books.nosql.repositories.BookRepositoryDest;

import java.util.List;
import java.util.Optional;

/**
 * Класс BookServiceNoSql содержит методы для работы с репозиторием книг библиотеки
 *
 * @see BookRepositoryDest
 */
@Service
public class BookServiceNoSql implements BookService {
    private final BookRepositoryDest bookRepository;
    private final AuthorService authorServiceMongoDb;
    private final GenreService genreServiceMongoDb;

    @Autowired
    public BookServiceNoSql(BookRepositoryDest bookRepository, AuthorService authorService,
                            GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorServiceMongoDb = authorService;
        this.genreServiceMongoDb = genreService;
    }

    /**
     * Метод createBook создает новую книгу (Crud)
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
    @Override
    public String createNewBook(String title, String authorFullName, String genreName) {
        Author author = authorServiceMongoDb.getFirstAuthorByFullName(authorFullName);
        Genre genre = genreServiceMongoDb.getFirstGenreByName(genreName);
        List<Book> listBook = bookRepository.findAllByTitleAndAuthorAndGenre(title, author, genre);
        Book book = (listBook.size() == 0) ? bookRepository.save(new Book(title, author, genre)) : listBook.get(0);
        String bookInfo = "id=" + book.getId() + " '" + book.getTitle() + "' " + book.getAuthor().getFullName()
                + " (" + book.getGenre().getName() + ")";
        return (listBook.size() == 0) ? "Book added " + bookInfo : "Book is already in the library " + bookInfo;
    }

    /**
     * Метод getIdByBook возвращает id для книги, если она есть в библиотеке (cRud)
     * Поиск выполняется по названию, автору и жанру книги.
     * Метод не изменяет данные
     *
     * @param title
     * @param authorFullName
     * @param genreName
     * @return
     */
    @Override
    public String getIdByBook(String title, String authorFullName, String genreName) {
        Author author = authorServiceMongoDb.getFirstAuthorByFullName(authorFullName);
        Genre genre = genreServiceMongoDb.getFirstGenreByName(genreName);
        List<Book> listIdBook = bookRepository.findAllByTitleAndAuthorAndGenre(title, author, genre);
        return listIdBook.size() == 0
                ? "Book '" + title + "' " + authorFullName + " (" + genreName + ") not found in the library!"
                : "Book '" + title + "' " + authorFullName + " (" + genreName + ") has an id=" + listIdBook.get(0).getId();
    }

    /**
     * Метод getBookById возвращает книгу по ее id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Override
    public String getBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.isEmpty() ? "The book was not found!" : "Book: "
                + book.get().getId() + " " + book.get().getAuthor().getFullName()
                + " (genre " + book.get().getGenre().getName() + ")";
    }

    /**
     * Метод findBookById возвращает книгу по ее id (cRud)
     * Если книга с таким id не найдена, возвращает null
     *
     * @param id
     * @return
     */
    @Override
    public Book findBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Override
    public String getAllBook() {
        List<Book> listBook = bookRepository.findAll();
        String bookString = "Books (" + listBook.size() + "):\n ";
        for (int i = 0; i < listBook.size(); i++) {
            bookString = bookString + (i + 1) + ") '" + listBook.get(i).getTitle() + "' "
                    + listBook.get(i).getAuthor().getFullName() + " (" + listBook.get(i).getGenre().getName() + ")"
                    + " id=" + listBook.get(i).getId() + (i < (listBook.size() - 1) ? ";\n " : ".");
        }
        return "Received " + bookString;
    }

    /**
     * Метод updateBookById обновляет данные по книге: название, автора, жанр (crUd)
     * Перед изменением данных проверяется автор и жанр на наличие в справочниках для исключения дубликатов
     * Если id книги не найден, возвращается сообщение о неуспешном обновлении.
     * Метод изменяет данныеo
     *
     * @param id
     * @param title
     * @param authorFullName
     * @param genreName
     * @return
     */
    @Override
    public String updateBookById(String id, String title, String authorFullName, String genreName) {
        if (bookRepository.findById(id).isPresent()) {
            Author author = authorServiceMongoDb.getFirstAuthorByFullName(authorFullName);
            Genre genre = genreServiceMongoDb.getFirstGenreByName(genreName);
            Book book = bookRepository.save(new Book(id, title, author, genre));
            return "The book id=" + book.getId() + " has " + "been updated (title: " + book.getTitle()
                    + ", author: " + book.getAuthor().getFullName() + ", genre: " + book.getGenre().getName() + ")";
        } else {
            return "Book id=" + id + " not found";
        }
    }

    /**
     * Метод deleteBookById (cruD)
     * Перед удалением выполняется проверка на наличие книги с данным id в библиотеке,
     * если книга есть, то производится ее удаление, если нет - возвращается сообщение,
     * что книга не найдена.
     * Метод изменяет данные
     * Для реализации метода используется MongoTemplate
     *
     * @param id
     * @return
     */
    @Override
    public String deleteBookById(String id) {
        Optional<Book> bookForDelete = bookRepository.findById(id);
        if (bookForDelete.isPresent()) {
            bookRepository.delete(bookForDelete.get());
            return "Book (id=" + id + " '" + bookForDelete.get().getTitle() + "' "
                    + bookForDelete.get().getAuthor().getFullName() + " " + bookForDelete.get().getGenre().getName()
                    + ") removed from the library";
        } else {
            return "Delete error: book id=" + id + " not found!";
        }
    }

    /**
     * Метод countBooks возвращает число книг
     *
     * @return
     */
    @Override
    public Long countBooks() {
        return bookRepository.count();
    }
}
