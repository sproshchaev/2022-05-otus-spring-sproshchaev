package ru.otus.spring10books.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring10books.services.LibraryService;

/**
 * Класс LibraryController реализующий контроллер, осуществляющий загрузку базовой html-страницы приложения
 */
@Controller
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    @GetMapping("/")
    public String libraryPage(Model model) {
        model.addAttribute("welcomeString", libraryService.aboutLibrary());
        return "library";
    }

}