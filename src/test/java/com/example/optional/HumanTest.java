package com.example.optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class HumanTest {
    @Test
    public void givenOptional_whenFlatMapWorks_thenCorrect2() {
        Human person = Human.builder().age(1).name("john").build();
        Optional<Human> personOptional = Optional.of(person);

        Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Human::getName);
        Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
        String name1 = nameOptional.orElse("");
        assertEquals("john", name1);

        String name = personOptional
                .flatMap(Human::getName)
                .orElse("");
        assertEquals("john", name);
    }
}
