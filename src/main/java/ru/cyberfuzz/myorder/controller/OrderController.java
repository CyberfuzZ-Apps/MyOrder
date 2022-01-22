package ru.cyberfuzz.myorder.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.cyberfuzz.myorder.model.Food;
import ru.cyberfuzz.myorder.model.Order;
import ru.cyberfuzz.myorder.model.Person;
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
    private final KafkaTemplate<Integer, Order> kafkaTemplate;

    public OrderController(OrderService orderService,
                           KafkaTemplate<Integer, Order> kafkaTemplate) {
        this.orderService = orderService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/{username}")
    public List<Order> findAllByUsername(@PathVariable String username) {
        return orderService.findByUsername(username);
    }

    @GetMapping("/findFood")
    public Food findFoodFromOrder(@RequestParam String name,
                                  @RequestParam Integer amount) {
        return orderService.getFoodByName(name, amount);
    }

    @PostMapping("/save")
    public Order saveOrder(@RequestBody Map<String, Integer> requestMap,
                           @ModelAttribute Order order,
                           @RequestParam String address) {
        order.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        int orderSum = 0;
        for (String name : requestMap.keySet()) {
            int amount = requestMap.get(name);
            Food food = orderService.getFoodByName(name, amount);
            order.addFood(food);
            orderSum += food.getSum();
        }
        order.setOrderSum(orderSum);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUsername(principal.toString());
        order.setAddress(address);
        Order savedOrder = orderService.save(order);
        //kafkaTemplate.send("order", savedOrder);
        return savedOrder;
    }

    @GetMapping("/allFoods")
    public List<Food> getAllFoods() {
        return orderService.findAllFoods();
    }
}
