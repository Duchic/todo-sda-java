package com.example.todosdajava.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class TodoCreateRequest {
    @NotEmpty(message = "Label is mandatory")
    @Size(min = 2, max = 200, message = "Label must be between 2 and 200 characters")
    private String label;
}
