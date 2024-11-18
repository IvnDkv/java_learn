package com.example;

public class App {
  public static void main( String[] args ) {
    int res;
    try {
      res = 7 / 0;
      System.out.println(res);
    } catch (ArithmeticException e) {
      System.out.println("ArithmeticException");
    } finally {
      System.out.println("Finally Arithmetic");
    }

    int[] arr = new int[3];
    try {
      for (int i = 0; i < 5; i++) {
        arr[i] = arr[i] * 2;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("ArrayIndexOutOfBoundsException");
    } finally {
      System.out.println("Finally ArrayIndex");
    }
  }
}
