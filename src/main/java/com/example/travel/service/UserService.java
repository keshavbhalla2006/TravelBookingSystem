package com.example.travel.service;

import com.example.travel.model.User;
import com.example.travel.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Find by id
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Find by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Create or update user
    public User save(User user) {
        return userRepository.save(user);
    }

    // Delete user by id
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    // Ensure a user exists by email (used for OAuth2)
    public User getOrCreateUser(User user) {
        return userRepository.findByEmail(user.getEmail())
                .orElseGet(() -> userRepository.save(user));
    }
}
