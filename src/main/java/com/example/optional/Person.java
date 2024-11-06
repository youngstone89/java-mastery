package com.example.optional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

        public Car(Insurance insurance) {
            this.insurance = Optional.ofNullable(insurance);
        }

        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    static class Insurance {
        // we should throw NPE when name is empty so that we could find bug
        private String name;

        public Insurance(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public static Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optIns -> optIns.map(Insurance::getName))
                .flatMap(Optional::stream) // remove empty optional
                .collect(Collectors.toSet());
    }

    public static Set<String> getCarInsuranceNamesWithoutOptionalStream(List<Person> persons) {
        return persons.stream()
                .map(p -> Optional.ofNullable(p.getCar())
                        .orElse(
                                Optional.ofNullable(
                                        new Car(null))))
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optIns -> optIns.map(Insurance::getName))
                // .filter(Optional::isPresent)
                .map(o -> o.orElse("null"))
                .peek(System.out::println)
                .collect(Collectors.toSet());
    }
}