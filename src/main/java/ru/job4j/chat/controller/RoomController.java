package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.entity.Room;
import ru.job4j.chat.service.RoomService;
import ru.job4j.chat.validation.Operation;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public Collection<Room> findAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        Optional<Room> roomOptional = roomService.findById(id);
        Room room = roomOptional.orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account is not found. Please, check requisites."
                ));
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Room> create(@RequestBody @Valid Room room) {
        return new ResponseEntity<>(
                this.roomService.save(room),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody @Valid Room room) {
        roomService.save(room);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Room room = new Room();
        room.setId(id);
        roomService.delete(room);
        return ResponseEntity.ok().build();
    }
}
