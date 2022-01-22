package ru.job4j.chat.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.chat.entity.Role;
import ru.job4j.chat.repository.RoleRepository;
import ru.job4j.chat.service.RoleService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(int id) {
        return roleRepository.findById(id);
    }
}
