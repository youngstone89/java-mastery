package com.example.exceptions;

import org.junit.jupiter.api.Test;

public class RuntimeSystemSearchHandlerTest {
    @Test
    void testComputeDivision() {
        try {
            RuntimeSystemSearchHandler.computeDivision(1, 0);
        } catch (ArithmeticException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }

    }
}
