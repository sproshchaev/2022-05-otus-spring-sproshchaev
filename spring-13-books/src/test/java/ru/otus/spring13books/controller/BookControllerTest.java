package ru.otus.spring13books.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.spring13books.services.AuthorService;
import ru.otus.spring13books.services.BookService;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @DisplayName("testAuthenticated with user in context for /books")
    @WithMockUser(username = "reader", authorities = {"ROLE_USER"})
    @Test
    public void testAuthenticatedWithUserForBooksIsOk() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @DisplayName("testAuthenticated without user in context for /books")
    @Test
    public void testAuthenticatedWithOutUserForBooksIsRedirection() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuthenticated with user in context for /books?filter=1&value=2")
    @WithMockUser(username = "reader", authorities = {"ROLE_USER"})
    @Test
    public void testAuthenticatedWithUserForBooksParamFilterValueIsOk() throws Exception {
        mockMvc.perform(get("/books?filter=1&value=2")).andExpect(status().isOk());
    }

    @DisplayName("testAuthenticated without user in context for /books?filter=1&value=2")
    @Test
    public void testAuthenticatedWithOutUserForBooksParamFilterValueIsRedirection() throws Exception {
        mockMvc.perform(get("/books?filter=1&value=2")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuthenticated with user in context for /books?operation=1&id=2")
    @WithMockUser(username = "reader", authorities = {"ROLE_USER"})
    @Test
    public void testAuthenticatedWithUserForBooksParamOperationIdIsOk() throws Exception {
        mockMvc.perform(get("/books?operation=1&id=2")).andExpect(status().isOk());
    }

    @DisplayName("testAuthenticated without user in context for /books?operation=1&id=2")
    @Test
    public void testAuthenticatedWithOutUserForBooksParamOperationIdIsRedirection() throws Exception {
        mockMvc.perform(get("/books?operation=1&id=2")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuthenticated without user in context for /createbook")
    @Test
    public void testAuthenticatedWithOutUserForCreateBookIsRedirection() throws Exception {
        mockMvc.perform(post("/createbook")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuthenticated without user in context for /saveeditedbook")
    @Test
    public void testAuthenticatedWithOutUserForSaveEditBookIsRedirection() throws Exception {
        mockMvc.perform(post("/saveeditedbook")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuthenticated without user in context for /deletingbook")
    @Test
    public void testAuthenticatedWithOutUserForDeletingBookIsRedirection() throws Exception {
        mockMvc.perform(post("/saveeditedbook")).andExpect(status().is3xxRedirection());
    }

}