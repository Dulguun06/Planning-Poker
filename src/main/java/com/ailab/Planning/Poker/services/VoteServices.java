package com.ailab.Planning.Poker.services;

import com.ailab.Planning.Poker.dto.TaskDTO;
import com.ailab.Planning.Poker.dto.VoteDTO;
import com.ailab.Planning.Poker.entity.Vote;
import com.ailab.Planning.Poker.mapper.VoteMapper;
import com.ailab.Planning.Poker.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoteServices {
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    @Autowired
    TaskServices taskServices;

    public VoteServices(VoteRepository voteRepository, VoteMapper voteMapper) {
        this.voteRepository = voteRepository;
        this.voteMapper = voteMapper;
    }

    public List<VoteDTO> getAllVote() {
        return voteRepository.findAll()
                .stream()
                .map(voteMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public VoteDTO getByVoteId(Long voteId) {
        return voteMapper.entityToDto(voteRepository.findById(voteId).orElse(new Vote()));
    }

    public VoteDTO save(VoteDTO newVote) {
        Vote vote = new Vote();
        voteMapper.dtoToEntity(newVote, vote);
        vote = voteRepository.save(vote);
        newVote.setId(vote.getId());
        return newVote;
    }


    public VoteDTO update(VoteDTO newVote, Long oldVoteId) {
        Optional<Vote> optionalVote = voteRepository.findById(oldVoteId);

        if (optionalVote.isPresent()) {
            Vote existingVote = optionalVote.get();
            voteMapper.dtoToEntity(newVote, existingVote);
            voteRepository.save(existingVote);
            return voteMapper.entityToDto(existingVote);
        } else {
            return null;
        }
    }

    public HttpStatus delete(Long voteId) {
        if (voteRepository.existsById(voteId)) {
            voteRepository.deleteById(voteId);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}