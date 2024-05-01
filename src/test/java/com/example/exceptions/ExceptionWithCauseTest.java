package com.example.exceptions;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ExceptionWithCauseTest {
    @Test
    void testThrowWithCause() {
        ExceptionWithCause.throwWithCause();
    }

    @Test
    void testThrowWithCause2() {
        try {
            ExceptionWithCause.throwWithCause2();
        } catch (RuntimeException e) {

            System.out
                    .println(String.join("\t", e.toString(), Optional.ofNullable(e.getCause().toString()).orElse("")));
        }
    }

}
