package ru.otus.spring14books.sql.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring14books.sql.repositories.CommentRepositorySource;

/**
 * Класс CommentServiceSql содержит методы для работы с репозиторием комментариев к книгам
 *
 * @see ru.otus.spring14books.sql.repositories.CommentRepositorySource
 */
@Service
public class CommentServiceSql implements CommentService {

    private final CommentRepositorySource commentRepositorySource;

    public CommentServiceSql(CommentRepositorySource commentRepositorySource) {
        this.commentRepositorySource = commentRepositorySource;
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
