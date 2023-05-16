package com.example.todosdajava.services;

import com.example.todosdajava.entities.RoleEntity;
import com.example.todosdajava.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = this.userService.findUserByEmail(email);
        var roles = this.getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, roles);
    }

    private List<GrantedAuthority> getUserAuthority(Set<RoleEntity> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (RoleEntity roleEntity : userRoles) {
            roles.add(new SimpleGrantedAuthority(roleEntity.getName()));
        }

        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(UserEntity userEntity, List<GrantedAuthority> grantedAuthorities) {
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isActive(),
                true,
                true,
                true,
                grantedAuthorities
        );
    }
}
