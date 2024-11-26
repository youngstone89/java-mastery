package com.example.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class LocalDateTimeExample {

    @Test
    void testName() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 45, 20);
        LocalDateTime ldt1 = LocalDateTime.of(LocalDate.of(2017, Month.APRIL, 1), LocalTime.of(11, 12));
        LocalDateTime ldt2 = LocalDate.of(2017, Month.APRIL, 1).atTime(13, 45, 20);
        LocalDateTime ldt3 = LocalDate.of(2017, Month.APRIL, 1).atStartOfDay();
        LocalDateTime ldt4 = LocalTime.now().atDate(LocalDate.of(2017, Month.APRIL, 1));

    }

}