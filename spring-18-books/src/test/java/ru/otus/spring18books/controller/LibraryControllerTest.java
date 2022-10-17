package ru.otus.spring18books.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring18books.services.LibraryService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("LibraryController test should ")
@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @DisplayName("testAuth with user")
    @WithMockUser(username = "guest", authorities = {"ROLE_USER"})
    @Test
    public void testAuthWithUserIsOk() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @DisplayName("testAuth without user")
    @Test
    public void testAuthWithoutUserIsOk() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

}