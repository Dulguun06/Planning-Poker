package com.ailab.Planning.Poker.repository;

import com.ailab.Planning.Poker.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findAllByTaskId(Long taskId);
//    List<Vote> findAllByUsername(String username);
    @Query("SELECT DISTINCT v.taskId FROM Vote v WHERE v.username = :username")
    List<Long> findAllByUsername(@Param("username") String username);

    @Query("SELECT DISTINCT v.estimation FROM Vote v WHERE v.taskId = :taskId")
    List<Long> getEstimationByTaskId(@Param("taskId") Long taskId);
}
