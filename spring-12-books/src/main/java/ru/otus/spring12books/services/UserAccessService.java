package ru.otus.spring12books.services;


import ru.otus.spring12books.domain.UserAccess;

/**
 * Интерфейс UserAccessService содержит методы для работы с пользователями
 *
 * @see ru.otus.spring12books.domain.UserAccess
 */
public interface UserAccessService {

    /**
     * Метод getUserAccess возвращает информацию о доступах пользователя
     *
     * @param login
     * @return
     */
    UserAccess getUserAccess(String login);

}
