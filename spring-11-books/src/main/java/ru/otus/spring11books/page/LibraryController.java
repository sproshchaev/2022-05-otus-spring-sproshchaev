package ru.otus.spring11books.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;
import ru.otus.spring11books.repositories.AuthorRepository;
import ru.otus.spring11books.repositories.BookRepository;
import ru.otus.spring11books.repositories.CommentRepository;
import ru.otus.spring11books.repositories.GenreRepository;

@Controller
public class LibraryController {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public LibraryController(AuthorRepository authorRepository, GenreRepository genreRepository,
                             BookRepository bookRepository, CommentRepository commentRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/")
    public String libraryPage(Model model) {
        String welcomeStrPattern = "Welcome to our library! We have more than %d" +
                " books by %d authors and %d genres in our library. " +
                "Our readers have left more than %d comments on the books!";
        Mono<String> welcomeStringMono = Mono.zip(bookRepository.count(), authorRepository.count(),
                        genreRepository.count(), commentRepository.count())
                .map(t4 -> String.format(welcomeStrPattern, t4.get(0), t4.get(1), t4.get(2), t4.get(3)));

        model.addAttribute("welcomeString", welcomeStringMono);
        return "library";
    }
}
