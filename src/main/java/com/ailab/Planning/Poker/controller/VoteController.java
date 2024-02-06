package com.ailab.Planning.Poker.controller;

import com.ailab.Planning.Poker.dto.VoteDTO;
import com.ailab.Planning.Poker.entity.Vote;
import com.ailab.Planning.Poker.services.VoteServices;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vote")
//@CrossOrigin("http://localhost:5173/")
public class VoteController {

    @Autowired
    VoteServices voteServices;

    private final Logger logger = LoggerFactory.getLogger(VoteController.class);

    @GetMapping("")
    public ResponseEntity<List<VoteDTO>> getAll() {
        return new ResponseEntity<>(voteServices.getAllVote(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteDTO> getByVoteId(@PathVariable Long id) {
        return new ResponseEntity<>(voteServices.getByVoteId(id), HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<List<Vote>> getByTaskId(@PathVariable Long id) {
        return new ResponseEntity<>(voteServices.getByTaskId(id), HttpStatus.OK);
    }

    @GetMapping("/voted/{username}")
    public ResponseEntity<List<Long>> getByTaskId(@PathVariable String username) {
        return new ResponseEntity<>(voteServices.getByUsername(username), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteDTO> save(@RequestBody Vote newVote) {
        return new ResponseEntity<>(voteServices.save(newVote), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoteDTO> updateVote(@Valid @RequestBody final VoteDTO updatedVote, @PathVariable Long id) {
        return new ResponseEntity<>(voteServices.update(updatedVote, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable final Long id) {
        return voteServices.delete(id);
    }
}