package com.example.todosdajava.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class TodoUpdateRequest {
    //    @Size(min=1)
    @Positive(message="Incorrect id")
    @NotNull(message = "Label is mandatory")
    private long id;

    @NotEmpty(message = "Label is mandatory")
    private String label;
}
