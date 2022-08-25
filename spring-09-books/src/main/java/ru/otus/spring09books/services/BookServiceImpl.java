package ru.otus.spring09books.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring09books.domain.Author;
import ru.otus.spring09books.domain.Book;
import ru.otus.spring09books.repositories.BookRepository;

import java.util.List;

/**
 * Класс BookServiceImpl содержит методы для работы с репозиторием книг библиотеки
 *
 * @see ru.otus.spring09books.repositories.BookRepository
 */
@Service
public class BookServiceImpl implements BookService {

    private final AuthorServiceImpl authorService;
    private final GenreServiceImpl genreService;
    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorServiceImpl authorService, GenreServiceImpl genreService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookRepository = bookRepository;
    }

    /**
     * Метод getBookById возвращает книгу по ее id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Book getBookById(long id) {
        return bookRepository.findBookById(id).orElse(null);
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
        return bookRepository.findAll();
    }

    /**
     * Метод возвращает все книги автора
     * Метод не изменяет данные
     *
     * @param authorFullName
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBookByAuthor(String authorFullName) {
        Author author = authorService.getFirstAuthorByFullName(authorFullName);
        return bookRepository.findBookByAuthor(author);
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
        return bookRepository.count();
    }

}