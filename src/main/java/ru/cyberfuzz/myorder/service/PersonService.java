package ru.cyberfuzz.myorder.service;

import org.springframework.stereotype.Service;
import ru.cyberfuzz.myorder.model.Person;
import ru.cyberfuzz.myorder.repository.PersonRepository;

/**
 * Класс PersonService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }
}
