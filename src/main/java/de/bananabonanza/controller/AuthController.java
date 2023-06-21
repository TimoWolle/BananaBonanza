package de.bananabonanza.controller;

import de.bananabonanza.dto.ChangePasswordRequest;
import de.bananabonanza.dto.LoginRequest;
import de.bananabonanza.dto.RegisterRequest;
import de.bananabonanza.entity.LoginResponse;
import de.bananabonanza.exeption.AuthExeption;
import de.bananabonanza.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Benutzeranmelde-Logik
            return ResponseEntity.ok(new LoginResponse(authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword()), "Login successful"));
        } catch (AuthExeption e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // Benutzerregistrierungs-Logik
            authenticationService.register(registerRequest);
            return ResponseEntity.ok("Registration successful");
        } catch (AuthExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Abmeldelogik
        authenticationService.logout();
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            // Passwortänderungslogik
            authenticationService.changePassword(changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());
            return ResponseEntity.ok("Password changed successfully");
        } catch (AuthExeption e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
