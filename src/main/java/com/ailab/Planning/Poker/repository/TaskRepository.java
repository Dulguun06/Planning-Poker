package com.ailab.Planning.Poker.repository;

import com.ailab.Planning.Poker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


//    @Query("select t  from Task as t where t.room_id = :id")
    List<Task> findAllByRoom_id(long roomId);

}
