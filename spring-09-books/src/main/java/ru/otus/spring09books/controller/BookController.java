package ru.otus.spring09books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring09books.domain.Author;
import ru.otus.spring09books.domain.Book;
import ru.otus.spring09books.dto.BookDto;
import ru.otus.spring09books.services.AuthorServiceImpl;
import ru.otus.spring09books.services.BookServiceImpl;

import java.util.List;

@Controller
public class BookController {

    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;

    @Autowired
    public BookController(BookServiceImpl bookService, AuthorServiceImpl authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public String booksPage(Model model) {
        List<Author> authorList = authorService.getAllAuthors();
        model.addAttribute("authors", authorList); // TODO: преобразование в dto?
        model.addAttribute("id_book", 0);
        return "books";
    }

    @GetMapping("/books/all")
    public String allBooks(Model model) {
        model.addAttribute("books", bookService.getAllBook()); // TODO: преобразование в dto?
        model.addAttribute("authors", authorService.getAllAuthors()); // TODO: преобразование в dto?
        model.addAttribute("id_book", 0);
        return "books";
    }

    @GetMapping("/books/author")
    public String authorBooks(@RequestParam("author") String authorFullName, Model model) {
        model.addAttribute("books", bookService.getAllBookByAuthor(authorFullName)); // TODO: преобразование в dto?
        model.addAttribute("authors", authorService.getAllAuthors()); // TODO: преобразование в dto?
        model.addAttribute("id_book", 0);
        return "books";
    }

    @GetMapping("/books/id")
    public String id_book(@RequestParam("id") long id, Model model) {
        model.addAttribute("books", bookService.getBookById(id)); // TODO: преобразование в dto?
        model.addAttribute("authors", authorService.getAllAuthors()); // TODO: преобразование в dto?
        model.addAttribute("id_book", id);
        return "books";
    }

    @GetMapping("/books/new")
    public String addNewBook(Model model) {
        model.addAttribute("book_dto", new BookDto("", "", ""));
        return "addbook";
    }

    @PostMapping("/createbook")
    public String saveBook(@ModelAttribute BookDto bookDto, Model model) {
        Book book = bookService.createNewBook(bookDto.getTitle(), bookDto.getAuthorFullName(), bookDto.getGenreName());
        return (book != null) ? "redirect:/books/id?id=" + book.getId() : "";
    }

    @GetMapping("/books/edit")
    public String editBook(@RequestParam("id") long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book_dto", BookDto.fromDomainObject(book));
        return "editbook";
    }

    @PostMapping("/saveeditedbook")
    public String saveEditedBook(@ModelAttribute BookDto bookDto, Model model) {
        int countEditedBooks = bookService.updateBookById(bookDto.getId(), bookDto.getTitle(),
                bookDto.getAuthorFullName(), bookDto.getGenreName());
        return (countEditedBooks == 1) ? "redirect:/books/id?id=" + bookDto.getId() : "";
    }

    @GetMapping("/books/delete")
    public String confirmDeleteBook(@RequestParam("id") long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book_dto", BookDto.fromDomainObject(book));
        return "deletebook";
    }

    @PostMapping("/deletingbook")
    public String deletingBook(@ModelAttribute BookDto bookDto, Model model) {
        bookService.deleteBookById(bookDto.getId());
        return "books";
    }

}