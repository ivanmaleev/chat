package ru.job4j.chat.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.chat.entity.Message;
import ru.job4j.chat.repository.MessageRepository;
import ru.job4j.chat.service.MessageService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Override
    public Optional<Message> findById(int id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }
}
