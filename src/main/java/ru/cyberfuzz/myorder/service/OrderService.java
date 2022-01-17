package ru.cyberfuzz.myorder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.cyberfuzz.myorder.model.Food;
import ru.cyberfuzz.myorder.model.Order;
import ru.cyberfuzz.myorder.repository.OrderRepository;

import java.util.List;

/**
 * Класс OrderService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class OrderService {

    @Value("${url.food}")
    private String url;

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository,
                        RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }


    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public List<Food> findAllFoods() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Food>>() {
        }).getBody();
    }

    public Food getFoodByName(String foodName, Integer amount) {
        return restTemplate.getForObject(url + "/find?foodName=" + foodName
                + "&amount=" + amount, Food.class);
    }
}
