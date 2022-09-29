package ru.otus.spring14books.services;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.sql.domain.Author;
import ru.otus.spring14books.sql.services.AuthorService;

import java.util.List;

/**
 * Класс AuthorReaderImpl реализует методы интерфейса AuthorReader для сущности Author
 * Считывание происходит по одной записи из выборки
 */
@Service
public class AuthorReaderImpl implements AuthorReader {

    private final AuthorService authorService;
    private List<Author> authorList;
    private int index = -1;

    @Autowired
    public AuthorReaderImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Методы, аннотированные @BeforeStep, будут выполнены 1 раз перед запуском всего шага
     * Метод getAuthorList() формирует список всех авторов для дальнейшей обработки в методе read()
     */
    @BeforeStep
    public void getAuthorList() {
        authorList = authorService.getAllAuthors();
    }

    /**
     * Метод read() возвращает по одной записи из полученной выборки authorList для процессора
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
        if (index < authorList.size()) {
            return authorList.get(index);
        } else {
            return null;
        }
    }
}
