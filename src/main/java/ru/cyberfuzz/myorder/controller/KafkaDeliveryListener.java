package ru.cyberfuzz.myorder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.cyberfuzz.myorder.model.Delivery;

/**
 * Класс KafkaDeliveryListener
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Component
public class KafkaDeliveryListener {

    private final ObjectMapper objectMapper;

    public KafkaDeliveryListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = {"delivery"})
    public void listenDelivery(ConsumerRecord<Integer, String> input) {
        try {
            Delivery delivery = objectMapper.readValue(input.value(), Delivery.class);
            System.out.println(delivery);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
