package com.ailab.Planning.Poker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {

    private Long id;

    @NotNull(message = "Title can't be null")
    @NotBlank(message = "Tasks should have title")
    private String title;

    @NotNull(message = "Description can't be null")
    @NotBlank(message = "Tasks should have detailed description")
    private String description;

   @JsonFormat(pattern = "yyyy-MM-dd")
    private Date due;

    private int estimation;

    private Long room_id;
}
