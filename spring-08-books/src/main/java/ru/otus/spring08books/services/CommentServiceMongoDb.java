package ru.otus.spring08books.services;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.otus.spring08books.entities.Book;
import ru.otus.spring08books.entities.Comment;
import ru.otus.spring08books.repositories.CommentRepositoryMongoDb;

import java.util.List;
import java.util.Optional;

/**
 * Класс CommentServiceMongoDb содержит методы для работы с репозиторием комментариев
 *
 * @see CommentRepositoryMongoDb
 */
@Service
public class CommentServiceMongoDb implements CommentService {
    private final CommentRepositoryMongoDb commentRepositoryMongoDb;
    private final MongoTemplate mongoTemplate;
    private final BookServiceMongoDb bookServiceMongoDb;

    public CommentServiceMongoDb(CommentRepositoryMongoDb commentRepositoryMongoDb, MongoTemplate mongoTemplate,
                                 BookServiceMongoDb bookServiceMongoDb) {
        this.commentRepositoryMongoDb = commentRepositoryMongoDb;
        this.mongoTemplate = mongoTemplate;
        this.bookServiceMongoDb = bookServiceMongoDb;
    }

    /**
     * Метод createCommentByIdBook создает новый комментарий (Crud)
     * Уникальность текста комментария не проверяется.
     * Метод изменяет данные
     *
     * @param idBook
     * @param comment
     * @return
     */
    @Override
    public String createCommentByIdBook(String idBook, String comment) {
        Book book = bookServiceMongoDb.findBookById(idBook);
        if (book != null) {
            Comment newComment = commentRepositoryMongoDb.save(new Comment(comment, book));
            return "New comment '" + newComment.getCommentText() + "' for book id=" + newComment.getBook().getId()
                    + " has been successfully created!";
        } else {
            return "Book id=" + idBook + " not found, it is not possible to add a comment!";
        }
    }

    /**
     * Метод getCommentById возвращает комментарий к книге по его id (cRud)
     * Метод не изменяет данные
     *
     * @param idComment
     * @return
     */
    @Override
    public String getCommentById(String idComment) {
        Optional<Comment> comment = commentRepositoryMongoDb.findById(idComment);
        return comment.isPresent() ? "Comment on the book ('" + comment.get().getBook().getTitle() + "' "
                + comment.get().getBook().getAuthor().getFullName() + " "
                + comment.get().getBook().getGenre().getName() + ") id="
                + idComment + ": " + comment.get().getCommentText()
                : "Comment with id=" + idComment + " not found!";
    }

    /**
     * Метод getAllCommentsBookById возвращает все комментарии к книге
     * Если id книги не найден - метод вернет строку ""
     * Метод не изменяет данные
     *
     * @param idBook
     * @return
     */
    @Override
    public String getAllCommentsBookById(String idBook) {
        Book book = bookServiceMongoDb.findBookById(idBook);
        if (book != null) {
            List<Comment> commentList = commentRepositoryMongoDb.findAllByBook(book);
            String commentsString = "All comments (" + commentList.size() + ") on book id=" + idBook + ": \n";
            for (int i = 0; i < commentList.size(); i++) {
                commentsString = commentsString + " " + (i + 1) + ") " + commentList.get(i).getCommentText()
                        + (i < (commentList.size() - 1) ? ", \n" : ".");
            }
            return commentsString;
        } else {
            return "Book id=" + idBook + " not found!";
        }
    }

    /**
     * Метод updateCommentById обновляет комментарий к книге по его id (crUd)
     * Метод изменяет данные
     *
     * @param idComment
     * @param commentText
     * @return
     */
    @Override
    public String updateCommentById(String idComment, String commentText) {
        Optional<Comment> comment = commentRepositoryMongoDb.findById(idComment);
        if (comment.isPresent()) {
            comment.get().setCommentText(commentText);
            Comment newComment = commentRepositoryMongoDb.save(comment.get());
            return "The comment id=" + newComment.getId() + " has been updated: '" + newComment.getCommentText() + "'";
        } else {
            return "Comment id=" + idComment + " not found!";
        }
    }

    /**
     * Метод deleteCommentById удаляет комментарий по id (cruD)
     * Метод изменяет данные
     *
     * @param idComment
     * @return
     */
    @Override
    public String deleteCommentById(String idComment) {
        if (commentRepositoryMongoDb.findById(idComment).isPresent()) {
            commentRepositoryMongoDb.deleteById(idComment);
            return "The comment id=" + idComment + " has been deleted";
        } else {
            return "Comment id=" + idComment + " not found!";
        }
    }

    /**
     * Метод countComments возвращает число комментариев
     *
     * @return
     */
    @Override
    public Long countComments() {
        return commentRepositoryMongoDb.count();
    }
}
