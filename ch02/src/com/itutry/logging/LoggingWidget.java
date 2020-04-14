package com.itutry.logging;

public class LoggingWidget extends Widget {

  public synchronized void doSomething() {
    System.out.println("this calling doSomething");
    super.doSomething();
  }
}

class Widget {

  public synchronized void doSomething() {
    System.out.println("super calling doSomething");
  }
}
