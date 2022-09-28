package ru.otus.spring14books.services;

import org.springframework.batch.item.ItemWriter;
import ru.otus.spring14books.nosql.domain.Genre;

/**
 * Интерфейс GenreWriter расширяет интерфейс ItemWriter для сущности Genre
 */
public interface GenreWriter extends ItemWriter<Genre> {
}
