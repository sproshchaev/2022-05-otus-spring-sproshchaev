package ru.otus.spring18books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring18books.domain.Author;
import ru.otus.spring18books.domain.Book;
import ru.otus.spring18books.dto.BookDto;
import ru.otus.spring18books.services.AuthorService;
import ru.otus.spring18books.services.BookService;

import java.util.List;

/**
 * Класс BookController обрабатывает запросы, связанные с сущностью Book
 *
 * @see ru.otus.spring18books.domain.Book
 */
@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public String booksPage(Model model) {
        List<Author> authorList = authorService.getAllAuthors();
        model.addAttribute("authors", authorList);
        model.addAttribute("id_book", 0);
        return "books";
    }

    @GetMapping("/booksadmin")
    public String booksAdminPage(Model model) {
        List<Author> authorList = authorService.getAllAuthors();
        model.addAttribute("authors", authorList);
        model.addAttribute("id_book", 0);
        return "booksadmin";
    }

    @GetMapping(value = "/booksadmin", params = {"filter", "value"})
    public String booksAdminFilter(@RequestParam("filter") String filter, @RequestParam("value") String value,
                                   Model model) {
        if (filter.equals("allbook")) {
            model.addAttribute("books", bookService.getAllBook());
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("id_book", 0);
        }
        if (filter.equals("author")) {
            if (!value.equals("")) {
                model.addAttribute("books", bookService.getAllBookByAuthor(value));
            }
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("id_book", 0);
        }
        if (filter.equals("id")) {
            model.addAttribute("books", bookService.getBookById(Long.parseLong(value)));
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("id_book", value);
        }
        return "booksadmin";
    }

    @GetMapping(value = "/booksadmin", params = {"operation", "id"})
    public String booksAdminOperation(@RequestParam("operation") String operation, @RequestParam("id") long id,
                                      Model model) {
        String templateName = null;
        if (operation.equals("new")) {
            model.addAttribute("book_dto", new BookDto("", "", ""));
            templateName = "addbook";
        }
        if (operation.equals("edit")) {
            Book book = bookService.getBookById(id);
            model.addAttribute("book_dto", BookDto.fromDomainObject(book));
            templateName = "editbook";
        }
        if (operation.equals("delete")) {
            Book book = bookService.getBookById(id);
            model.addAttribute("book_dto", BookDto.fromDomainObject(book));
            templateName = "deletebook";
        }
        if (operation.equals("read")) {
            Book book = bookService.getBookById(id);
            model.addAttribute("book_dto", BookDto.fromDomainObject(book));
            templateName = "readbook";
        }
        return templateName;
    }

    @GetMapping(value = "/books", params = {"filter", "value"})
    public String booksFilter(@RequestParam("filter") String filter, @RequestParam("value") String value, Model model) {
        if (filter.equals("allbook")) {
            model.addAttribute("books", bookService.getAllBook());
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("id_book", 0);
        }
        if (filter.equals("author")) {
            if (!value.equals("")) {
                model.addAttribute("books", bookService.getAllBookByAuthor(value));
            }
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("id_book", 0);
        }
        if (filter.equals("id")) {
            model.addAttribute("books", bookService.getBookById(Long.parseLong(value)));
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("id_book", value);
        }
        return "books";
    }

    @GetMapping(value = "/books", params = {"operation", "id"})
    public String booksOperation(@RequestParam("operation") String operation, @RequestParam("id") long id, Model model) {
        String templateName = null;
        if (operation.equals("new")) {
            model.addAttribute("book_dto", new BookDto("", "", ""));
            templateName = "addbook";
        }
        if (operation.equals("edit")) {
            Book book = bookService.getBookById(id);
            model.addAttribute("book_dto", BookDto.fromDomainObject(book));
            templateName = "editbook";
        }
        if (operation.equals("delete")) {
            Book book = bookService.getBookById(id);
            model.addAttribute("book_dto", BookDto.fromDomainObject(book));
            templateName = "deletebook";
        }
        if (operation.equals("read")) {
            Book book = bookService.getBookById(id);
            model.addAttribute("book_dto", BookDto.fromDomainObject(book));
            templateName = "readbook";
        }
        return templateName;
    }

    @PostMapping("/createbook")
    public String saveBook(@ModelAttribute BookDto bookDto, Model model) {
        Book book = bookService.createNewBook(bookDto.getTitle(), bookDto.getAuthorFullName(), bookDto.getGenreName());
        return (book != null) ? "redirect:/books?filter=id&value=" + book.getId() : "";
    }

    @PostMapping("/saveeditedbook")
    public String saveEditedBook(@ModelAttribute BookDto bookDto, Model model) {
        int countEditedBooks = bookService.updateBookById(bookDto.getId(), bookDto.getTitle(),
                bookDto.getAuthorFullName(), bookDto.getGenreName());
        return "redirect:/books?filter=id&value=" + bookDto.getId();
    }

    @PostMapping("/deletingbook")
    public String deletingBook(@ModelAttribute BookDto bookDto, Model model) {
        bookService.deleteBookById(bookDto.getId());
        return "books";
    }
}