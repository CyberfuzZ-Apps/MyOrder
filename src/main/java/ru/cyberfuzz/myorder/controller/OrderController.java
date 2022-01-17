package ru.cyberfuzz.myorder.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import ru.cyberfuzz.myorder.model.Food;
import ru.cyberfuzz.myorder.model.Order;
import ru.cyberfuzz.myorder.service.OrderService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    public Order saveOrder(@RequestBody Map<String, Integer> requestMap,
                           @ModelAttribute Order order) {
        order.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        int orderSum = 0;
        for (String name : requestMap.keySet()) {
            int amount = requestMap.get(name);
            Food food = orderService.getFoodByName(name, amount);
            order.addFood(food);
            orderSum += food.getSum();
        }
        order.setOrderSum(orderSum);

        /* TODO 17.01.2022 set username from Security  */

        return orderService.save(order);
    }

    @GetMapping("/allFoods")
    public List<Food> getAllFoods() {
        return orderService.findAllFoods();
    }
}
