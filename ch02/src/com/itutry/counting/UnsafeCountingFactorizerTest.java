package com.itutry.counting;

import java.io.IOException;
import javax.servlet.ServletException;

public class UnsafeCountingFactorizerTest {

  public static void main(String[] args) {
//    final UnsafeCountingFactorizer factorizer = new UnsafeCountingFactorizer();
    final SafeCountingFactorizer factorizer = new SafeCountingFactorizer();
    final Runnable runnable = () -> {
      for (int i = 0; i < 20; i++) {
        try {
          factorizer.service(null, null);
          Thread.sleep(1);
        } catch (ServletException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    final Thread t1 = new Thread(runnable);
    final Thread t2 = new Thread(runnable);
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(factorizer.getCount());
  }
}
