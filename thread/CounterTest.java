package thread;

import java.time.Instant;

import org.junit.Test;

public class CounterTest {

    @Test
    public void testRaceCondition() {
        long start = System.currentTimeMillis();
        Counter counter = new Counter();

        System.out.println("initial: " + counter.count);
        Thread[] threads = new Thread[100000];
        for (int i = 0; i < 100000; i++) {
            Thread thread1 = new Thread(() -> {
                counter.add(1);
                System.out.println("" + Thread.currentThread().getName() + ":" + counter.count);
            });
            threads[i] = thread1;
        }

        Thread[] spinnerTheads = new Thread[2];
        spinnerTheads[0] = new Thread(() -> {
            for (int j = 0; j < 50000; j++) {
                threads[j].start();
            }
        });
        spinnerTheads[1] = new Thread(() -> {
            for (int j = 50000; j < 100000; j++) {
                threads[j].start();
            }
        });

        for (int i = 0; i < spinnerTheads.length; i++) {
            spinnerTheads[i].start();
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("final count:" + counter.count);
        long end = System.currentTimeMillis();
        System.out.println(Instant.ofEpochMilli(end - start).getEpochSecond());
    }
}
