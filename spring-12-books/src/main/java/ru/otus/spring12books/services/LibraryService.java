package ru.otus.spring12books.services;

/**
 * Интерфейс LibraryService содержит методы для работы с библиотекой
 */
public interface LibraryService {

    /**
     * Метод возвращает информацию о библиотеке, книгах, авторах, жанрах и комментариях
     * @return
     */
    String aboutLibrary();

}