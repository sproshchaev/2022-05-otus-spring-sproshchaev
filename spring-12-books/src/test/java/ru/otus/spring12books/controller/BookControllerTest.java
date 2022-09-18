package ru.otus.spring12books.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring12books.services.AuthorServiceImpl;
import ru.otus.spring12books.services.BookServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("BookController test should ")
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;
    @MockBean
    private AuthorServiceImpl authorService;

    @DisplayName("testAuthenticated")
    @WithMockUser(username = "reader", authorities = {"ROLE_USER"})
    @Test
    public void testAuthenticated() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

}