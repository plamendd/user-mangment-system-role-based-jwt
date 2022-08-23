package com.example.springbootjwtauthorisation.service;

import com.example.springbootjwtauthorisation.model.Role;
import com.example.springbootjwtauthorisation.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);

    List<User> findAllUsers();
}
