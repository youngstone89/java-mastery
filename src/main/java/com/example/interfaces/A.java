package com.example.interfaces;

public interface A {
    default void hello() {
        System.out.println("hello A");
    }
}
