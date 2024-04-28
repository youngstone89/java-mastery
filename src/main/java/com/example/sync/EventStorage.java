package com.example.sync;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class EventStorage {
    private int maxSize;
    private Queue<Date> storage;
    private long threadId;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void set() {
        updateCurrentThread();
        while (storage.size() == maxSize) {
            try {
                System.out.printf("[producer thread: %s start waiting]Size: %d(MaxSize Reached) \n",
                        Thread.currentThread().getId(),
                        storage.size());
                wait();
                System.out.printf("[producer thread: %s woke up]Size: %d \n",
                        Thread.currentThread().getId(),
                        storage.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.offer(new Date());
        System.out.printf("[producer thread: %s]Added to Queue Size: %d \n", Thread.currentThread().getId(),
                storage.size());
        notify();
        System.out.printf("[producer thread: %s]notified\n", Thread.currentThread().getId());
    }

    private void updateCurrentThread() {
        if (threadId != Thread.currentThread().getId()) {
            threadId = Thread.currentThread().getId();
        }
        System.out.println("[Thread:" + threadId + "]" + "got controll");
    }

    public synchronized void get() {
        updateCurrentThread();
        while (storage.size() == 0) {
            try {
                System.out.printf("[consumer thread: %s start waiting]no queue to consume -> Size: %d: \n",
                        Thread.currentThread().getId(),
                        storage.size());
                wait();
                System.out.printf("[consumer thread: %s woke up]Size: %d: \n",
                        Thread.currentThread().getId(),
                        storage.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String element = storage.poll().toString();
        System.out.printf("[consumer thread: %s]Polled from Queue, Size: %d: %s\n", Thread.currentThread().getId(),
                storage.size(),
                element);
        notify();
        System.out.printf("[consumer thread: %s]notified\n", Thread.currentThread().getId());

    }

}
