package com.ailab.Planning.Poker.repository;

import com.ailab.Planning.Poker.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
