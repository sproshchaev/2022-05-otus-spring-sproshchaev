package ru.otus.spring14books.config;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring14books.sql.domain.Author;
import ru.otus.spring14books.sql.services.AuthorService;

import java.util.List;

@Component
public class AuthorReader implements ItemReader {

    private final AuthorService authorService;
    private List<Author> authorList;
    private int index = 0;

    @Autowired
    public AuthorReader(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Методы, аннотированные @BeforeStep, будут выполнены 1 раз перед запуском всего шага
     */
    @BeforeStep
    public void getAuthorList() {
        authorList = authorService.getAllAuthors();
    }

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        index++;
        if (index < authorList.size()) {
            return authorList.get(index);
        } else {
            return null;
        }
    }

}
