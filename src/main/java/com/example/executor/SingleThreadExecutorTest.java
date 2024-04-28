package com.example.executor;

import java.util.concurrent.Executors;

import com.example.thread.Calculator;

public class SingleThreadExecutorTest {
    public static void main(String[] args) {
        var pool = Executors.newSingleThreadExecutor();
        Calculator calculator = new Calculator();

        pool.submit(calculator);
        System.out.println("1");
        pool.submit(calculator);
        System.out.println("2");
        pool.shutdown();
    }
}
