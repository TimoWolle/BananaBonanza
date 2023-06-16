package de.bananabonanza.service;

import de.bananabonanza.entity.User;
import de.bananabonanza.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        user.setPasswort(passwordEncoder.encode(user.getPasswort()));
        return userRepository.save(user);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(User updatedUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setAddresses(updatedUser.getAddresses());
                    user.setWishlist(updatedUser.getWishlist());
                    user.setShoppingCart(updatedUser.getShoppingCart());
                    user.setEmail(updatedUser.getEmail());
                    user.setFirstname(updatedUser.getFirstname());
                    user.setLastname(updatedUser.getLastname());
                    user.setPasswort(updatedUser.getPasswort());
                    user.setSaveforlaterlist(updatedUser.getSaveforlaterlist());
                    user.setPayments(updatedUser.getPayments());

                    if (!user.getPasswort().equals(updatedUser.getPasswort())) {
                        user.setPasswort(passwordEncoder.encode(updatedUser.getPasswort()));
                    }

                    userRepository.save(user);
                    return user;
                });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
