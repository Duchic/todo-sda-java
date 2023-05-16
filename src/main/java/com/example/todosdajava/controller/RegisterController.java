package com.example.todosdajava.controller;

import com.example.todosdajava.requests.CreateUserRequest;
import com.example.todosdajava.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// POST, GET, PATCH, DELETE
// http://localhost:8080/api/v1/users [POST]
// http://localhost:8080/api/v1/users [GET]
// http://localhost:8080/api/v1/users{id} [GET]
// http://localhost:8080/api/v1/users{id} [DELETE]

@RestController
@RequestMapping("api/v1")
public class RegisterController {

    private final UserService userService;
    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@RequestBody @Valid CreateUserRequest userRequest) {
        try{
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
