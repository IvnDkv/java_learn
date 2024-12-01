package com.example.counters;

public class VolatileCounter implements SiteVisitCounter {
  private volatile int counter = 0;

  @Override
  public void incrementVisitCount() {
    try {
      Thread.sleep(100);
      counter++;
    } catch (InterruptedException e) {
      System.out.println("InterruptedException in UnsynchronizedCounter");
    }
  }

  @Override
  public int getVisitCount() {
    return counter;
  }
}
