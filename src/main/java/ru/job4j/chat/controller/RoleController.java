package ru.job4j.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.common.Common;
import ru.job4j.chat.entity.Role;
import ru.job4j.chat.service.RoleService;
import ru.job4j.chat.validation.Operation;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Role> create(@RequestBody @Valid Role role) {
        role = this.roleService.save(role);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentLength(role.toString().length())
                .body(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable int id) {
        Optional<Role> roleOptional = roleService.findById(id);
        Role role = roleOptional.orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Role is not found. Please, check requisites."
                ));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(role.toString().length())
                .body(role);
    }

    @PatchMapping("/")
    public Role patch(@RequestBody Role role)
            throws InvocationTargetException, IllegalAccessException {
        var currentRole = roleService.findById(role.getId()).orElse(null);
        Common.patch(role, currentRole);
        roleService.save(role);
        return currentRole;
    }
}
