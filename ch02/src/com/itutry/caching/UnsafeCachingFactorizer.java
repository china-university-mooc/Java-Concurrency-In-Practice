package com.itutry.caching;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import net.jcip.annotations.NotThreadSafe;

/**
 * 没有足够的原子性保证的情况下对最近计算结果进行缓存
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends AbstractFactorizer {

  private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
  private final AtomicReference<BigInteger[]> lastFactor = new AtomicReference<>();

  @Override
  public void service(ServletRequest req, ServletResponse res)
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
