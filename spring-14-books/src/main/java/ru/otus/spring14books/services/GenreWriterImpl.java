package ru.otus.spring14books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring14books.nosql.domain.Genre;
import ru.otus.spring14books.nosql.services.GenreService;

import java.util.List;

/**
 * Класс GenreWriterImpl реализует методы интерфейса GenreWriter
 */
@Component
public class GenreWriterImpl implements GenreWriter {

    private final GenreService genreService;

    @Autowired
    public GenreWriterImpl(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Метод write обрабатывает записи сущности Genre пачкой (в отличие от райтера и процессора,
     * которые обрабатывают по одной)
     *
     * @param list
     * @throws Exception
     */
    @Override
    public void write(List<? extends Genre> list) {
        for (int i = 0; i < list.size(); i++) {
            if (genreService.getFirstGenreByName(list.get(i).getName()) == null) {
                genreService.createGenre(list.get(i));
            }
        }
    }
}
