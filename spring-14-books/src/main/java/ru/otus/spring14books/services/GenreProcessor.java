package ru.otus.spring14books.services;

import org.springframework.batch.item.ItemProcessor;
import ru.otus.spring14books.sql.domain.Genre;

/**
 * Интерфейс GenreProcessor расширяет интерфейс ItemProcessor для сущности Genre
 *
 * @see Genre
 */
public interface GenreProcessor extends ItemProcessor<Genre, ru.otus.spring14books.nosql.domain.Genre> {
}
