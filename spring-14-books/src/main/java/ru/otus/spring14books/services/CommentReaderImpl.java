package ru.otus.spring14books.services;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.sql.domain.Comment;
import ru.otus.spring14books.sql.services.CommentService;

import java.util.List;

/**
 * Класс CommentReaderImpl реализует кастомные методы интерфейса CommentReader для сущности Comment
 * Считывание происходит по одной записи из выборки
 */
@Service
public class CommentReaderImpl implements CommentReader {

    private final CommentService commentService;
    private List<Comment> commentList;
    private int index = -1;

    @Autowired
    public CommentReaderImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Методы, аннотированные @BeforeStep, будут выполнены 1 раз перед запуском всего шага
     * Метод getCommentList() формирует список комментариев для последующей обработки в методе read()
     */
    @BeforeStep
    public void getCommentList() {
        commentList = commentService.getAllComment();
    }

    /**
     * Метод read() возвращает по одной записи из полученной выборки authorList для процессора
     *
     * @return
     * @throws Exception
     * @throws UnexpectedInputException
     * @throws ParseException
     * @throws NonTransientResourceException
     */
    @Override
    public Object read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        index++;
        if (index < commentList.size()) {
            return commentList.get(index);
        } else {
            return null;
        }
    }
}
