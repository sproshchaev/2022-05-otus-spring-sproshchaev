package ru.otus.spring09books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring09books.domain.Book;
import ru.otus.spring09books.dto.BookDto;
import ru.otus.spring09books.services.BookServiceImpl;

import java.util.List;

@Controller
public class BookController {

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String booksPage(Model model) {
        return "books";
    }

    @GetMapping("/books/all")
    public String allBooks(Model model) {
        List<Book> bookList = bookService.getAllBook(); // TODO: преобразование в dto?
        model.addAttribute("books", bookList);
        return "books";
    }


}