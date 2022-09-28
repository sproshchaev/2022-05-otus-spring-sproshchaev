package ru.otus.spring14books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring14books.nosql.domain.Book;
import ru.otus.spring14books.nosql.services.BookService;

import java.util.List;

/**
 * Класс BookWriterImpl реализует методы интерфейса BookWriter
 */
@Component
public class BookWriterImpl implements BookWriter {

    private final BookService bookService;

    @Autowired
    public BookWriterImpl(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Метод write обрабатывает записи сущности Author пачкой (в отличие от райтера и процессора,
     * которые обрабатывают по одной)
     *
     * @param list
     * @throws Exception
     */
    @Override
    public void write(List<? extends Book> list) {

        for (int i = 0; i < list.size(); i++) {

            System.out.println((i + 1) + ")Book:" + list.get(i).getId() + ":");
            System.out.println("  - title:" + list.get(i).getTitle());
            System.out.println("  - author:" + list.get(i).getAuthor().getId() + " " + list.get(i).getAuthor().getFullName());
            System.out.println("  - genre:" + list.get(i).getGenre().getId() + " " + list.get(i).getGenre().getName()); // todo убрать

            if (bookService.getIdByBook(list.get(i).getTitle(), list.get(i).getAuthor().getFullName(),
                    list.get(i).getGenre().getName()).equals("")) {
                bookService.createBook(list.get(i));
                System.out.println("Элемент: " + list.get(i).getId() + " " + list.get(i).getTitle()
                        + " " + list.get(i).getAuthor().getFullName()
                        + " " + list.get(i).getGenre().getName()); // todo убрать
            }
        }
    }
}
