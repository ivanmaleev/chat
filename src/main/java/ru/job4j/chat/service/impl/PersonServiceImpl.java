package ru.job4j.chat.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.chat.entity.Person;
import ru.job4j.chat.repository.PersonRepository;
import ru.job4j.chat.service.PersonService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }
}
