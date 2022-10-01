package ru.otus.spring14books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring14books.nosql.domain.Author;
import ru.otus.spring14books.nosql.services.AuthorService;

import java.util.List;

/**
 * Класс AuthorWriterImpl реализует кастомыне методы интерфейса AuthorWriter
 */
@Component
public class AuthorWriterImpl implements AuthorWriter {

    private final AuthorService authorService;

    @Autowired
    public AuthorWriterImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Метод write обрабатывает записи сущности Author пачкой (в отличие от райтера и процессора,
     * которые обрабатывают по одной)
     *
     * @param list
     * @throws Exception
     */
    @Override
    public void write(List<? extends Author> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            if (authorService.getFirstAuthorByFullName(list.get(i).getFullName()) == null) {
                authorService.createAuthor(list.get(i));
            }
        }
    }
}
