package ru.otus.spring16books.services;


import ru.otus.spring16books.domain.UserAccess;

/**
 * Интерфейс UserAccessService содержит методы для работы с пользователями
 *
 * @see ru.otus.spring16books.domain.UserAccess
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
