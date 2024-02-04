package com.ailab.Planning.Poker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class VoteDTO {

    private Long id;

    @NotNull(message = "Task ID can't be null")
    private Long task_id;

    @NotNull(message = "Username can't be null")
    @NotBlank(message = "Username should have detailed description")
    private String username;

    private Long estimation;
}
