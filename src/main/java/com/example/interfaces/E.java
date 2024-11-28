package com.example.interfaces;

public interface E {
    default void hey() {
        System.out.println("Hey from E");
    }
}
