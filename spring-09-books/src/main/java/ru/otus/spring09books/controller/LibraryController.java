package ru.otus.spring09books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring09books.services.LibraryServiceImpl;

/**
 * Класс LibraryController реализующий контроллер для библиотеки
 */
@Controller
public class LibraryController {
    private final LibraryServiceImpl libraryService;

    public LibraryController(LibraryServiceImpl libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String libraryPage(Model model) {
        model.addAttribute("welcomeString", libraryService.aboutLibrary());
        return "library";
    }

}