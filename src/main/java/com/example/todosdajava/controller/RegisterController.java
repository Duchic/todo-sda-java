package com.example.todosdajava.controller;

import com.example.todosdajava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class RegisterController {

    private final UserService userService;
    @Autowired
    public RegisterController(UserService userService){
        this.userService = userService;
    }
}
