package ru.otus.spring09books.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring09books.domain.Author;
import ru.otus.spring09books.domain.Book;
import ru.otus.spring09books.domain.Genre;
import ru.otus.spring09books.services.AuthorServiceImpl;
import ru.otus.spring09books.services.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("BookController test s should ")
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private AuthorServiceImpl authorService;

    @DisplayName("display a page for selecting books")
    @Test
    void shouldBooksPage() throws Exception {
        List<Author> authorList = new ArrayList<>();
        authorList.add(new Author("New_Author"));
        given(authorService.getAllAuthors()).willReturn(authorList);
        this.mvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @DisplayName("display a page with all books")
    @Test
    void shouldAllBooks() throws Exception {
        this.mvc.perform(get("/books/all"))
                .andExpect(status().isOk());
    }

    @DisplayName("display a page with all the author's books")
    @Test
    void shouldAuthorBooks() throws Exception {
        this.mvc.perform(get("/books/author?author=authorFullName"))
                .andExpect(status().isOk());
    }

    @DisplayName("output a page with a book by id")
    @Test
    void shouldGetBookById() throws Exception {
        this.mvc.perform(get("/books/id?id=1"))
                .andExpect(status().isOk());
    }

    @DisplayName("display a page for adding a new book")
    @Test
    void shouldAddNewBook() throws Exception {
        this.mvc.perform(get("/books/id?id=1"))
                .andExpect(status().isOk());
    }

    @DisplayName("edit the selected book")
    @Test
    void shouldEditBook() throws Exception {
        Book book = new Book(2L, "", new Author(""), new Genre(""));
        given(bookService.getBookById(2L)).willReturn(book);
        this.mvc.perform(get("/books/edit?id=2"))
                .andExpect(status().isOk());
    }

}