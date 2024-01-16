package com.ailab.Planning.Poker.services;

import com.ailab.Planning.Poker.dto.UserDTO;
import com.ailab.Planning.Poker.entity.User;
import com.ailab.Planning.Poker.mapper.UserMapper;
import com.ailab.Planning.Poker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public UserDTO getByUserName(String userName) {
        return userMapper.entityToDto(userRepository.findById(userName).orElse(new User()));
    }

    public UserDTO save(UserDTO newUser) {
        User user = new User();
        userMapper.dtoToEntity(newUser, user);
        return userMapper.entityToDto(userRepository.save(user));
    }

    public UserDTO update(UserDTO newUser, String oldUsername) {
        Optional<User> optionalUser = userRepository.findById(oldUsername);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            userMapper.dtoToEntity(newUser, existingUser);
            userRepository.save(existingUser);
            return userMapper.entityToDto(existingUser);
        } else {
            return null;
        }
    }

    public HttpStatus delete(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}