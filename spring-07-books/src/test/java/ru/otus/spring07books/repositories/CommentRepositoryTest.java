package ru.otus.spring07books.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring07books.entities.Comment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс CommentRepositoryTest тестирует не стандартные методы интерфейса CommentRepository
 * Аннотация replace = AutoConfigureTestDatabase.Replace.NONE определяет тестирование на базе приложения
 * Аннотация @Transactional(propagation = Propagation.NOT_SUPPORTED) не используется
 */
@DisplayName("Repository for working with comments ")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("must edit the text of a comment by its id")
    @Test
    void shouldUpdateComment() {
        long idComment = 1;
        String commentTextForTest = "New_comment_for_test";
        commentRepository.updateComment(idComment, commentTextForTest);
        Comment updatedComment = entityManager.find(Comment.class, idComment);
        assertThat(updatedComment.getCommentText()).isEqualTo(commentTextForTest);
    }

}