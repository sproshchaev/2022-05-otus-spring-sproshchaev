package ru.otus.spring18books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring18books.domain.UserAccess;

import java.util.List;

/**
 * Интерфейс UserAccessRepository содержит методы работы с доступами пользователей
 *
 * @see ru.otus.spring18books.domain.UserAccess
 */
@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {
    List<UserAccess> findByLogin(String login);
}
