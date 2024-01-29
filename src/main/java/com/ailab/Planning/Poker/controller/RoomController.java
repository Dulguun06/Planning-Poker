package com.ailab.Planning.Poker.controller;

import com.ailab.Planning.Poker.dto.RoomDTO;
import com.ailab.Planning.Poker.entity.Room;
import com.ailab.Planning.Poker.entity.Task;
import com.ailab.Planning.Poker.services.RoomServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    RoomServices roomServices;

    @GetMapping("")
    public ResponseEntity<List<RoomDTO>> getAll() {
        return new ResponseEntity<>(roomServices.getAllRoom(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getByRoomId(@PathVariable Long id) {
        return new ResponseEntity<>(roomServices.getByRoomId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> getTasks(@PathVariable Long id){
        return new ResponseEntity<>(roomServices.getTasks(id),HttpStatus.OK);
    }

    @PostMapping("/check-pass")
    public ResponseEntity<String> checkPassword(@RequestBody Map<String, Object> requestBody) {
        Long roomId = Long.parseLong(requestBody.get("roomId").toString());
        String password = requestBody.get("password").toString();
        if(roomServices.checkPassword(roomId, password))
            return ResponseEntity.ok("success");
        else return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomDTO> create(@Valid @RequestBody Room newRoom) {
        return new ResponseEntity<>(roomServices.save(newRoom), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@Valid @RequestBody final RoomDTO updatedRoom, @PathVariable Long id) {
        return new ResponseEntity<>(roomServices.update(updatedRoom, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable final Long id) {
        return roomServices.delete(id);
    }
}
