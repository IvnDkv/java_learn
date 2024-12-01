package com.example.counters;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {
  private final Lock lock = new ReentrantLock(true);
  private int counter = 0;

  @Override
  public void incrementVisitCount() {
    lock.lock();
    try {
      Thread.sleep(100);
      counter++;
    } catch (InterruptedException e) {
      System.out.println("InterruptedException in UnsynchronizedCounter");
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int getVisitCount() {
    return counter;
  }
}
