package com.ailab.Planning.Poker.mapper;

import com.ailab.Planning.Poker.dto.TaskDTO;
import com.ailab.Planning.Poker.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO entityToDto(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setUsername(task.getUsername());
        taskDTO.setDue(task.getDue());
        taskDTO.setEstimation(task.getEstimation());
        taskDTO.setRoom_id(task.getRoom_id());
        return taskDTO;
    }

    public void dtoToEntity(TaskDTO dto, Task task) {
        if (dto != null && task != null) {

            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setUsername(dto.getUsername());
            task.setDue(dto.getDue());
            task.setEstimation(dto.getEstimation());
            task.setRoom_id(dto.getRoom_id());
        }
    }
}