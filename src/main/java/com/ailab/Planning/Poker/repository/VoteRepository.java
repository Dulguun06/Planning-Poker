package com.ailab.Planning.Poker.repository;

import com.ailab.Planning.Poker.entity.Task;
import com.ailab.Planning.Poker.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findAllByTaskId(Long taskId);
}
