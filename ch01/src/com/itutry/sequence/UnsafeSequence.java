package com.itutry.sequence;

import net.jcip.annotations.NotThreadSafe;

/**
 * 非线程安全的数值序列生成器
 *
 * @author itutry
 * @create 2020-04-14_13:42
 */
@NotThreadSafe
public class UnsafeSequence implements Sequence {

  private int value;

  public int getNext() {
    return value++;
  }
}
