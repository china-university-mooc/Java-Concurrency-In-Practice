package com.itutry.lazy;

import net.jcip.annotations.NotThreadSafe;

/**
 * 延迟初始化中的竞态条件
 *
 * @author itutry
 * @create 2020-04-14_13:42
 */
@NotThreadSafe
public class LazyInitRace {

  private ExpensiveObject instance = null;

  public ExpensiveObject getInstance() {
    if (instance == null) {
      instance = new ExpensiveObject();
    }
    return instance;
  }
}

class ExpensiveObject {

  public ExpensiveObject() {
    try {
      Thread.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
