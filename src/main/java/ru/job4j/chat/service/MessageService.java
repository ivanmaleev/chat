package ru.job4j.chat.service;

import ru.job4j.chat.entity.Message;

import java.util.Optional;

public interface MessageService {

    public Optional<Message> findById(int id);

    public Message save(Message message);

    public void delete(Message message);
}
