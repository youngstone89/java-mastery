package com.example.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

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

        LocalDate date1 = LocalDate.of(2017, 10, 1);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(31);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 2);
        LocalDate date5 = date4.plusWeeks(5);
        LocalDate date6 = date5.minusDays(5);
        LocalDate date7 = date6.plus(5, ChronoUnit.MONTHS);

        System.out.println(date1.toString());
        System.out.println(date4.toString());

        LocalDate date8 = LocalDate.from(ZonedDateTime.now());
        System.out.println(date8);

        LocalDate date = LocalDate.of(2024, 11, 26); // Example LocalDate
        TemporalField field = ChronoField.YEAR;
        // TemporalAccessor methods
        System.out.println("Year: " + date.get(field)); // Access a specific field
        System.out.println("Is field supported: " + date.isSupported(field)); // Check support

        TemporalAccessor accessor = LocalDate.of(2024, 11, 26);
        int x = accessor.get(field);
        System.out.println(x);
    }

}
