package ru.otus.spring14books.services;

import org.springframework.batch.item.ItemWriter;
import ru.otus.spring14books.nosql.domain.Comment;

/**
 * Интерфейс CommentWriter расширяет интерфейс ItemWriter для сущности Comment
 */
public interface CommentWriter extends ItemWriter<Comment> {
}
