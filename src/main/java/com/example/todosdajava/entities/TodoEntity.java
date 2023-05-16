package com.example.todosdajava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="todos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="label", nullable = false, unique = true)
    private String label;

    public TodoEntity(String label) {
        this.label = label;
    }
}
