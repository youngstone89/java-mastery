package com.example.exceptions;

public class ExceptionWithCause {

    static void throwWithCause() {
        try {
            // Creating an exception
            NumberFormatException ex = new NumberFormatException("Exception");

            // Setting a cause of the exception
            ex.initCause(new NullPointerException(
                    "This is actual cause of the exception"));

            // Throwing an exception with cause.
            throw ex;
        }

        catch (NumberFormatException ex) {
            // displaying the exception
            System.out.println(ex);

            // Getting the actual cause of the exception
            System.out.println(ex.getCause());
        }
    }

    static void throwWithCause2() {
        try {
            // code that might throw an exception
            int[] numbers = new int[5];
            int divisor = 0;
            for (int i = 0; i < numbers.length; i++) {
                int result = numbers[i] / divisor;
                System.out.println(result);
            }
        } catch (ArithmeticException e) {
            // create a new exception with the original exception as the cause
            throw new RuntimeException("division by zero", e);
        }
    }
}
