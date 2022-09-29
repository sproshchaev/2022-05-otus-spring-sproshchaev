package ru.otus.spring14books.nosql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring14books.nosql.domain.Book;
import ru.otus.spring14books.nosql.domain.Comment;
import ru.otus.spring14books.nosql.repositories.CommentRepositoryDest;

import java.util.List;
import java.util.Optional;

/**
 * Класс CommentServiceNoSql содержит методы для работы с репозиторием комментариев
 *
 * @see CommentRepositoryDest
 */
@Service
public class CommentServiceNoSql implements CommentService {
    private final CommentRepositoryDest commentRepository;
    private final BookService bookService;

    @Autowired
    public CommentServiceNoSql(CommentRepositoryDest commentRepository, BookService bookService) {
        this.commentRepository = commentRepository;
        this.bookService = bookService;
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
        Book book = bookService.findBookById(idBook);
        if (book != null) {
            Comment newComment = commentRepository.save(new Comment(comment, book));
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
        Optional<Comment> comment = commentRepository.findById(idComment);
        return comment.isPresent() ? "Comment on the book ('" + comment.get().getBook().getTitle() + "' "
                + comment.get().getBook().getAuthor().getFullName() + " "
                + comment.get().getBook().getGenre().getName() + ") id="
                + idComment + ": " + comment.get().getCommentText()
                : "Comment with id=" + idComment + " not found!";
    }

    /**
     * Метод getAllCommentsBookById возвращает все комментарии к книге
     * Если id книги не найден - метод вернет сообщение об отсутствии книги с id
     * Метод не изменяет данные
     *
     * @param idBook
     * @return
     */
    @Override
    public String getAllCommentsBookById(String idBook) {
        Book book = bookService.findBookById(idBook);
        if (book != null) {
            List<Comment> commentList = commentRepository.findAllByBook(book);
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
        Optional<Comment> comment = commentRepository.findById(idComment);
        if (comment.isPresent()) {
            comment.get().setCommentText(commentText);
            Comment newComment = commentRepository.save(comment.get());
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
        return commentRepository.findById(idComment)
                .map(comment -> {
                    commentRepository.delete(comment);
                    return "The comment id=" + idComment + " has been deleted";
                }).orElse("Comment id=" + idComment + " not found!");
    }

    /**
     * Метод deleteAllCommentBook удаляет все комментарии к книге
     * Если id книги не найден - метод вернет сообщение об отсутствии книги с id
     * Метод изменяет данные
     *
     * @param idBook
     * @return
     */
    @Override
    public String deleteAllCommentBook(String idBook) {
        Book book = bookService.findBookById(idBook);
        if (book != null) {
            List<Comment> commentList = commentRepository.findAllByBook(book);
            commentRepository.deleteAllByBook(book);
            return "Removed " + commentList.size() + " comments to the book id=" + idBook;
        } else {
            return "Book id=" + idBook + " not found!";
        }
    }

    /**
     * Метод countComments возвращает число комментариев
     *
     * @return
     */
    @Override
    public Long countComments() {
        return commentRepository.count();
    }

    /**
     * Метод getAllComment возвращает все комментарии ко всем книгам библиотеки
     *
     * @return
     */
    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    /**
     * Метод findCommentByCommentTextAndBook возвращает список комментариев с соответствующим текстом для выбранной
     * книги
     *
     * @param commentText
     * @param book
     * @return
     */
    @Override
    public List<Comment> findCommentByCommentTextAndBook(String commentText, Book book) {
        return commentRepository.findCommentByCommentTextAndBook(commentText, book);
    }
}
