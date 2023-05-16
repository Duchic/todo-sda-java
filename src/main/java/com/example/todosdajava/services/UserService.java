package com.example.todosdajava.services;

import com.example.todosdajava.entities.UserEntity;
import com.example.todosdajava.repositories.RoleRepository;
import com.example.todosdajava.repositories.UserRepository;
import com.example.todosdajava.requests.CreateUserRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Transactional(rollbackOn = Exception.class)
    public UserEntity create(CreateUserRequest userRequest) {
        try {
            var userEntity = new UserEntity(
                    userRequest.getEmail(),
                    userRequest.getUsername(),
                    userRequest.getFirstName(),
                    userRequest.getLastName(),
                    passwordEncoder.encode(userRequest.getPassword()),
                    true
            );

            var role = roleRepository.findByName("USER");
            userEntity.setRoles(new HashSet<>(Collections.singletonList(role)));

            return this.userRepository.save(userEntity);
        } catch (Exception e) {
            // @TODO odchyceni vyjimky
            throw new Error(e.getMessage());
        }
    }
}
