package ru.otus.spring08books.services;

/**
 * Интерфейс LibraryService содержит набор методов для работы с библиотекой
 */
public interface LibraryService {

    /**
     * Метод aboutLibrary выводит пользователю строку с приглашением и числом книг в библиотеке
     * Метод не изменяет данные
     *
     * @return
     */
    String aboutLibrary();
}