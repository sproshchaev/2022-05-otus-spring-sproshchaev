package ru.otus.spring11books.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring11books.domain.Author;
import ru.otus.spring11books.repositories.AuthorRepository;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/api/authors")
    public Flux<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
