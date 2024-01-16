package com.ailab.Planning.Poker.repository;

import com.ailab.Planning.Poker.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
