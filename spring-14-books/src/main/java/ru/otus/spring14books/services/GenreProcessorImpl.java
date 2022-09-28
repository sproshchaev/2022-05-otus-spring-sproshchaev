package ru.otus.spring14books.services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.sql.domain.Genre;

/**
 * Класс GenreProcessorImpl формирует новую сущность для записи в MongoDB
 */
@Service
public class GenreProcessorImpl implements GenreProcessor {

    /**
     * Метод process получает сущность Genre из реляционной СУБД SQL и возвращает
     * сущность для записи в СУБД NoSQL
     *
     * @param genreSql
     * @return
     */
    @Override
    public ru.otus.spring14books.nosql.domain.Genre process(Genre genreSql) {
        ru.otus.spring14books.nosql.domain.Genre genreNoSql = new ru.otus.spring14books.nosql.domain.Genre(
                String.valueOf(new ObjectId()),
                genreSql.getName()
        );
        return genreNoSql;
    }
}
