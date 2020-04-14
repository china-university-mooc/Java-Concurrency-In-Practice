package com.itutry.sequence;

public class SequenceTest {

  public static void main(String[] args) {
//    final Sequence sequence = new UnsafeSequence();
    final Sequence sequence = new SafeSequence();
    final Runnable runnable = () -> {
      for (int i = 0; i < 20; i++) {
        System.out.println(Thread.currentThread().getName() + ": seq=" + sequence.getNext());

        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    new Thread(runnable).start();
    new Thread(runnable).start();
  }
}
