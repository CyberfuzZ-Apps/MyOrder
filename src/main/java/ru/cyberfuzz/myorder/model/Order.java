package ru.cyberfuzz.myorder.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Класс Order
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private Timestamp created;
    private int orderSum;

    @ElementCollection
    private Collection<Food> foods = new ArrayList<>();

    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
