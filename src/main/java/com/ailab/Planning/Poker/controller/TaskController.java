package com.ailab.Planning.Poker.controller;

import com.ailab.Planning.Poker.dto.TaskDTO;
import com.ailab.Planning.Poker.services.TaskServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
//@CrossOrigin("http://localhost:5173/")
public class TaskController {

    @Autowired
    TaskServices taskServices;

    @GetMapping("")
    public ResponseEntity<List<TaskDTO>> getAll() {
        return new ResponseEntity<>(taskServices.getAllTask(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getByTaskId(@PathVariable Long id) {
        return new ResponseEntity<>(taskServices.getByTaskId(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> create(@Valid @RequestBody TaskDTO newTask) {
        return new ResponseEntity<>(taskServices.save(newTask), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@Valid @RequestBody final TaskDTO updatedTask, @PathVariable Long id) {
        return new ResponseEntity<>(taskServices.update(updatedTask, id), HttpStatus.OK);
    }

    @PutMapping("/addToRoom/{id}/{roomId}")
    public ResponseEntity<String> addToRoom(@PathVariable Long id, @PathVariable Long roomId, @RequestParam(name = "type") String type){
        return taskServices.addToRoom(roomId,id, type);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable final Long id) {
        return taskServices.delete(id);
    }
}