package ru.otus.spring14books.services;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.sql.domain.Book;
import ru.otus.spring14books.sql.services.BookService;

import java.util.List;

/**
 * Класс BookReaderImpl реализует методы интерфейса BookReader для сущности Book
 * Считывание происходит по одной записи из выборки
 */
@Service
public class BookReaderImpl implements BookReader {

    private final BookService bookService;
    private List<Book> bookList;
    private int index = -1;

    @Autowired
    public BookReaderImpl(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Методы, аннотированные @BeforeStep, будут выполнены 1 раз перед запуском всего шага
     * Метод getBookList() формирует список книг для последующей обработки в методе read()
     */
    @BeforeStep
    public void getBookList() {
        bookList = bookService.getAllBook();
    }

    /**
     * Метод read() возвращает по одной записи из полученной выборки bookList для процессора
     *
     * @return
     * @throws Exception
     * @throws UnexpectedInputException
     * @throws ParseException
     * @throws NonTransientResourceException
     */
    @Override
    public Object read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        index++;
        if (index < bookList.size()) {
            return bookList.get(index);
        } else {
            return null;
        }
    }
}
