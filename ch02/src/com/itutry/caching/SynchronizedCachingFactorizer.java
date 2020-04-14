package com.itutry.caching;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 能正确的对最近计算结果进行缓存，但并发性很差
 */
@ThreadSafe
public class SynchronizedCachingFactorizer extends AbstractFactorizer {

  @GuardedBy("this") private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
  @GuardedBy("this") private final AtomicReference<BigInteger[]> lastFactor = new AtomicReference<>();

  @Override
  public synchronized void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    BigInteger i = extractFromRequest(req);

    if (i.equals(lastNumber.get())) {
      System.out.println(Thread.currentThread().getName() + ": " + i + "->" + lastFactor.get()[0]);
      encodeIntoResponse(res, lastFactor.get());
    } else {
      BigInteger[] factors = factor(i);
      lastNumber.set(i);
      lastFactor.set(factors);

      encodeIntoResponse(res, factors);
    }
  }
}
