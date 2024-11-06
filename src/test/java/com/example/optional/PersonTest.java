package com.example.optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.optional.Person.Car;
import com.example.optional.Person.Insurance;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public void testPerson_flatMapGetCar() {
        assertDoesNotThrow(() -> {
            Person person = new Person(new Car(new Insurance(null)));
            Optional<Person> optPerson = Optional.ofNullable(person);
            Optional<Car> optCar = optPerson.flatMap(Person::getCar);
            assertTrue(optCar.isPresent());
        });

    }

    @Test
    public void testPerson_assertThrowsNPE_whenFlatMapGetInsurance() {
        assertThrows(NullPointerException.class, () -> {
            Person person = new Person(new Car(new Insurance(null)));
            Optional<Person> optPerson = Optional.ofNullable(person);
            Optional<Car> optCar = optPerson.flatMap(Person::getCar);
            Optional<Insurance> x = optCar.flatMap(Car::getInsurance);
        });
    }

    @Test
    public void testPerson_assertNotThrowsNPE_whenFlatMapGetInsurance() {
        assertDoesNotThrow(() -> {
            Person person = new Person(new Car(new Insurance("abcd")));
            Optional<Person> optPerson = Optional.ofNullable(person);
            String name = Person.getCarInsuranceName(optPerson);
            log.info(name);
            assertEquals("abcd", name);
        });
    }

    @Test
    public void testPerson_assertNotThrowsNPEAndNameIsUnknown_whenFlatMapGetInsurance() {
        assertDoesNotThrow(() -> {
            Person person = new Person(new Car(null));
            Optional<Person> optPerson = Optional.ofNullable(person);
            String name = Person.getCarInsuranceName(optPerson);
            log.info(name);
            assertEquals("Unknown", name);
        });
    }

    @Test
    public void testPerson_assertNotThrowsNPEAndNameIsUnknown_whenOptPersonIsEmpty() {
        assertDoesNotThrow(() -> {
            Optional<Person> optPerson = Optional.empty();
            String name = Person.getCarInsuranceName(optPerson);
            log.info(name);
            assertEquals("Unknown", name);
        });
    }

    @Test
    public void testPerson_getOptionalString_whenMapOptionalInsurance() {
        assertDoesNotThrow(() -> {
            Optional<Insurance> optInsur = Optional.empty();
            Optional<String> optString = optInsur.map(Insurance::getName);
            Class<? extends Optional> x = optString.getClass();
            log.info(x.getName());
            log.info(x.getSimpleName());
            log.info(x.getTypeName());

            log.info(x.getCanonicalName());
        });
    }

    @Test
    public void testPerson_getCarInsuranceNames() {
        List<Person> persons = List.of(
                new Person(new Car(new Insurance("a"))),
                new Person(new Car(new Insurance("b"))),
                new Person(new Car(new Insurance("c"))));
        Set<String> x = Person.getCarInsuranceNames(persons);
        log.info(x.toString());
    }

    @Test
    public void testPerson_getCarInsuranceNamesWithoutOptionalStream() {
        List<Person> persons = List.of(
                new Person(new Car(null)),
                new Person(null),
                new Person(new Car(new Insurance("c"))));
        Set<String> x = Person.getCarInsuranceNamesWithoutOptionalStream(persons);
        log.info(x.toString());
    }

}
