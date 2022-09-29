package ru.otus.spring14books.sql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring14books.sql.domain.Comment;
import ru.otus.spring14books.sql.repositories.CommentRepositorySource;

import java.util.List;

/**
 * Класс CommentServiceSql содержит методы для работы с репозиторием комментариев к книгам
 *
 * @see ru.otus.spring14books.sql.repositories.CommentRepositorySource
 */
@Service
public class CommentServiceSql implements CommentService {

    private final CommentRepositorySource commentRepositorySource;

    @Autowired
    public CommentServiceSql(CommentRepositorySource commentRepositorySource) {
        this.commentRepositorySource = commentRepositorySource;
    }

    /**
     * Получить список всех комментариев ко всем книгам библиотеки
     *
     * @return
     */
    @Override
    public List<Comment> getAllComment() {
        return commentRepositorySource.findAll();
    }

    /**
     * Число комментариев ко всем книгам в библиотеке, определяет популярность ресурса
     * Метод не изменяет данные
     *
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public long countComments() {
        return commentRepositorySource.count();
    }

}
