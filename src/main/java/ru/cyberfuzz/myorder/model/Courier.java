package ru.cyberfuzz.myorder.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Класс Courier
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Component
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Courier {

    private int id;
    private String name;
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Courier courier = (Courier) o;
        return id == courier.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
