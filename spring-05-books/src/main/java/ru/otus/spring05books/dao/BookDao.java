package ru.otus.spring05books.dao;

/**
 *
 */
public interface BookDao {

    /**
     * Создать книгу
     */
    void createBook();

    /**
     * Обновить книгу
     */
    void updateBook();

    /**
     * Удалить книгу
     */
    void deleteBook();

    /**
     * Получить книгу по Id
     */
    String getBookById();

    /**
     * Получить все книги
     * @return
     */
    String getAllBooks();

}
