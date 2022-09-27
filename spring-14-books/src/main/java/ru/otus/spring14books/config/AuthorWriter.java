package ru.otus.spring14books.config;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import ru.otus.spring14books.nosql.domain.Author;

import java.util.List;

@Component
public class AuthorWriter implements ItemWriter<Author> {
    @Override
    public void write(List<? extends Author> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Элемент: " + list.get(i));
        }
    }
}
