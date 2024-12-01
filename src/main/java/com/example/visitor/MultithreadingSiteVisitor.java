package com.example.visitor;

import java.util.ArrayList;
import java.util.List;

import com.example.counters.SiteVisitCounter;

public class MultithreadingSiteVisitor {
  private final SiteVisitCounter svc;
  private final List<Thread> threads = new ArrayList<>();
  private long start;
  private long end;

  public MultithreadingSiteVisitor(SiteVisitCounter svc) {
    this.svc = svc;
  }

  public void visitMultithread(int numOfThreads) {
    for (int i = 0; i < numOfThreads; i++) {
      Thread thread = new Thread(svc::incrementVisitCount);
      thread.start();
      threads.add(thread);
    }
  }
  
  public void waitUntilAllVisited() {
    start = System.currentTimeMillis();
    threads.stream().forEach(t -> {
      try {
        t.join();
      } catch (InterruptedException e) {
        System.out.println("InterruptedException in waitUntilAllVisited");
      }
    });
    end = System.currentTimeMillis();
  }

  public long getTotalTimeOfHandling() {
    return end - start;
  }

}
