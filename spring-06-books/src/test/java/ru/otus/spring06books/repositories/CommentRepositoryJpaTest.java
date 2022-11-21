package ru.otus.spring06books.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring06books.entities.Book;
import ru.otus.spring06books.entities.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс CommentRepositoryJpaTest выполняет тестирование методов класса CommentRepositoryJpa
 * Аннотация @DataJpaTest создает кусок контекста слоя persist, TestEntityManager, транзакцию в начале каждого теста
 * тесты из JUnit5 (org.junit.jupiter)
 */
@DisplayName("Repository for working with books ")
@DataJpaTest
@Import({AuthorRepositoryJpa.class, GenreRepositoryJpa.class, CommentRepositoryJpa.class, BookRepositoryJpa.class})
class CommentRepositoryJpaTest {

    /**
     * Внедрение зависимости тестируемого класса
     */
    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    private TestEntityManager entityManager;

    /**
     * Поле ожидаемое количества комментариев в базе
     */
    private static final int EXPECTED_COMMENTS_COUNT = 4;

    /**
     * Метод shouldCreateNewComment тестирует метод createComment
     * В созданной БД на начальном этапе EXPECTED_COMMENTS_COUNT комментариев.
     * Метод создает новый комментарий к книге и через getCommentById() получает его по id
     */
    @DisplayName("create a new comment")
    @Test
    void shouldCreateNewComment() {
        long idBook = 1;
        Comment expectedComment = new Comment(new Book(idBook), "New_comment");
        long idExpectedComment = commentRepositoryJpa.createComment(expectedComment);
        Comment actualComment = entityManager.find(Comment.class, idExpectedComment);
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    /**
     * Метод shouldGetCommentById выполняет тестирование метода getCommentById
     * При создании БД под id=1 "The Pilgrims Progress — is a very interesting book!"
     */
    @DisplayName("get the comment by id")
    @Test
    void shouldGetCommentById() {
        long idBook = 1;
        Comment expectedComment = new Comment(1, "The Pilgrims Progress — is a very interesting book!", new Book(idBook));
        Comment actualComment = commentRepositoryJpa.getCommentById(1);
        assertThat(actualComment.getCommentText()).isEqualTo(expectedComment.getCommentText());
    }
    /**
     * Метод shouldGetIdByComment тестирует getIdByComment
     */
    @DisplayName("get the comment's id")
    @Test
    void shouldGetIdByComment() {
        long expectedIdComment = 1;
        Comment commentForGet = new Comment("The Pilgrims Progress — is a very interesting book!");
        long commentId = commentRepositoryJpa.getIdByComment(commentForGet);
        assertThat(commentId).isEqualTo(expectedIdComment);
    }

    /**
     * Метод shouldUpdateComment тестирует updateComment
     * Через инструкцию:
     * commentForUpdate.setId(1);
     * entityManager.merge(commentForUpdate);
     * изменения сохраняются и через метод commentRepositoryJpa.getCommentById(1) сравниваются две сущности в assertThat
     */
    @DisplayName("update a new comment")
    @Test
    void shouldUpdateComment() {
        long idBook = 1;
        Comment commentForUpdate = new Comment(new Book(idBook), "New comment");
        commentRepositoryJpa.updateComment(commentForUpdate);
        commentForUpdate.setId(1);
        entityManager.merge(commentForUpdate);
        assertThat(commentRepositoryJpa.getCommentById(1).getCommentText()).isEqualTo(commentForUpdate.getCommentText());
    }

    /**
     * Метод shouldDeleteCommentById тестирует метод deleteCommentById
     */
    @DisplayName("deletes the comment by id")
    @Test
    void shouldDeleteCommentById() {
        long idBook = 1;
        Comment commentForDelete = new Comment(new Book(idBook), "New comment");
        long commentForDeleteId = commentRepositoryJpa.createComment(commentForDelete);
        boolean result = commentRepositoryJpa.deleteCommentById(commentForDeleteId);
        assertThat(result).isEqualTo(true);
    }

    /**
     * Метод shouldGetAllCommentsBookById тестирует getAllCommentsBookById
     */
    @DisplayName("get all comments on the book by id")
    @Test
    void shouldGetAllCommentsBookById() {
        List<Comment> expectedCommentsList = new ArrayList();
        expectedCommentsList.add(new Comment(new Book(1), "The Pilgrims Progress — is a very interesting book!"));
        List<Comment> actualCommentsList = commentRepositoryJpa.getAllCommentsBookById(1);
        assertThat(actualCommentsList.size()).isEqualTo(expectedCommentsList.size());
    }

    /**
     * Метод shouldGetCountOfComments тестирует getCountOfComments
     * В константе EXPECTED_COMMENTS_COUNT записано число комментариев в библиотеке с которым происходит сравнение
     */
    @DisplayName("return the expected number of comments from the library")
    @Test
    void shouldGetCountOfComments() {
        long actualCommentsCount = commentRepositoryJpa.getCountOfComment();
        assertThat(actualCommentsCount).isEqualTo(EXPECTED_COMMENTS_COUNT);
    }
}