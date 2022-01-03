package ru.job4j.chat.entity;

import lombok.Data;
import ru.job4j.chat.validation.Operation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;
    @NotBlank(message = "Text must be not empty")
    private String text;
    @ManyToOne
    @NotNull(message = "Author must be non null")
    private Person author;
    @ManyToOne
    @NotNull(message = "Room must be non null")
    private Room room;
}
