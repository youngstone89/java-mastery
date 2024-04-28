package com.example.futures;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SharedObject {

    private final Map<Integer, Integer> map = new ConcurrentHashMap();

    private volatile int count = 0;

    public synchronized void increment() {
        count++;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void set(int key, int val) {
        map.put(key, val);
    }

    public int getCount() {
        return count;
    }

}
