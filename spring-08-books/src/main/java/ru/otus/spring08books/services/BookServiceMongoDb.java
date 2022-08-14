package ru.otus.spring08books.services;

import org.springframework.stereotype.Service;
import ru.otus.spring08books.repositories.BookRepositoryMongoDb;

/**
 * Класс BookServiceMongoDb содержит методы для работы с репозиторием книг библиотеки
 *
 * @see BookRepositoryMongoDb
 */
@Service
public class BookServiceMongoDb implements BookService {

    /**
     * Метод createBook создает новую книгу (Crud)
     * Метод выполняет проверку на наличие книги в таблице book для исключения дубликатов.
     * В результат, возвращаемый методом, добавляется информация - данная книга была создан, или же он уже есть
     * в библиотеке
     * Метод изменяет данные
     *
     * @param title          (book.title)
     * @param authorFullName (author.fullName)
     * @param genreName      (genre.name)
     * @return
     */
    @Override
    public String createNewBook(String title, String authorFullName, String genreName) {
        return null;
    }

    /**
     * Метод getIdByBook возвращает id для книги, если она есть в библиотеке (cRud)
     * Поиск выполняется по названию, автору и жанру книги.
     * Метод не изменяет данные
     *
     * @param title
     * @param fullName
     * @param name
     * @return
     */
    @Override
    public String getIdByBook(String title, String fullName, String name) {
        return null;
    }

    /**
     * Метод getBookById возвращает книгу по ее id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Override
    public String getBookById(long id) {
        return null;
    }

    /**
     * Метод getAllBook возвращает все книги из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Override
    public String getAllBook() {
        return null;
    }

    /**
     * Метод updateBookById обновляет данные по книге: название, автора, жанр (crUd)
     * Перед изменением данных проверяется автор и жанр на наличие в справочниках для исключения дубликатов
     * Если id книги не найден, возвращается сообщение о неуспешном обновлении.
     * Метод изменяет данные
     *
     * @param id
     * @param title
     * @param authorFullName
     * @param genreName
     * @return
     */
    @Override
    public String updateBookById(long id, String title, String authorFullName, String genreName) {
        return null;
    }

    /**
     * Метод deleteBookById (cruD)
     * Перед удалением выполняется проверка на наличие книги с данным id в библиотеке,
     * если книга есть, то производится ее удаление, если нет - возвращается сообщение,
     * что книга не найдена.
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    @Override
    public String deleteBookById(long id) {
        return null;
    }
}
