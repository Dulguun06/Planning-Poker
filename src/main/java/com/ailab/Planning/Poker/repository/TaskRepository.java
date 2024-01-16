package com.ailab.Planning.Poker.repository;

import com.ailab.Planning.Poker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
