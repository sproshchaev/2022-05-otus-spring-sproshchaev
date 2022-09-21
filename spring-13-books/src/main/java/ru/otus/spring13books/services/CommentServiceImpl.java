package ru.otus.spring13books.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring13books.repositories.CommentRepository;

/**
 * Класс CommentServiceImpl содержит методы для работы с репозиторием комментариев к книгам
 *
 * @see ru.otus.spring13books.repositories.CommentRepository
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
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
        return commentRepository.count();
    }

}