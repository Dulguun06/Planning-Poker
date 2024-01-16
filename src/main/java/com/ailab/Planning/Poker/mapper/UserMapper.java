package com.ailab.Planning.Poker.mapper;

import com.ailab.Planning.Poker.dto.UserDTO;
import com.ailab.Planning.Poker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO entityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setImage(user.getImage());
        return userDTO;
    }

    public void dtoToEntity(UserDTO userDTO, User user) {
        if (userDTO != null && user != null) {
            user.setUsername(userDTO.getUsername());
            user.setImage(userDTO.getImage());
        }
    }
}
