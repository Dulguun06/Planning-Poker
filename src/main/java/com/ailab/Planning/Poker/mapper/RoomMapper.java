package com.ailab.Planning.Poker.mapper;

import com.ailab.Planning.Poker.dto.RoomDTO;
import com.ailab.Planning.Poker.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomDTO entityToDto(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoom_name(room.getRoom_name());
        roomDTO.setUrl(room.getUrl());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setJoinedUsers(room.getUsers().size());
        return roomDTO;
    }

    public void dtoToEntity(RoomDTO dto, Room room) {
        if (dto != null && room != null) {
            room.setRoom_name(dto.getRoom_name());
            room.setUrl(dto.getUrl());
            room.setCapacity(dto.getCapacity());
        }
    }
}