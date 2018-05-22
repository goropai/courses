package com.goropai.filter;

import javax.servlet.*;
import java.io.IOException;

public class CodingFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) {
    }
}
