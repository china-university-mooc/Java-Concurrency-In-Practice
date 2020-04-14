package com.itutry.caching;

import java.io.IOException;
import javax.servlet.ServletException;

public class CachingFactorizerTest {

  public static void main(String[] args) {
    final AbstractFactorizer factorizer = new UnsafeCachingFactorizer();
//    final AbstractFactorizer factorizer = new SynchronizedCachingFactorizer();
//    final AbstractFactorizer factorizer = new GoodCachingFactorizer();
    final Runnable runnable = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 20; i++) {
          try {
            factorizer.service(null, null);
          } catch (ServletException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
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
  }
}
