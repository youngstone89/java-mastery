package com.example.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

import org.junit.jupiter.api.Test;

public class LocalDateExampleTest {

    @Test
    void testLocalDate() {
        final LocalDate localDate = LocalDate.of(2024, 11, 25); // immutable

        int year = localDate.getYear();
        Month month = localDate.getMonth();
        int monthVal = month.getValue();
        int day = localDate.getDayOfMonth();
        DayOfWeek dow = localDate.getDayOfWeek();
        int lengthOfMonth = localDate.lengthOfMonth();
        boolean leap = localDate.isLeapYear();

        LocalDate today = LocalDate.now();
        System.out.println(today.toString());

        int year2 = localDate.get(ChronoField.YEAR);
        int moy = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day2 = localDate.get(ChronoField.DAY_OF_MONTH);

        assertEquals(year, year2);
        assertEquals(day, day2);

        LocalDate dt = LocalDate.parse("2017-09-21");
        assertEquals(2017, dt.getYear());
    }

}
