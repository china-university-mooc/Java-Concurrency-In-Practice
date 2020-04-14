package com.itutry.caching;

import java.io.IOException;
import java.math.BigInteger;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 很好的对最近计算结果进行缓存，并计算命中率
 */
@ThreadSafe
public class GoodCachingFactorizer extends AbstractFactorizer {

  @GuardedBy("this")
  private BigInteger lastNumber;
  @GuardedBy("this")
  private BigInteger[] lastFactor;
  @GuardedBy("this")
  private long hits;
  @GuardedBy("this")
  private long cacheHits;

  public double getCacheHitRatio() {
    return (double) cacheHits / hits;
  }

  public long getHits() {
    return hits;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    BigInteger i = extractFromRequest(req);
    BigInteger[] factors = null;

    synchronized (this) {
      hits++;
      if (i.equals(lastNumber)) {
        cacheHits++;
        factors = lastFactor.clone();
        System.out.println(Thread.currentThread().getName() + ": " + i + "->" + lastFactor[0]);
      }
    }
    if (factors == null) {
      factors = factor(i);
      synchronized (this) {
        lastNumber = i;
        lastFactor = factors.clone();
      }
    }

    encodeIntoResponse(res, factors);
  }
}
