package ru.cyberfuzz.myorder.model;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.cyberfuzz.myorder.status.DeliveryStatus;

import java.util.Objects;

/**
 * Класс Delivery
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Component
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Delivery {


    private int id;
    private DeliveryStatus status;
    private Courier courier;
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Delivery delivery = (Delivery) o;
        return id == delivery.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
