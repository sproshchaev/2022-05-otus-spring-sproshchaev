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
 * Класс BookService содержит методы для работы с репозиторием книг библиотеки
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
     * @param title     (book.title)
     * @param author    (author.fullName)
     * @param genreName (genre.name)
     * @return
     */
    @Transactional
    public String createNewBook(String title, String authorFullName, String genreName) {
        List<Author> authorList = authorRepository.getAuthorByFullName(authorFullName);
        Author author = (authorList.size() == 0) ? authorRepository.save(new Author(authorFullName)) : authorList.get(0);
        List<Genre> genreList = genreRepository.getGenreByName(genreName);
        Genre genre = (genreList.size() == 0) ? genreRepository.save(new Genre(genreName)) : genreList.get(0);

        List<Book> listBook = bookRepository.findBookByTitleAndAuthorAndGenre(title, author, genre);

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

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    public String getAllBook() {
        List<Book> listBook = bookRepository.findAll();
        String bookString = "Books (" + listBook.size() + "): ";
        for (int i = 0; i < listBook.size(); i++) {
            bookString = bookString + (i + 1) + ")" + listBook.get(i).getTitle() + " "
                    + listBook.get(i).getAuthor().getFullName() + " ("
                    + listBook.get(i).getGenre().getName() + ")"
                    + (i < (listBook.size() - 1) ? ", " : ".");
        }
        return "Received " + (listBook == null ? 0 : bookString);
    }

    /**
     * Метод updateBookById обновляет данные по книге: название, автора, жанр (crUd)
     * Перед изменением данных проверяется автор и жанр на наличие в справочниках для исключения дубликатов
     * Если id книги не найден, возвращается сообщение о неуспешном обновлении.
     * Метод изменяет данные
     *
     * @param id
     * @param title
     * @param author
     * @param genre
     * @return
     */
    @Transactional
    public String updateBookById(long id, String title, String authorFullName, String genreName) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            List<Author> authorList = authorRepository.getAuthorByFullName(authorFullName);
            Author author = (authorList.size() == 0) ? authorRepository.save(new Author(authorFullName)) : authorList.get(0);
            List<Genre> genreList = genreRepository.getGenreByName(genreName);
            Genre genre = (genreList.size() == 0) ? genreRepository.save(new Genre(genreName)) : genreList.get(0);
            int countUpdatedBooks = bookRepository.updateBook(id, title, author, genre);
            return countUpdatedBooks == 1 ? "The book id=" + id + " has " + "been updated (title: " + title + ", author: " + author + ", genre: " + genre + ")"
                    : "Error: Something went wrong!";
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
     *
     * @param id
     * @return
     */
    @Transactional
    public String deleteBookById(long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
            return "The book id=" + id + " has been deleted";
        } else {
            return "Book id=" + id + " not found";
        }
    }
}
