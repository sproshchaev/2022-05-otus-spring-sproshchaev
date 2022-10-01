package ru.otus.spring14books.services;

import org.springframework.batch.item.ItemWriter;
import ru.otus.spring14books.nosql.domain.Book;

/**
 * Интерфейс BookWriter расширяет интерфейс ItemWriter для сущности Book
 */
public interface BookWriter extends ItemWriter<Book> {
}
