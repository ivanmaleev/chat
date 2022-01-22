package ru.job4j.chat.entity;

import lombok.Data;
import ru.job4j.chat.validation.Operation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;
    @NotBlank(message = "Login must be not empty")
    private String login;
    @NotBlank(message = "Password must be not empty")
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;
}