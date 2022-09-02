package ru.otus.spring10books.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring10books.dto.BookDto;
import ru.otus.spring10books.services.AuthorService;
import ru.otus.spring10books.services.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return BookDto.fromDomainObjectList(bookService.getAllBook());
    }

}