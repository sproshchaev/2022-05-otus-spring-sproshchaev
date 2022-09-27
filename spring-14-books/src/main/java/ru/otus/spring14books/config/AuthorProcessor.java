package ru.otus.spring14books.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.spring14books.sql.domain.Author;

@Component
public class AuthorProcessor implements ItemProcessor<Author, Author> {
    @Override
    public Author process(Author author) throws Exception {
        return author;
    }
}
