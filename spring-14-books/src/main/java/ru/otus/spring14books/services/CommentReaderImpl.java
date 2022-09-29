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
 * Класс CommentReaderImpl реализует методы интерфейса CommentReader для сущности Comment
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
     */
    @BeforeStep
    public void getAuthorList() {

        commentList = commentService.getAllComment();
        System.out.println("Комментариев: " + commentList.size()); // todo удалить!
        for (int i = 0; i < commentList.size(); i++) {
            System.out.println(" -" + commentList.get(i).getId() + " " + commentList.get(i).getCommentText());
        }
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
            System.out.println("read(): " + commentList.get(index)); // todo удалить!
            return commentList.get(index);
        } else {
            return null;
        }
    }

}
