package ru.otus.spring16books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring16books.domain.UserAccess;
import ru.otus.spring16books.repositories.UserAccessRepository;

import java.util.List;

/**
 * Класс UserAccessServiceImpl содержит методы, реализующие интерфейс работы с аутентификацией пользователей
 *
 * @see ru.otus.spring16books.domain.UserAccess
 */
@Service
public class UserAccessServiceImpl implements UserAccessService {

    private final UserAccessRepository userAccessRepository;

    @Autowired
    public UserAccessServiceImpl(UserAccessRepository userAccessRepository) {
        this.userAccessRepository = userAccessRepository;
    }

    /**
     * Метод getUserAccess возвращает информацию о доступах пользователя
     *
     * @param login
     * @return
     */
    @Override
    public UserAccess getUserAccess(String login) {
        List<UserAccess> userAccessList = userAccessRepository.findByLogin(login);
        return userAccessList.size() == 1 ? userAccessList.get(0) : null;
    }
}
