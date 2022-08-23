package com.example.springbootjwtauthorisation.service;

import com.example.springbootjwtauthorisation.model.Role;
import com.example.springbootjwtauthorisation.model.User;
import com.example.springbootjwtauthorisation.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional //Transactional is required when executing on update/delete query.

    public void changeRole(Role newRole, String username){
        userRepository.updateUserRole(username, newRole);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
