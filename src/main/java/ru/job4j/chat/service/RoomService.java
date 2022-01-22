package ru.job4j.chat.service;

import ru.job4j.chat.entity.Room;

import java.util.Collection;
import java.util.Optional;

public interface RoomService {

    public Collection<Room> findAll();

    public Optional<Room> findById(int id);

    public Room save(Room room);

    public void delete(Room room);
}
