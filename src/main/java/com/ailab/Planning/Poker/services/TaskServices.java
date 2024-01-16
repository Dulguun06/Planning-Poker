package com.ailab.Planning.Poker.services;

import com.ailab.Planning.Poker.dto.TaskDTO;
import com.ailab.Planning.Poker.entity.Task;
import com.ailab.Planning.Poker.mapper.TaskMapper;
import com.ailab.Planning.Poker.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServices {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServices(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskDTO> getAllTask() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public TaskDTO getByTaskId(Long taskId) {
        return taskMapper.entityToDto(taskRepository.findById(taskId).orElse(new Task()));
    }

    public TaskDTO save(TaskDTO newTask) {
        Task task = new Task();
        taskMapper.dtoToEntity(newTask, task);
        return taskMapper.entityToDto(taskRepository.save(task));
    }

    public TaskDTO update(TaskDTO newTask, Long oldTaskId) {
        Optional<Task> optionalTask = taskRepository.findById(oldTaskId);

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            taskMapper.dtoToEntity(newTask, existingTask);
            taskRepository.save(existingTask);
            return taskMapper.entityToDto(existingTask);
        } else {
            return null;
        }
    }

    public HttpStatus delete(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}