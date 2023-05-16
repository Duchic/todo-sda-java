package com.example.todosdajava.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    public long id;

    @Column(name="name", unique = true, nullable = false)
    public String name;

    public RoleEntity(String name) {
        this.name = name;
    }
}
