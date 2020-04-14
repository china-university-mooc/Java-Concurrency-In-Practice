package com.itutry.sequence;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;

/**
 * 线程安全的数值序列生成器
 *
 * @author itutry
 * @create 2020-04-14_13:42
 */
@NotThreadSafe
public class SafeSequence implements Sequence {

  @GuardedBy("this")
  private int value;

  public synchronized int getNext() {
    return value++;
  }
}
