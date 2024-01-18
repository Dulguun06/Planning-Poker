package com.ailab.Planning.Poker.mapper;

import com.ailab.Planning.Poker.dto.VoteDTO;
import com.ailab.Planning.Poker.entity.Task;
import com.ailab.Planning.Poker.entity.User;
import com.ailab.Planning.Poker.entity.Vote;
import com.ailab.Planning.Poker.services.TaskServices;
import com.ailab.Planning.Poker.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {
    @Autowired
    TaskServices taskServices;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserServices userServices;
    @Autowired
    UserMapper userMapper;

    public VoteDTO entityToDto(Vote vote) {
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setId(vote.getId());
        voteDTO.setTask_id(vote.getTask().getId());
        voteDTO.setUsername(vote.getUser().getUsername());
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
}