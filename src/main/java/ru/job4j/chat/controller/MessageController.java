package ru.job4j.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.common.Common;
import ru.job4j.chat.entity.Message;
import ru.job4j.chat.service.MessageService;
import ru.job4j.chat.validation.Operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.
            getLogger(UserController.class.getSimpleName());

    private MessageService messageService;
    private final ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        Optional<Message> messageOptional = messageService.findById(id);
        Message message = messageOptional.orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Message is not found. Please, check requisites."
                ));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Message> create(@RequestBody @Valid Message message) {
        return new ResponseEntity<>(
                this.messageService.save(message),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody @Valid Message message) {
        messageService.save(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Message message = new Message();
        message.setId(id);
        messageService.delete(message);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/")
    public Message patch(@RequestBody Message message)
            throws InvocationTargetException, IllegalAccessException {
        var currentMessage = messageService.findById(message.getId()).orElse(null);
        Common.patch(message, currentMessage);
        messageService.save(message);
        return currentMessage;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request,
                                 HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpStatus.NO_CONTENT.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        LOGGER.error(e.getLocalizedMessage());
    }
}
