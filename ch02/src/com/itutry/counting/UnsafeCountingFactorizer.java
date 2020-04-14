package com.itutry.counting;

import java.io.IOException;
import java.math.BigInteger;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import net.jcip.annotations.NotThreadSafe;

/**
 * 无同步的情况下统计已处理请求数量的Servlet
 *
 * @author itutry
 * @create 2020-04-14_13:42
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends GenericServlet implements Servlet {

  private long count = 0;

  public long getCount() {
    return count;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    BigInteger i = extractFromRequest(req);
    BigInteger[] factors = factor(i);
    count++;
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
