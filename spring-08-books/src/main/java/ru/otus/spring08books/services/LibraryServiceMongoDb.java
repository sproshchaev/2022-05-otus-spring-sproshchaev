package ru.otus.spring08books.services;

import org.springframework.stereotype.Service;

/**
 * Класс LibraryServiceMongoDb содержит методы для работы библиотекой (как совокупный объект)
 */
@Service
public class LibraryServiceMongoDb implements LibraryService {

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Метод не изменяет данные
     *
     * @return
     */
    @Override
    public String aboutLibrary() {
        return null;
    }
}
