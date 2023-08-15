package ru.otus.spring16books.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Класс ErrorController содержит методы обработки ошибок с детализацией наиболее частых
 */
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        var errorString = new StringBuilder();
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            errorString.append(statusCode);
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                errorString.append(" No access!");
            }
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorString.append(" Not found!");
            }
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorString.append(" Internal server error!");
            }
        } else {
            errorString.append("unknown");
        }
        model.addAttribute("errorString", "Error " + errorString);
        return "error";
    }
}
