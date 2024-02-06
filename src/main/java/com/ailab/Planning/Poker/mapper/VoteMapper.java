package com.ailab.Planning.Poker.mapper;

import com.ailab.Planning.Poker.dto.VoteDTO;
import com.ailab.Planning.Poker.entity.Vote;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoteMapper {
    public VoteDTO entityToDto(Vote vote) {
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setId(vote.getId());
        voteDTO.setTask_id(vote.getTaskId());
        voteDTO.setUsername(vote.getUsername());
        voteDTO.setEstimation(vote.getEstimation());
        return voteDTO;
    }

    public void dtoToEntity(VoteDTO dto, Vote vote) {
        if (dto != null && vote != null) {
            vote.setTaskId(dto.getTask_id());
            vote.setUsername(dto.getUsername());
            vote.setEstimation(dto.getEstimation());
        }
    }

    public Long voteToTaskId(Vote vote){
        return vote.getTaskId();
    }
}