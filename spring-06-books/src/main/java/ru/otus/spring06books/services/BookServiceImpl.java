package ru.otus.spring06books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06books.entities.Author;
import ru.otus.spring06books.entities.Book;
import ru.otus.spring06books.entities.Genre;
import ru.otus.spring06books.repositories.BookRepositoryJpa;

import java.util.List;

/**
 * Класс BookServiceImpl содержит методы сервиса работы с репозиторием Книг библиотеки
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    public BookServiceImpl(BookRepositoryJpa bookRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @Override
    @Transactional
    public String createNewBook(String title, String author, String genre) {
        long id = bookRepositoryJpa.createBook(new Book(title, new Author(author), new Genre(genre)));
        return "The book (" + id + ") " + title + ", " + author + " (" + genre + ") has been successfully entered!";
    }

    @Override
    @Transactional
    public String deleteBookById(long id) {
        return bookRepositoryJpa.deleteBookById(id) ? "The book id=" + id + " has been deleted" : "Error: Something went "
                + "wrong!";
    }

    @Override
    @Transactional(readOnly = true)
    public String getIdByBook(String title, String fullName, String name) {
        String fullNameBook = title + " " + fullName + " (" + name + ")";
        long id = bookRepositoryJpa.getIdByBook(new Book(title, new Author(fullName), new Genre(name)));
        return id == 0 ? "Book '" + fullNameBook + "' not found in the library!" : "Book '" + fullNameBook
                + "' has an id=" + id;
    }

    @Override
    @Transactional(readOnly = true)
    public String getBookById(long id) {
        Book book = bookRepositoryJpa.getBookById(id);
        return book == null ? "The book was not found!" : "Book: "
                + book.getId() + " " + book.getAuthor().getFullName() + " (genre " + book.getGenre().getName() + ") ";
    }

    @Override
    @Transactional
    public String updateBookById(long id, String title, String author, String genre) {
        return bookRepositoryJpa.updateBookById(id, new Book(title, new Author(author), new Genre(genre))) ?
                "The book id=" + id + " has " + "been updated (title: " + title + ", author: " + author + ", genre: "
                        + genre + ")" : "Error: Something went wrong!";
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllBook() {
        List<Book> listBook = bookRepositoryJpa.getAllBooks();
        String bookString = "Books (" + listBook.size() + "): ";
        for (int i = 0; i < listBook.size(); i++) {
            bookString = bookString + (i + 1) + ")" + listBook.get(i).getTitle() + " "
                    + listBook.get(i).getAuthor().getFullName() + " ("
                    + listBook.get(i).getGenre().getName() + ")"
                    + (i < (listBook.size() - 1) ? ", " : ".");
        }
        return "Received " + (listBook == null ? 0 : bookString);
    }

}
