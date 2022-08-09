package ru.otus.spring07books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring07books.entities.Author;
import ru.otus.spring07books.entities.Book;
import ru.otus.spring07books.entities.Genre;
import ru.otus.spring07books.repositories.AuthorRepository;
import ru.otus.spring07books.repositories.BookRepository;
import ru.otus.spring07books.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

/**
 * Класс BookService содержит методы сервиса работы с репозиторием книг библиотеки
 *
 * @see
 */
@Service
public class BookService {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Метод createBook (Crud)
     * Метод выполняет проверку на наличие книги в таблице book для исключения дубликатов.
     * В результат, возвращаемый методом, добавляется информация - данная книга была создан, или же он уже есть
     * в библиотеке
     * Метод изменяет данные
     *
     * @param title  (book.title)
     * @param author (author.fullName)
     * @param genreName  (genre.name)
     * @return
     */
    @Transactional
    public String createNewBook(String title, String authorFullName, String genreName) {
        List<Author> authorList = authorRepository.getAuthorByFullName(authorFullName);
        Author author = (authorList.size() == 0) ? authorRepository.save(new Author(authorFullName)) : authorList.get(0);
        List<Genre> genreList = genreRepository.getGenreByName(genreName);
        Genre genre = (genreList.size() == 0) ? genreRepository.save(new Genre(genreName)) : genreList.get(0);
        List<Book> listBook = bookRepository.getBook(title, authorFullName, genreName);
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
     * @param fullName
     * @param name
     * @return
     */
    @Transactional(readOnly = true)
    public String getIdByBook(String title, String fullName, String name) {
        List<Long> listIdBook = bookRepository.getIdByBook(title, fullName, name);
        return listIdBook.size() == 0
                ? "Book '" + title + "' " + fullName + " (" + name + ") not found in the library!"
                : "Book '" + title + "' " + fullName + " (" + name + ") has an id=" + listIdBook.get(0);
    }

    /**
     * Метод getBookById возвращает книгу по ее id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public String getBookById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.isEmpty() ? "The book was not found!" : "Book: "
                + book.get().getId() + " " + book.get().getAuthor().getFullName()
                + " (genre " + book.get().getGenre().getName() + ")";
    }

}
