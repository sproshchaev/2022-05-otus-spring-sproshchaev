package ru.otus.spring11books.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring11books.domain.Book;
import ru.otus.spring11books.repositories.CommentRepository;

/**
 * Класс MongoCommentsCascadeDeleteEventsListener содержит методы каскадного удаления комментариев
 * в случае удаления книги
 *
 * @see ru.otus.spring11books.repositories.CommentRepository
 */
@Component
public class MongoCommentsCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {
    private final CommentRepository commentRepository;

    @Autowired
    public MongoCommentsCascadeDeleteEventsListener(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        String idDeletedBook = event.getSource().get("_id").toString();
        commentRepository.deleteAllByBook(new Book(idDeletedBook));
    }
}