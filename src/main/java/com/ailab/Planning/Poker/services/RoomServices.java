package com.ailab.Planning.Poker.services;

import com.ailab.Planning.Poker.dto.RoomDTO;
import com.ailab.Planning.Poker.entity.Room;
import com.ailab.Planning.Poker.entity.Task;
import com.ailab.Planning.Poker.entity.User;
import com.ailab.Planning.Poker.mapper.RoomMapper;
import com.ailab.Planning.Poker.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServices {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    @Autowired
    private TaskServices taskServices;
    @Autowired
    private UserServices userServices;

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

    public Boolean checkPassword(Long roomId, String password) {
        Room room = roomRepository.findById(roomId).orElse(null);
        return room != null && room.getPassword().equals(password);

    }

    public RoomDTO save(Room newRoom) {
        roomRepository.save(newRoom);
        newRoom.setUrl("http://localhost:5173/room/" + newRoom.getId());
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

    public int count(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null)
            return room.getUsers().size();
        else return 0;
    }

    public HttpStatus delete(Long roomId) {
        if (roomRepository.existsById(roomId)) {
            roomRepository.deleteById(roomId);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    public List<Task> getTasks(long roomId) {
        return taskServices.getAllByRoomId(roomId);
    }

    public List<User> getUsers(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null)
            return room.getUsers();
        else return null;
    }

    public void addUser(Long roomId, String username) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            List<User> users = room.getUsers();
            if (users.size() < room.getCapacity()) {
                User user = userServices.getInfoByUserName(username);
                if (Objects.nonNull(user)) {
                    room.addUser(user);
                    roomRepository.save(room);
                }
            }
        }
    }

    public void removeUser(Long roomId, String username) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            User user = userServices.getInfoByUserName(username);
            if (Objects.nonNull(user)) {
                room.removeUser(user);
                roomRepository.save(room);
            }

        }
    }
}