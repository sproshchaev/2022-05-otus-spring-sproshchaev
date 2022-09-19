package ru.otus.spring12books.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring12books.services.AuthorService;
import ru.otus.spring12books.services.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("BookController test should ")
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;

    @DisplayName("testAuthenticated with user in context")
    @WithMockUser(username = "reader", authorities = {"ROLE_USER"})
    @Test
    public void testAuthenticatedWithUserIsOk() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @DisplayName("testAuthenticated without user in context")
    @Test
    public void testAuthenticatedWithOutUserIsRedirection() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().is3xxRedirection());
    }

}