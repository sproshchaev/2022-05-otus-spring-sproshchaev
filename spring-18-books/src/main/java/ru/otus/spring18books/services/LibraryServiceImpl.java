package ru.otus.spring18books.services;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс LibraryServiceImpl содержит методы для работы с сервисами авторов, жанров, книг, комментариев
 */
@Service
public class LibraryServiceImpl implements LibraryService {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;
    private final BookService bookService;

    @Autowired
    public LibraryServiceImpl(AuthorService authorService, GenreService genreService, CommentService commentService,
                              BookService bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
        this.bookService = bookService;
    }

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Метод не изменяет данные
     * Метод обернут в Hystrix Javanica
     * @return
     */
    @HystrixCommand(commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="7000")})
    @Transactional(readOnly = true)
    @Override
    public String aboutLibrary() {
        long countOfBooks = bookService.countBooks();
        long countOfAuthors = authorService.countAuthors();
        long countOfGenres = genreService.countGenres();
        long countOfComment = commentService.countComments();
        return "Welcome to our library! We have more than " + countOfBooks
                + " books by " + countOfAuthors + " authors and " + countOfGenres + " genres in our library. " +
                "Our readers have left more than " + countOfComment + " comments on the books!";
    }

}