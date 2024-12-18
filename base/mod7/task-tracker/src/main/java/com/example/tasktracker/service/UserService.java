package com.example.tasktracker.service;

import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<User> updateUser(String id, User updatedUser) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    if (updatedUser.getUsername() != null) {
                        existingUser.setUsername(updatedUser.getUsername());
                    }
                    if (updatedUser.getEmail() != null) {
                        existingUser.setEmail(updatedUser.getEmail());
                    }
                    return userRepository.save(existingUser);
                });
    }

    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }
}
