package ru.otus.spring11books.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        String welcomeStr = "Welcome to our library! We have more than " + bookRepository.count()
                + " books by " + authorRepository.count()
                + " authors and " + genreRepository.count() + " genres in our library. "
                + "Our readers have left more than " + commentRepository.count() + " comments on the books!";
        model.addAttribute("welcomeString", welcomeStr);
        return "library";
    }
}
