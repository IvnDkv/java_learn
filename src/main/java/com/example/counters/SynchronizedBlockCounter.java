package com.example.counters;

public class SynchronizedBlockCounter implements SiteVisitCounter {
  private volatile int counter = 0;

  @Override
  public synchronized void incrementVisitCount() {
    counter++;
  }

  @Override
  public int getVisitCount() {
    return counter;
  }
}
