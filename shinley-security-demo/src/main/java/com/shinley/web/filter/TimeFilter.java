package com.shinley.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();
        System.out.println("time filter finish:" + (start-end));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
