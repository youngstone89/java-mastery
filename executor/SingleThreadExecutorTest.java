package executor;

import java.util.concurrent.Executors;

import thread.Calculator;

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
