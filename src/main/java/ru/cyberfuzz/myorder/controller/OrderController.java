package ru.cyberfuzz.myorder.controller;

import org.springframework.web.bind.annotation.*;
import ru.cyberfuzz.myorder.model.Food;
import ru.cyberfuzz.myorder.model.Order;
import ru.cyberfuzz.myorder.service.OrderService;

import java.util.List;

/**
 * Класс OrderController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{username}")
    public List<Order> findAllByUsername(@PathVariable String username) {
        return orderService.findByUsername(username);
    }

    @PostMapping("/save")
    public Order saveOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping("/allFoods")
    public List<Food> getAllFoods() {
        return orderService.findAllFoods();
    }
}
