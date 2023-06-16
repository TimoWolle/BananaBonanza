package de.bananabonanza.service;

import de.bananabonanza.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.getUserByEmail(email);
    }

}
