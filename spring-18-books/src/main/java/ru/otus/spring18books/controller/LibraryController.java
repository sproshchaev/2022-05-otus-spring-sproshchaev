package ru.otus.spring18books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring18books.services.LibraryService;

/**
 * Класс LibraryController реализующий контроллер для библиотеки
 */
@Controller
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String libraryPage(Model model) {
        model.addAttribute("welcomeString", libraryService.aboutLibrary());
        return "library";
    }

}