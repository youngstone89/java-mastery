package com.example.interfaces;

public interface F {
    default void hey() {
        System.out.println("Hey from F");
    }
}
