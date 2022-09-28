package ru.otus.spring14books.services;

import org.springframework.batch.item.ItemWriter;
import ru.otus.spring14books.nosql.domain.Author;

/**
 * Интерфейс AuthorWriter расширяет интерфейс ItemWriter для сущности Author
 */
public interface AuthorWriter extends ItemWriter<Author> {
}
