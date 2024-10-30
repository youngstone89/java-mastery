package com.example.optional;

import java.util.Optional;

/**
 * Person
 */
public class Person {

    // 선택형 값인 경우에만
    private Optional<Car> car;

    public Person(Car car) {
        // this.car = Optional.of(car);
        this.car = Optional.ofNullable(car);
    }

    public Optional<Car> getCar() {
        return car;
    }

    static class Car {
        private Optional<Insurance> insurance;

        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    static class Insurance {
        // we should throw NPE when name is empty so that we could find bug
        private String name;

        public String getName() {
            return name;
        }
    }
}