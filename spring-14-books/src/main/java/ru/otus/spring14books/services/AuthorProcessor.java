package ru.otus.spring14books.services;

import org.springframework.batch.item.ItemProcessor;
import ru.otus.spring14books.nosql.domain.Author;

/**
 * Интерфейс AuthorProcessor расширяет интерфейс ItemProcessor для сущности Author
 *
 * @see ru.otus.spring14books.sql.domain.Author
 */
public interface AuthorProcessor extends ItemProcessor<ru.otus.spring14books.sql.domain.Author, Author> {
}
