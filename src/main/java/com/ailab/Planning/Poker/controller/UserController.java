package com.ailab.Planning.Poker.controller;

import com.ailab.Planning.Poker.dto.UserDTO;
import com.ailab.Planning.Poker.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin("http://localhost:5173/")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userServices.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getByUserName(@PathVariable String username) {
        return new ResponseEntity<>(userServices.getByUserName(username), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO newUser) {
        return new ResponseEntity<>(userServices.save(newUser), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody final UserDTO updatedUser, @PathVariable String username) {
        return new ResponseEntity<>(userServices.update(updatedUser, username), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public HttpStatus delete(@PathVariable final String username) {
        return userServices.delete(username);
    }
}

