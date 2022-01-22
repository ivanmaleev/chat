package ru.job4j.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.common.Common;
import ru.job4j.chat.entity.Person;
import ru.job4j.chat.service.PersonService;
import ru.job4j.chat.validation.Operation;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Person> create(@RequestBody @Valid Person person) {
        return new ResponseEntity<>(
                this.personService.save(person),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        Optional<Person> personOptional = personService.findById(id);
        Person person = personOptional.orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account is not found. Please, check requisites."
                ));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PatchMapping("/")
    public Person patch(@RequestBody Person person)
            throws InvocationTargetException, IllegalAccessException {
        var currentPerson = personService.findById(person.getId()).orElse(null);
        Common.patch(person, currentPerson);
        personService.save(person);
        return currentPerson;
    }
}
