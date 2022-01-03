package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.entity.Role;
import ru.job4j.chat.entity.Room;
import ru.job4j.chat.service.RoleService;

import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        if (role.getName() == null) {
            throw new NullPointerException("Role name mustn't be empty");
        }
        return new ResponseEntity<>(
                this.roleService.save(role),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable int id) {
        Optional<Role> roleOptional = roleService.findById(id);
        Role role = roleOptional.orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Role is not found. Please, check requisites."
                ));
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
