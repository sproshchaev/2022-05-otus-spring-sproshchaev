package ru.otus.pojo;

import org.springframework.stereotype.Component;

/**
 * Класс Student - POJO-класс, идентифицирующий имя тестируемого
 */
@Component
public class Student {
    private String name;

    /**
     * Конструктор класса
     */
    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
