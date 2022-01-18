package ru.cyberfuzz.myorder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cyberfuzz.myorder.model.Person;
import ru.cyberfuzz.myorder.service.PersonService;

/**
 * Класс PesonController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@RestController
@RequestMapping("/person")
public class PesonController {

    private final PersonService personService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PesonController(PersonService personService,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personService = personService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> signUp(@RequestBody Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        Person savedPerson = personService.save(person);
        return new ResponseEntity<>(
                savedPerson,
                savedPerson != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST
        );
    }
}
