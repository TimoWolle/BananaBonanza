package de.bananabonanza.service;

import de.bananabonanza.dto.RegisterRequest;
import de.bananabonanza.entity.User;
import de.bananabonanza.exeption.AuthExeption;
import de.bananabonanza.respository.UserRepository;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private String loggedInUserId; // ID des aktuell angemeldeten Benutzers

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String username, String password) throws AuthenticationException {
        // Benutzer anhand des Benutzernamens aus der Datenbank abrufen
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AuthExeption("Invalid username or password"));

        // Überprüfen, ob das eingegebene Passwort mit dem in der Datenbank gespeicherten Passwort übereinstimmt
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthExeption("Invalid username or password");
        }

        // Angemeldeten Benutzer setzen
        loggedInUserId = user.getId().toString();

        // Token generieren und zurückgeben
        return generateToken(user);
    }

    public void register(RegisterRequest registerRequest) throws AuthenticationException {
        // Überprüfen, ob der Benutzername bereits existiert
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new AuthExeption("Username already exists");
        }

        // Passwort verschlüsseln
        String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());

        // Neuen Benutzer erstellen und in der Datenbank speichern
        User newUser = new User(registerRequest.getEmail(), encryptedPassword);
        newUser.setFirstname(registerRequest.getFirstname());
        newUser.setLastname(registerRequest.getLastname());
        userRepository.save(newUser);
    }

    public void logout() {
        // Angemeldeten Benutzer zurücksetzen
        loggedInUserId = null;
    }

    public void changePassword(String oldPassword, String newPassword) throws AuthenticationException {
        // Überprüfen, ob ein Benutzer angemeldet ist
        if (loggedInUserId == null) {
            throw new AuthExeption("User not logged in");
        }

        // Benutzer anhand der ID aus der Datenbank abrufen
        User user = userRepository.findById(Long.parseLong(loggedInUserId))
                .orElseThrow(() -> new AuthExeption("User not found"));

        // Überprüfen, ob das alte Passwort mit dem in der Datenbank gespeicherten Passwort übereinstimmt
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new AuthExeption("Invalid old password");
        }

        // Neues Passwort verschlüsseln und speichern
        user.setPasswort(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private String generateToken(User user) {

        return "generated-token";
    }
}
