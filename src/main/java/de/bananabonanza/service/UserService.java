package de.bananabonanza.service;

import de.bananabonanza.entity.User;
import de.bananabonanza.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User saveUser(User user) {
        user.setPasswort(passwordEncoder.encode(user.getPasswort()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with the id " + id + " not found."));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User updateUser(User _user) {
        User existingUser = getUserById(_user.getId());

        existingUser.setFirstname(_user.getFirstname());
        existingUser.setLastname(_user.getLastname());
        existingUser.setEmail(_user.getEmail());
        existingUser.setAddresses(_user.getAddresses());

        // only update the password if it has been changed
        if (!existingUser.getPasswort().equals(_user.getPasswort())) {
            existingUser.setPasswort(passwordEncoder.encode(_user.getPasswort()));
        }

        existingUser.setShoppingCart(_user.getShoppingCart());
        existingUser.setWishlist(_user.getWishlist());
        existingUser.setSaveforlaterlist(_user.getSaveforlaterlist());
        existingUser.setPayments(_user.getPayments());

        return userRepository.save(existingUser);
    }
}
