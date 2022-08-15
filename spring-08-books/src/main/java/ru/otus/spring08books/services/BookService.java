package ru.otus.spring08books.services;

/**
 * Интерфейс BookService содержит набор методов для работы с репозиторием сущности Book
 *
 * @see ru.otus.spring08books.entities.Book
 */
interface BookService {

    /**
     * Метод createBook создает новую книгу (Crud)
     * Метод изменяет данные
     *
     * @param title          (book.title)
     * @param authorFullName (author.fullName)
     * @param genreName      (genre.name)
     * @return
     */
    String createNewBook(String title, String authorFullName, String genreName);

    /**
     * Метод getIdByBook возвращает id для книги, если она есть в библиотеке (cRud)
     * Метод не изменяет данные
     *
     * @param title
     * @param fullName
     * @param name
     * @return
     */
    String getIdByBook(String title, String fullName, String name);

    /**
     * Метод getBookById возвращает книгу по ее id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    String getBookById(String id);

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    String getAllBook();

    /**
     * Метод updateBookById обновляет данные по книге: название, автора, жанр (crUd)
     * Метод изменяет данные
     *
     * @param id
     * @param title
     * @param authorFullName
     * @param genreName
     * @return
     */
    String updateBookById(String id, String title, String authorFullName, String genreName);

    /**
     * Метод deleteBookById (cruD)
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    String deleteBookById(String id);
}
