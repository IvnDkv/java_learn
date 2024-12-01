package com.example;

import com.example.counters.AtomicIntegerCounter;
import com.example.counters.ReentrantLockCounter;
import com.example.counters.SynchronizedBlockCounter;
import com.example.counters.UnsynchronizedCounter;
import com.example.counters.VolatileCounter;
import com.example.visitor.MultithreadingSiteVisitor;

public class App {
  public static void main( String[] args ) {

    //UnsynchronizedCounter
    // UnsynchronizedCounter uc = new UnsynchronizedCounter();
    // MultithreadingSiteVisitor visitorUc = new MultithreadingSiteVisitor(uc);
    // visitorUc.visitMultithread(10);
    // visitorUc.waitUntilAllVisited();
    // System.out.println("Total time UnsynchronizedCounter: " + visitorUc.getTotalTimeOfHandling());
    // System.out.println("UnsynchronizedCounter: " + uc.getVisitCount());

    //VolatileCounter
    // VolatileCounter vc = new VolatileCounter();
    // MultithreadingSiteVisitor visitorVc = new MultithreadingSiteVisitor(vc);
    // visitorVc.visitMultithread(10);
    // visitorVc.waitUntilAllVisited();
    // System.out.println("Total time VolatileCounter: " + visitorVc.getTotalTimeOfHandling());
    // System.out.println("VolatileCounter: " + vc.getVisitCount());

    //SynchronizedBlockCounter
    // SynchronizedBlockCounter sc = new SynchronizedBlockCounter();
    // MultithreadingSiteVisitor visitorSc = new MultithreadingSiteVisitor(sc);
    // visitorSc.visitMultithread(10);
    // visitorSc.waitUntilAllVisited();
    // System.out.println("Total time SynchronizedBlockCounter: " + visitorSc.getTotalTimeOfHandling());
    // System.out.println("SynchronizedBlockCounter: " + sc.getVisitCount());

    //AtomicIntegerCounter
    // AtomicIntegerCounter ac = new AtomicIntegerCounter();
    // MultithreadingSiteVisitor visitorAc = new MultithreadingSiteVisitor(ac);
    // visitorAc.visitMultithread(10);
    // visitorAc.waitUntilAllVisited();
    // System.out.println("Total time AtomicIntegerCounter: " + visitorAc.getTotalTimeOfHandling());
    // System.out.println("AtomicIntegerCounter: " + ac.getVisitCount());

    //ReentrantLockCounter
    ReentrantLockCounter rc = new ReentrantLockCounter();
    MultithreadingSiteVisitor visitorRc = new MultithreadingSiteVisitor(rc);
    visitorRc.visitMultithread(10);
    visitorRc.waitUntilAllVisited();
    System.out.println("Total time ReentrantLockCounter: " + visitorRc.getTotalTimeOfHandling());
    System.out.println("ReentrantLockCounter: " + rc.getVisitCount());
  }
}
