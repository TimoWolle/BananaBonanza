package de.bananabonanza.exeption;

import org.springframework.security.core.AuthenticationException;

public class AuthExeption extends AuthenticationException {

    public AuthExeption(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthExeption(String msg) {
        super(msg);
    }
}
