package thread;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class Calculator implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        long current = 1L;
        long max = 2000L;
        long numPrimes = 0L;
        System.out.printf("Thread '%s' : Start\n", Thread.currentThread().getName());
        while (current < max) {
            if (isPrime(current)) {
                numPrimes++;
            }
            current++;
        }

        System.out.printf("Thread '%s' : End. # of Primes: %d\n", Thread.currentThread().getName(), numPrimes);
    }

    private boolean isPrime(long number) {
        if (number < 2) {
            return true;
        }
        for (long i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.printf("Min priority: %s\n", Thread.MIN_PRIORITY);
        System.out.printf("Normal priority: %s\n", Thread.NORM_PRIORITY);
        System.out.printf("Max priority: %s\n", Thread.MAX_PRIORITY);

        Thread threads[];
        Thread.State status[];
        threads = new Thread[10];
        status = new Thread.State[10];
        for (int index = 0; index < 10; index++) {
            threads[index] = new Thread(new Calculator());
            if (index % 2 == 0) {
                threads[index].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[index].setPriority(Thread.MIN_PRIORITY);
            }
            threads[index].setName("My thread" + index);
        }
        try (
                FileWriter file = new FileWriter("log.txt");
                PrintWriter pw = new PrintWriter(file);) {
            for (int i = 0; i < 10; i++) {
                pw.println("Main : Status of Thread :" + i + ":" + threads[i].getState());
                status[i] = threads[i].getState();
            }
            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != status[i]) {
                        writeThreadInfo(pw, threads[i], status[i]);
                        status[i] = threads[i].getState();
                    }
                }
                finish = true;
                for (int i = 0; i < 10; i++) {
                    finish = finish && (threads[i].getState() == State.TERMINATED);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
        pw.printf("Main : ID %d - %s\n", thread.getId(), thread.getName());
        pw.printf("Main : Priority: %d\n", thread.getPriority());
        pw.printf("Main : Old State %s\n", state);
        pw.printf("Main : New State %s\n", thread.getState());
        pw.printf("Main : ******************************************\n");
    }
}
