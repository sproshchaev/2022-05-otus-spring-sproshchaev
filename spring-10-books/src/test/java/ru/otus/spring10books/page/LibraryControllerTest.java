package ru.otus.spring10books.page;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring10books.services.LibraryService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("LibraryControllerTest test s should ")
@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService libraryService;

    @DisplayName("display a page library")
    @Test
    void shouldLibraryPage() throws Exception {
        given(libraryService.aboutLibrary()).willReturn("Welcome to our library!");
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("library"))
                .andExpect(model().attributeExists("welcomeString"));
    }
}