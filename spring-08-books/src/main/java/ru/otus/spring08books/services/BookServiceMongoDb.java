package ru.otus.spring08books.services;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.otus.spring08books.entities.Author;
import ru.otus.spring08books.entities.Book;
import ru.otus.spring08books.entities.Genre;
import ru.otus.spring08books.repositories.BookRepositoryMongoDb;

import java.util.List;
import java.util.Optional;

/**
 * Класс BookServiceMongoDb содержит методы для работы с репозиторием книг библиотеки
 *
 * @see BookRepositoryMongoDb
 */
@Service
public class BookServiceMongoDb implements BookService {
    private final BookRepositoryMongoDb bookRepositoryMongoDb;
    private final MongoTemplate mongoTemplate;
    private final AuthorServiceMongoDb authorServiceMongoDb;
    private final GenreServiceMongoDb genreServiceMongoDb;

    @Autowired
    public BookServiceMongoDb(BookRepositoryMongoDb bookRepositoryMongoDb, MongoTemplate mongoTemplate,
                              AuthorServiceMongoDb authorServiceMongoDb, GenreServiceMongoDb genreServiceMongoDb) {
        this.bookRepositoryMongoDb = bookRepositoryMongoDb;
        this.mongoTemplate = mongoTemplate;
        this.authorServiceMongoDb = authorServiceMongoDb;
        this.genreServiceMongoDb = genreServiceMongoDb;
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
        List<Book> listBook = bookRepositoryMongoDb.findAllByTitleAndAuthorAndGenre(title, author, genre);
        Book book = (listBook.size() == 0) ? bookRepositoryMongoDb.save(new Book(title, author, genre)) : listBook.get(0);
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
        List<Book> listIdBook = bookRepositoryMongoDb.findAllByTitleAndAuthorAndGenre(title, author, genre);
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
        Optional<Book> book = bookRepositoryMongoDb.findById(id);
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
        Optional<Book> book = bookRepositoryMongoDb.findById(id);
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
        List<Book> listBook = bookRepositoryMongoDb.findAll();
        String bookString = "Books (" + listBook.size() + "):\n ";
        for (int i = 0; i < listBook.size(); i++) {
            bookString = bookString + (i + 1) + ") '" + listBook.get(i).getTitle() + "' "
                    + listBook.get(i).getAuthor().getFullName() + " ("
                    + listBook.get(i).getGenre().getName() + ")"
                    + (i < (listBook.size() - 1) ? ",\n " : ".");
        }
        return "Received " + (listBook == null ? 0 : bookString);
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
        if (bookRepositoryMongoDb.findById(id).isPresent()) {
            Author author = authorServiceMongoDb.getFirstAuthorByFullName(authorFullName);
            Genre genre = genreServiceMongoDb.getFirstGenreByName(genreName);
            Book book = bookRepositoryMongoDb.save(new Book(id, title, author, genre));
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
        Book bookForDelete = mongoTemplate.findById(id, Book.class);
        if (bookForDelete != null) {
            DeleteResult deleteResult = mongoTemplate.remove(bookForDelete);
            return "Book (id=" + id + " '" + bookForDelete.getTitle() + "' "
                    + bookForDelete.getAuthor().getFullName() + " "
                    + bookForDelete.getGenre().getName()
                    + ") removed from the library (deleted " + deleteResult.getDeletedCount() + " entries)";
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
        return bookRepositoryMongoDb.count();
    }
}