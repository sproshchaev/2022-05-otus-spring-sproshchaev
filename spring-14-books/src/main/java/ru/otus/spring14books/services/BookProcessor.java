package ru.otus.spring14books.services;

import org.springframework.batch.item.ItemProcessor;
import ru.otus.spring14books.sql.domain.Book;

/**
 * Интерфейс BookProcessor расширяет интерфейс ItemProcessor для сущности Book
 *
 * @see Book
 */
public interface BookProcessor extends ItemProcessor<Book, ru.otus.spring14books.nosql.domain.Book> {
}
