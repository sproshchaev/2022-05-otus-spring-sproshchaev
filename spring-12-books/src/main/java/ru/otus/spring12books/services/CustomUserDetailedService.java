package ru.otus.spring12books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring12books.domain.UserAccess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Класс CustomUserDetailedService реализует аутентификацию пользователей
 */
@Service
public class CustomUserDetailedService implements UserDetailsService {

    private final UserAccessServiceImpl userAccessServiceImpl;

    @Autowired
    public CustomUserDetailedService(UserAccessServiceImpl userAccessServiceImpl) {
        this.userAccessServiceImpl = userAccessServiceImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccess userAccess = userAccessServiceImpl.getUserAccess(username);
        if (userAccess == null) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        return new User(userAccess.getLogin(), userAccess.getPassword(), authorities);
    }

}
