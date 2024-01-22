package com.ailab.Planning.Poker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomDTO {

    private Long id;

    @NotNull(message = "Room name can't be null")
    @NotBlank(message = "Room name should have detailed description")
    private String room_name;

    private String url;
}
