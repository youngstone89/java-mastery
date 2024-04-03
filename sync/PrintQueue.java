package sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
  private Lock queueLock;

  public PrintQueue(boolean booleanfairMode) {
    this.queueLock = new ReentrantLock(booleanfairMode);
  }

  public void printJob(Object document) {
    queueLock.lock();
    try {
      Long duration = (long) (Math.random() * 10000);
      System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during " +
          (duration / 1000) + " seconds");
      Thread.sleep(duration);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      queueLock.unlock();
    }
  }
}
