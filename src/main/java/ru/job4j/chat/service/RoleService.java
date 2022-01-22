package ru.job4j.chat.service;

import ru.job4j.chat.entity.Role;

import java.util.Optional;

public interface RoleService {

    public Role save(Role role);

    public Optional<Role> findById(int id);
}
