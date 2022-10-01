package ru.otus.spring14books.services;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.sql.domain.Genre;
import ru.otus.spring14books.sql.services.GenreService;

import java.util.List;

/**
 * Класс GenreReaderImpl реализует методы интерфейса GenreReader для сущности Genre
 * Считывание происходит по одной записи из выборки
 */
@Service
public class GenreReaderImpl implements GenreReader {

    private final GenreService genreService;
    private List<Genre> genreList;
    private int index = -1;

    @Autowired
    public GenreReaderImpl(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Методы, аннотированные @BeforeStep, будут выполнены 1 раз перед запуском всего шага
     * Метод getGenreList() формирует список жанров для последующей обработки в методе read()
     */
    @BeforeStep
    public void getGenreList() {
        genreList = genreService.getAllGenre();
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
        if (index < genreList.size()) {
            return genreList.get(index);
        } else {
            return null;
        }
    }
}
