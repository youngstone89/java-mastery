package futures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class CompletableFutureTest {

    public Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(2000);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    @Test
    public void testSimpleCompletableFuture() {
        String result = "";
        try {
            Future<String> completableFuture = calculateAsync();
            System.out.println("do something");
            System.out.println("waiting");
            result = completableFuture.get();
            System.out.println("got result");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals("Hello", result);
    }

    @Test
    public void testSupplyAsyncWithLambdaSupplier() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        try {
            assertEquals("Hello", future.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSupplyAysncAndThenApplyWithFunction() {
        try {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return "Hello";
            });
            System.out.println("hello future");
            CompletableFuture<String> future = completableFuture
                    .thenApply(s -> {
                        System.out.println(Thread.currentThread().getName());
                        return s + " World";
                    });
            System.out.println("then apply");
            assertEquals("Hello World", future.get());
            System.out.println("done");
        } catch (

        InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSupplyAysncAndThenAcceptWithConsumer() {
        try {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

            CompletableFuture<Void> future = completableFuture
                    .thenAccept(s -> System.out.println("Computation returned: " + s));

            future.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSupplyAysncAndThenRunWithRunnable() {
        try {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

            CompletableFuture<Void> future = completableFuture
                    .thenRun(() -> System.out.println("Computation finished."));

            future.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSupplyAysncAndThenCompose() {
        try {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> 1)
                    .thenCompose(s -> CompletableFuture.supplyAsync(() -> String.valueOf(s) + " World"));

            assertEquals("1 World", completableFuture.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSupplyAysncAndThenCombine() {
        try {
            CompletableFuture<String> completableFuture = CompletableFuture
                    .supplyAsync(() -> "Hello")
                    .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);

            assertEquals("Hello World", completableFuture.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSupplyAysncAndAcceptBoth() {
        try {
            CompletableFuture future = CompletableFuture
                    .supplyAsync(() -> "Hello")
                    .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                            (s1, s2) -> System.out.println(s1 + s2));
            future.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testAllOf() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        // ...

        combinedFuture.get();

        assertTrue(future1.isDone());
        assertTrue(future2.isDone());
        assertTrue(future3.isDone());
        System.out.println("" + future1.join() + future2.join() + future3.join());
        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
        System.out.println(combined);
        assertEquals("Hello Beautiful World", combined);
    }

    @Test
    public void testHandle() {
        String name = null;

        // ...

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle(
                (s, t) -> {
                    if (t != null) {
                        return "exception catched" + t.getMessage();
                    } else {
                        return s;
                    }
                });

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testWithCompleteExceptionally() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        // ...

        completableFuture.completeExceptionally(
                new RuntimeException("Calculation failed!"));

        // ...

        completableFuture.get(); // ExecutionException
    }

    @Test
    public void testThenApplyAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "Hello";
        });
        CompletableFuture<String> future = completableFuture
                .thenApplyAsync(s -> {
                    System.out.println(Thread.currentThread().getName());
                    return s + " World";
                });

        assertEquals("Hello World", future.get());
    }

    @Test
    public void testPutAll() throws InterruptedException, ExecutionException {
        SharedObject object = new SharedObject();
        Map<Integer, Integer> justMap = new HashMap<>();
        Random rn = new Random();

        int SIZE = 100000;
        CompletableFuture<Integer>[] futures = new CompletableFuture[SIZE];
        for (int i = 0; i < futures.length; i++) {
            final int val = i;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(rn.nextInt(1));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("future[" + val + "]" + Thread.currentThread().getName() + "\t put val:" + val);
                object.set(val, val);
                object.increment();
                justMap.put(val, val);
                return Integer.valueOf(val);
            });
            futures[i] = future;
        }
        CompletableFuture<Void> all = CompletableFuture.allOf(futures);
        // all.thenAccept(Void -> {
        // for (int i = 0; i < futures.length; i++) {
        // futures[i].join();
        // Integer val = futures[i].join();
        // System.out.println("future[" + i + "]" + Thread.currentThread().getName() +
        // "\t put val:" + val);
        // object.getMap().put(val, val);
        // }
        // });
        Thread newThread = new Thread(() -> {
            boolean stop = false;
            int checkLimit = SIZE;
            int checkpoint = 0;
            while (!stop) {
                if (object.getMap().size() == SIZE || object.getCount() == SIZE) {
                    System.out.println("reached size limit");
                    stop = true;
                }
                if (checkpoint >= checkLimit) {
                    System.out.println("checkpoint exceeds");
                    stop = true;
                }
                System.out.println("size check:" + object.getMap().size());
                System.out.println("count check:" + object.getCount());
                checkpoint++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("stoppeds");
        });
        newThread.start();
        System.out.println("check thread started");
        all.join();
        System.out.println("futures completed");
        Thread.sleep(60_000);
        System.out.println("sleep completed");
        System.out.println(object.getMap().size());
        System.out.println(object.getMap().values().size());
        System.out.println(object.getCount());
        System.out.println(justMap.size());
    }
}
