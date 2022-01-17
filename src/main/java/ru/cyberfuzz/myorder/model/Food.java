package ru.cyberfuzz.myorder.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

/**
 * Класс Food
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Embeddable
public class Food {

    private int id;
    private String name;
    private int price;
    private int amount;
    private int sum;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return id == food.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
