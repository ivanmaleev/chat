package ru.job4j.chat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.chat.entity.Room;

import java.util.Collection;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    @Override
    Collection<Room> findAll();
}
