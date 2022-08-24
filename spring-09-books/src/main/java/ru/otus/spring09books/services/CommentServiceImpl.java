package ru.otus.spring09books.services;

import org.springframework.stereotype.Service;
import ru.otus.spring09books.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Число комментариев ко всем книгам в библиотеке, определяет популярность ресурса
     *
     * @return
     */
    @Override
    public long countComments() {
        return commentRepository.count();
    }
}