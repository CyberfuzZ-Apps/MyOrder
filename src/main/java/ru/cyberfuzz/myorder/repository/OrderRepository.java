package ru.cyberfuzz.myorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cyberfuzz.myorder.model.Order;

import java.util.List;

/**
 * Класс OrderRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUsername(String username);

}
