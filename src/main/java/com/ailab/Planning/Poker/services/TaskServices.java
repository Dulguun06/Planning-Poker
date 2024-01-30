package com.ailab.Planning.Poker.services;

import com.ailab.Planning.Poker.dto.TaskDTO;
import com.ailab.Planning.Poker.entity.Task;
import com.ailab.Planning.Poker.mapper.TaskMapper;
import com.ailab.Planning.Poker.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        return taskRepository.findAll().stream().map(taskMapper::entityToDto).collect(Collectors.toList());
    }

    public List<TaskDTO> getAddableTask() {
        return taskRepository.findAllByRoom_id(null).stream().map(taskMapper::entityToDto).collect(Collectors.toList());
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
        Task optionaltask = taskRepository.findById(oldTaskId).orElse(null);
        if (!Objects.isNull(optionaltask)) {
            taskMapper.dtoToEntity(newTask, optionaltask);
            taskRepository.save(optionaltask);
            return newTask;
        }
        return null;

    }

    public HttpStatus delete(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    public List<Task> getAllByRoomId(Long roomId) {
        return taskRepository.findAllByRoom_id(roomId);
    }

    public ResponseEntity<String> addToRoom(Long roomId, Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return new ResponseEntity<>("No task with " + id + " id like this", HttpStatus.NOT_FOUND);
        } else if(task.getRoom_id()!=null){
            return new ResponseEntity<>("The task belongs to another room.", HttpStatus.FORBIDDEN);
        }
        else {
            task.setRoom_id(roomId);
            taskRepository.save(task);
            return new ResponseEntity<>("Added", HttpStatus.OK);
        }
    }

    public ResponseEntity<String> removeFromRoom(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setRoom_id(null);
            taskRepository.save(task);
            return new ResponseEntity<>("Removed", HttpStatus.OK);
        } else return new ResponseEntity<>("Failed to Remove", HttpStatus.NOT_FOUND);
    }

}