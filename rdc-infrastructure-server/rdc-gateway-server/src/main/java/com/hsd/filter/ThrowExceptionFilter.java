package com.hsd.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;

public class ThrowExceptionFilter extends ZuulFilter  {
    private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);
    
    @Override
    public String filterType() {
        return "pre";
    }
    
    @Override
    public int filterOrder() {
        return 0;
    }
    
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    @Override
    public Object run() {
        log.info("This is a pre filter, it will throw a RuntimeException");
        doSomething();
        return null;
    }
    
    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }
  
}