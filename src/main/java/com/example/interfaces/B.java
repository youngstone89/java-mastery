package com.example.interfaces;

public interface B extends A {
    default void hello() {
        System.out.println("hello B");
    }
}
