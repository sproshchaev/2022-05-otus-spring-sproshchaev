package ru.otus.spring17books.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring17books.services.AuthorService;
import ru.otus.spring17books.services.BookService;

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

    @DisplayName("testAuth with user in context for /books")
    @WithMockUser(username = "reader", authorities = {"ROLE_USER"})
    @Test
    public void testAuthWithUserForBooksIsOk() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @DisplayName("testAuth without user in context for /books")
    @Test
    public void testAuthWithOutUserForBooksIsRedirection() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuth with user in context for /books?filter=1&value=2")
    @WithMockUser(username = "reader", authorities = {"ROLE_USER"})
    @Test
    public void testAuthWithUserForBooksParamFilterValueIsOk() throws Exception {
        mockMvc.perform(get("/books?filter=1&value=2")).andExpect(status().isOk());
    }

    @DisplayName("testAuth without user in context for /books?filter=1&value=2")
    @Test
    public void testAuthWithOutUserForBooksParamFilterValueIsRedirection() throws Exception {
        mockMvc.perform(get("/books?filter=1&value=2")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuth with user in context for /books?operation=1&id=2")
    @WithMockUser(username = "reader", authorities = {"ROLE_USER"})
    @Test
    public void testAuthWithUserForBooksParamOperationIdIsOk() throws Exception {
        mockMvc.perform(get("/books?operation=1&id=2")).andExpect(status().isOk());
    }

    @DisplayName("testAuth without user in context for /books?operation=1&id=2")
    @Test
    public void testAuthWithOutUserForBooksParamOperationIdIsRedirection() throws Exception {
        mockMvc.perform(get("/books?operation=1&id=2")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuth without user in context for /createbook")
    @Test
    public void testAuthWithOutUserForCreateBookIsRedirection() throws Exception {
        mockMvc.perform(post("/createbook")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuth without user in context for /saveeditedbook")
    @Test
    public void testAuthWithOutUserForSaveEditBookIsRedirection() throws Exception {
        mockMvc.perform(post("/saveeditedbook")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuth without user in context for /deletingbook")
    @Test
    public void testAuthWithOutUserForDeletingBookIsRedirection() throws Exception {
        mockMvc.perform(post("/saveeditedbook")).andExpect(status().is3xxRedirection());
    }

    // @GetMapping("/booksadmin")
    @DisplayName("testAuth with user in context for /booksadmin")
    @WithMockUser(username = "administrator", authorities = {"ROLE_ADMIN"})
    @Test
    public void testAuthWithUserForBooksAdminIsOk() throws Exception {
        mockMvc.perform(get("/booksadmin")).andExpect(status().isOk());
    }

    @DisplayName("testAuth without user in context for /booksadmin")
    @Test
    public void testAuthWithOutUserForBooksAdminIsRedirection() throws Exception {
        mockMvc.perform(get("/booksadmin")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuth with user in context for /booksadmin?filter=1&value=2")
    @WithMockUser(username = "administrator", authorities = {"ROLE_ADMIN"})
    @Test
    public void testAuthWithUserForBooksAdminParamFilterValueIsOk() throws Exception {
        mockMvc.perform(get("/booksadmin?filter=1&value=2")).andExpect(status().isOk());
    }

    @DisplayName("testAuth without user in context for /booksadmin?filter=1&value=2")
    @Test
    public void testAuthWithOutUserForBooksAdminParamFilterValueIsRedirection() throws Exception {
        mockMvc.perform(get("/booksadmin?filter=1&value=2")).andExpect(status().is3xxRedirection());
    }

    @DisplayName("testAuth with user in context for /booksadmin?operation=1&id=2")
    @WithMockUser(username = "reader", authorities = {"ROLE_ADMIN"})
    @Test
    public void testAuthWithUserForBooksAdminParamOperationIdIsOk() throws Exception {
        mockMvc.perform(get("/booksadmin?operation=1&id=2")).andExpect(status().isOk());
    }

    @DisplayName("testAuth without user in context for /booksadmin?operation=1&id=2")
    @Test
    public void testAuthWithOutUserForBooksAdminParamOperationIdIsRedirection() throws Exception {
        mockMvc.perform(get("/booksadmin?operation=1&id=2")).andExpect(status().is3xxRedirection());
    }

}