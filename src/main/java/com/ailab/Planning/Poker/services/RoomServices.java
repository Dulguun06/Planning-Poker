package com.ailab.Planning.Poker.services;

import com.ailab.Planning.Poker.dto.RoomDTO;
import com.ailab.Planning.Poker.entity.Room;
import com.ailab.Planning.Poker.mapper.RoomMapper;
import com.ailab.Planning.Poker.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServices {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomServices(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public List<RoomDTO> getAllRoom() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public RoomDTO getByRoomId(Long roomId) {
        return roomMapper.entityToDto(roomRepository.findById(roomId).orElse(new Room()));
    }

    public ResponseEntity<String> checkPassword(Long roomId, String password) {
        Room room = roomRepository.findById(roomId).orElse(null);

        if (room != null && room.getPassword().equals(password)) {
            return new ResponseEntity<>("Password Correct", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
        }
    }

    public RoomDTO save(Room newRoom) {
        roomRepository.save(newRoom);
        return roomMapper.entityToDto(newRoom);
    }

    public RoomDTO update(RoomDTO newRoom, Long oldRoomId) {
        Optional<Room> optionalRoom = roomRepository.findById(oldRoomId);

        if (optionalRoom.isPresent()) {
            Room existingRoom = optionalRoom.get();
            roomMapper.dtoToEntity(newRoom, existingRoom);
            roomRepository.save(existingRoom);
            return roomMapper.entityToDto(existingRoom);
        } else {
            return null;
        }
    }

    public HttpStatus delete(Long roomId) {
        if (roomRepository.existsById(roomId)) {
            roomRepository.deleteById(roomId);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}