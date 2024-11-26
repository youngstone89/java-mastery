package com.example.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

import org.junit.jupiter.api.Test;

public class InstantExample {

    @Test
    void testName() {
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        Instant.ofEpochSecond(2, 1_000_000_000);

    }

    @Test
    void testDuration() {
        Duration d1 = Duration.between(LocalTime.of(10, 00), LocalTime.of(10, 10));
        System.out.println(d1.getSeconds());

        Duration d2 = Duration.between(LocalDateTime.of(LocalDate.now(), LocalTime.now()), LocalDateTime.of(
                LocalDate.now(), LocalTime.now()));
        System.out.println(d2.getSeconds());

        Duration d3 = Duration.between(Instant.now(), Instant.now());
        System.out.println(d3.toSeconds());
    }

    @Test
    void testPeriod() {
        Period days = Period.between(LocalDate.of(2018, 10, 10), LocalDate.of(2018, 10, 11));
        System.out.println(days.getDays());
    }

}
