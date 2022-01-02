package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.entity.Room;
import ru.job4j.chat.repository.RoomRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Collection<Room> findAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }
}
