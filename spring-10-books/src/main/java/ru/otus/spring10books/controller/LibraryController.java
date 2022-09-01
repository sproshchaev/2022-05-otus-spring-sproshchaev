package ru.otus.spring10books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс LibraryController реализующий контроллер для библиотеки
 */
@Controller
public class LibraryController {

    @GetMapping("/")
    public String libraryPage(Model model) {
        model.addAttribute("welcomeString", "123");
        return "library";
    }

}