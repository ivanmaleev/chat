package ru.job4j.chat.entity;

import lombok.Data;
import ru.job4j.chat.validation.Operation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;
    @NotBlank(message = "Login must be not empty")
    private String name;
    private String description;
    @ManyToOne
    @NotNull(message = "Author must be non null")
    private Person author;
}
