package ru.otus.spring18books.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("ErrorController test should ")
@WebMvcTest(ErrorController.class)
class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("testAuth with user")
    @WithMockUser(username = "reader", authorities = {"ROLE_USER"})
    @Test
    public void testAuthWithUserIsOk() throws Exception {
        mockMvc.perform(get("/error")).andExpect(status().isOk());
    }

    @DisplayName("testAuth without user")
    @WithMockUser(username = "administrator", authorities = {"ROLE_ADMIN"})
    @Test
    public void testAuthWithoutUserIsOk() throws Exception {
        mockMvc.perform(get("/error")).andExpect(status().isOk());
    }
}
