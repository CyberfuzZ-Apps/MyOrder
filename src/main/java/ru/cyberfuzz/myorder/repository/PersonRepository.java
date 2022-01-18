package ru.cyberfuzz.myorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cyberfuzz.myorder.model.Person;

/**
 * Класс PersonRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByUsername(String username);

}
