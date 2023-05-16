package com.example.todosdajava.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="active", nullable = false)
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<RoleEntity> roles;
}
