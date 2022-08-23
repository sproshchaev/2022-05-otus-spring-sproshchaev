package ru.otus.spring09books.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring09books.domain.Book;
import ru.otus.spring09books.repositories.BookRepository;

import java.util.List;

@Service
public class BookServiceJpa {

    private final AuthorServiceJpa authorServiceJpa;
    private final GenreServiceJpa genreServiceJpa;
    private final BookRepository bookRepository;

    public BookServiceJpa(AuthorServiceJpa authorServiceJpa, GenreServiceJpa genreServiceJpa, BookRepository bookRepository) {
        this.authorServiceJpa = authorServiceJpa;
        this.genreServiceJpa = genreServiceJpa;
        this.bookRepository = bookRepository;
    }

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }



}
