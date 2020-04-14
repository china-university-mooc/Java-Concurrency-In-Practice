package com.itutry.caching;

import java.math.BigInteger;
import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author itutry
 * @create 2020-04-14_14:25
 */
public abstract class AbstractFactorizer extends GenericServlet {

  protected void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    // do nothing
  }

  protected BigInteger[] factor(BigInteger i) {
    return new BigInteger[]{i};
  }

  protected BigInteger extractFromRequest(ServletRequest req) {
    int num = (int) (Math.random() * 10);
    return new BigInteger(String.valueOf(num));
  }
}
