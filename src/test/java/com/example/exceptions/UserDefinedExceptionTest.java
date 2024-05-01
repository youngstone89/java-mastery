package com.example.exceptions;

import org.junit.jupiter.api.Test;

public class UserDefinedExceptionTest {
    @Test
    void testUserDefinedException() {
        try {
            throw new UserDefinedException("defined by me");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUserDefinedException_printMessage() {
        try {
            throw new UserDefinedException("defined by me");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testUserDefinedException_toString() {
        try {
            throw new UserDefinedException("defined by me");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
