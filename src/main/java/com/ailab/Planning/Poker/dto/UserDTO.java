package com.ailab.Planning.Poker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "Username not blank")
    @NotNull(message = "Username not null")
    private String username;
}
