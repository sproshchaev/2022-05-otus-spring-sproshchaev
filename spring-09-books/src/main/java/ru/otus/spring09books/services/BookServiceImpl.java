package ru.otus.spring09books.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring09books.domain.Book;
import ru.otus.spring09books.dto.BookDto;
import ru.otus.spring09books.repositories.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorServiceImpl authorServiceImpl;
    private final GenreServiceImpl genreServiceImpl;
    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorServiceImpl authorServiceImpl, GenreServiceImpl genreServiceImpl, BookRepository bookRepository) {
        this.authorServiceImpl = authorServiceImpl;
        this.genreServiceImpl = genreServiceImpl;
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

    /**
     * Число книг в библиотеке
     *
     * @return
     */
    @Override
    public long countBooks() {
        return bookRepository.count();
    }
}