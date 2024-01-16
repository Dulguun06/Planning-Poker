package com.ailab.Planning.Poker.services;

import com.ailab.Planning.Poker.entity.User;
import com.ailab.Planning.Poker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findById(username);
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(String username) {
        userRepository.deleteById(username);
    }
}