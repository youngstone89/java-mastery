package com.example.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;

public class TemporalAdjustExample {

    @Test
    void testName() {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.println(date.getDayOfWeek());
        LocalDate nextMonday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println(nextMonday);
        System.out.println(nextMonday.getDayOfWeek());

        LocalDate nextMonday2 = nextMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(nextMonday2);
        System.out.println(nextMonday2.getDayOfWeek());
    }

}
