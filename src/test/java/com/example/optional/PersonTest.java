package com.example.optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.optional.Person.Car;
import com.example.optional.Person.Insurance;

public class PersonTest {

    @Test
    public void testPerson_throwsNPE_whenNullIsPassed() {
        assertThrows(NullPointerException.class, () -> new Person(null));
    }

    @Test
    public void testPerson_notThrowsNPE_whenNullIsPassed() {
        assertDoesNotThrow(() -> new Person(null));
    }

    @Test
    public void testPerson_mapGet() {
        assertDoesNotThrow(() -> {
            Person person = new Person(new Car());
            Optional<Car> optCar = person.getCar();
            Optional<Insurance> optIns = optCar.flatMap(Car::getInsurance);
        });

    }
}
