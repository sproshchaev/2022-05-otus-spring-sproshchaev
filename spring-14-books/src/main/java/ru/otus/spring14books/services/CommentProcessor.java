package ru.otus.spring14books.services;

import org.springframework.batch.item.ItemProcessor;
import ru.otus.spring14books.sql.domain.Comment;

/**
 * Интерфейс CommentProcessor расширяет интерфейс ItemProcessor для сущности Comment
 *
 * @see ru.otus.spring14books.sql.domain.Comment
 */
public interface CommentProcessor extends ItemProcessor<Comment, ru.otus.spring14books.nosql.domain.Comment> {
}
