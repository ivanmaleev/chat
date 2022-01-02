package ru.job4j.chat.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @ManyToOne
    private Person author;
}
