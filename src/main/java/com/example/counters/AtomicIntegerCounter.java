package com.example.counters;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {
  private AtomicInteger counter = new AtomicInteger(0);

  @Override
  public void incrementVisitCount() {
    counter.incrementAndGet();
  }

  @Override
  public int getVisitCount() {
    return counter.get();
  }
}
