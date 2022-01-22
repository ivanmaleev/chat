package ru.job4j.chat.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.chat.entity.Room;
import ru.job4j.chat.repository.RoomRepository;
import ru.job4j.chat.service.RoomService;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

    @Override
    public Collection<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }
}
