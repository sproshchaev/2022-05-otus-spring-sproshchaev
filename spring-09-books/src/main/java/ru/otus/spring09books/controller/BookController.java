package ru.otus.spring09books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring09books.domain.Book;
import ru.otus.spring09books.services.BookServiceJpa;

import java.util.List;

@Controller
public class BookController {

    private final BookServiceJpa bookServiceJpa;

    @Autowired
    public BookController(BookServiceJpa bookServiceJpa) {
        this.bookServiceJpa = bookServiceJpa;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> bookList = bookServiceJpa.getAllBook(); // todo: переделать String на List?
        model.addAttribute("books", bookList);
        return "book";
    }

}