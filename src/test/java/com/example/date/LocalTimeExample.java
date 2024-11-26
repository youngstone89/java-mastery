package com.example.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class LocalTimeExample {

    @Test
    void testName() {
        LocalTime time = LocalTime.of(13, 45, 20);
        System.out.println(String.format("%s %s %s ", time.getHour(), time.getMinute(), time.getSecond()));
        LocalTime time2 = LocalTime.parse("13:45:20");
        assertEquals(time.getHour(), time2.getHour());
    }

}