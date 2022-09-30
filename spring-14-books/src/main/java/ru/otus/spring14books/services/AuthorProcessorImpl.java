package ru.otus.spring14books.services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.nosql.domain.Author;

/**
 * Класс AuthorProcessorImpl формирует новую сущность для записи в MongoDB
 */
@Service
public class AuthorProcessorImpl implements AuthorProcessor {

    /**
     * Метод process получает сущность Author из реляционной СУБД SQL и возвращает
     * сущность для записи в СУБД NoSQL
     *
     * @param authorSql
     * @return
     */
    @Override
    public Author process(ru.otus.spring14books.sql.domain.Author authorSql) {
        Author authorNoSql = new Author(
                String.valueOf(new ObjectId()),
                authorSql.getFullName()
        );
        return authorNoSql;
    }
}
