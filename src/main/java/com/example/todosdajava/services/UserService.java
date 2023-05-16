package com.example.todosdajava.services;

import com.example.todosdajava.entities.UserEntity;
import com.example.todosdajava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
