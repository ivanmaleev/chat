package ru.job4j.chat.service;

import ru.job4j.chat.entity.Person;
import java.util.Optional;

public interface PersonService {

    public Person save(Person person);

    public Optional<Person> findById(int id);
}
