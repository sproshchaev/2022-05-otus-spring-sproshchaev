package ru.otus.spring14books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring14books.nosql.domain.Comment;
import ru.otus.spring14books.nosql.services.CommentService;

import java.util.List;

/**
 * Класс CommentWriterImpl реализует методы интерфейса CommentWriter
 */
@Component
public class CommentWriterImpl implements CommentWriter {

    private final CommentService commentService;

    @Autowired
    public CommentWriterImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Метод write обрабатывает записи сущности Comment пачкой (в отличие от райтера и процессора,
     * которые обрабатывают по одной)
     *
     * @param list
     * @throws Exception
     */
    @Override
    public void write(List<? extends Comment> list) {
        for (int i = 0; i < list.size(); i++) {
            if (commentService.findCommentByCommentTextAndBook(list.get(i).getCommentText(),
                    list.get(i).getBook()).size() == 0) {
                commentService.createCommentByIdBook(list.get(i).getBook().getId(),
                        list.get(i).getCommentText());
            }
        }
    }
}
