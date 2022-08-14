package ru.otus.spring08books.services;

import org.springframework.stereotype.Service;
import ru.otus.spring08books.entities.Author;
import ru.otus.spring08books.repositories.AuthorRepositoryMongoDb;

/**
 * Класс AuthorServiceMongoDb содержит методы для работы с репозиторием авторов библиотеки
 *
 * @see AuthorRepositoryMongoDb
 */
@Service
public class AuthorServiceMongoDb implements AuthorService {

    /**
     * Метод createNewAuthor создает нового автора в библиотеке (Crud)
     * Метод выполняет проверку на наличие добавляемого автора в таблице author для исключения дубликатов.
     * В результат, возвращаемый методом, добавляется информация - данный автор был создан, или же он уже есть
     * в справочнике авторов библиотеки
     * Метод изменяет данные
     *
     * @param fullName
     * @return
     */
    @Override
    public String createNewAuthor(String fullName) {
        return null;
    }

    /**
     * Метод getIdByAuthor возвращает информацию в текстовом виде о результатах поиска
     * автора с данным id в библиотеке
     * Метод не изменяет данные
     *
     * @param fullName
     * @return
     */
    @Override
    public String getIdByAuthor(String fullName) {
        return null;
    }

    /**
     * Метод getAuthorById получает данные об авторе по его id (cRud)
     * Метод не изменяет данные
     *
     * @param id
     * @return
     */
    @Override
    public String getAuthorById(long id) {
        return null;
    }

    /**
     * Метод getAllAuthors получает список всех авторов из библиотеки (cRud)
     * Метод не изменяет данные
     *
     * @return
     */
    @Override
    public String getAllAuthors() {
        return null;
    }

    /**
     * Метод updateAuthor обновляет данные об авторе в библиотеке (crUd)
     * Обновлению подлежит поле fullName для передаваемого в качестве аргумента id.
     * Перед выполнением запроса на изменение данных об авторе проверяется наличие автора с таким id,
     * если id был найден в таблице author, то изменяется поле fullName, иначе возвращается сообщение,
     * что автор не был обновлен
     * Метод изменяет данные
     *
     * @param id
     * @param fullName
     * @return
     */
    @Override
    public String updateAuthor(long id, String fullName) {
        return null;
    }

    /**
     * Метод deleteAuthorById удаляет данные об авторе в библиотеке (cruD)
     * Перед удалением выполняется проверка существования автора с таким id в таблице author
     * Метод изменяет данные
     *
     * @param id
     * @return
     */
    @Override
    public String deleteAuthorById(long id) {
        return null;
    }

    /**
     * Метод getFirstAuthorByFullName возвращает первого автора из списка
     * авторов с одинаковым значением поля fullName (cRud)
     *
     * @param authorFullName
     * @return
     */
    @Override
    public Author getFirstAuthorByFullName(String authorFullName) {
        return null;
    }
}
