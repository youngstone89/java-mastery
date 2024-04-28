package com.example.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ConsoleClock implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s %s\n", Thread.currentThread().getName(), new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("%s The FileClock has been interrupted\n", Thread.currentThread().getName());
            }
        }

    }

    public static void main(String[] args) {
        ConsoleClock clock = new ConsoleClock();
        Thread thread = new Thread(clock);
        thread.start();
        System.out.println("after thread start");
        try {
            System.out.println("before 5 seconds sleep");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("after 5 seconds sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;
        System.out.println("before interrupt");
        thread.interrupt();
        System.out.println("after interrupt");
    }

}
