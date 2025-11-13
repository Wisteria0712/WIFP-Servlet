package com.wisteria.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, @NotNull FilterChain filterChain) throws IOException, ServletException {
        System.out.println("url filter access");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        System.out.println("url: " + url);
        if (url.contains("/user/")) {
            if (session.getAttribute("user") == null) {
                System.out.println("user is null");
                response.sendRedirect(request.getContextPath() + "/IndexServlet.tran?url=/user/login.jsp");
            } else {
                System.out.println("user is not null");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
