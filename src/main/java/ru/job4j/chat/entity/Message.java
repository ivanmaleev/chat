package ru.job4j.chat.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @ManyToOne
    private Person author;
    @ManyToOne
    private Room room;
}
