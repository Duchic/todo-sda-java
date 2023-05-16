package com.example.todosdajava.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    @NotEmpty
    private String firstName;

    private String lastName;

    private String password;

    private String username;

    private String email;

    private boolean active;
}
