package com.itutry.lazy;

public class LazyInitRaceTest {

  public static void main(String[] args) {
    LazyInitRace lazyInitRace = new LazyInitRace();
    final Runnable runnable = () -> {
      final ExpensiveObject instance = lazyInitRace.getInstance();
      System.out.println(
          Thread.currentThread().getName() + ": instance=" + System.identityHashCode(instance));
    };

    new Thread(runnable).start();
    new Thread(runnable).start();
  }
}
