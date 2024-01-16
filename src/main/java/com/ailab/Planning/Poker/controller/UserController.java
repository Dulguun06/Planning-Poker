package com.ailab.Planning.Poker.controller;

import com.ailab.Planning.Poker.entity.User;
import com.ailab.Planning.Poker.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:5173/")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userServices.getAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable final String username) {
        Optional<User> user = userServices.getByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        }
    }

    @PostMapping(path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody final User newUser) {
        User user = userServices.save(newUser);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @PutMapping("users/{username}")
    public ResponseEntity<User> update(@RequestBody final User updatedUser) {
        User user = userServices.save(updatedUser);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @DeleteMapping("/users/{username}")
    public HttpStatus delete(@PathVariable final String username) {
        try {
            userServices.delete(username);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND (CODE 404)\n");
        }
    }
}

