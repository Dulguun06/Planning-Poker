package com.ailab.Planning.Poker.repository;

import com.ailab.Planning.Poker.entity.Room;
import com.ailab.Planning.Poker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findById(long id);
}
