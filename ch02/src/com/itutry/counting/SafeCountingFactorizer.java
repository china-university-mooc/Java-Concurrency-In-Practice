package com.itutry.counting;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import net.jcip.annotations.ThreadSafe;

/**
 * 使用AtomicLong类型变量来统计已处理请求的数量
 *
 * @author itutry
 * @create 2020-04-14_13:42
 */
@ThreadSafe
public class SafeCountingFactorizer extends GenericServlet implements Servlet {

  private AtomicLong count = new AtomicLong(0);

  public long getCount() {
    return count.get();
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    BigInteger i = extractFromRequest(req);
    BigInteger[] factors = factor(i);
    count.incrementAndGet();
    encodeIntoResponse(res, factors);
  }

  private void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {

  }

  private BigInteger[] factor(BigInteger i) {
    return new BigInteger[]{i};
  }

  private BigInteger extractFromRequest(ServletRequest req) {
    return new BigInteger("7");
  }
}
