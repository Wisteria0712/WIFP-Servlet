package com.wisteria.filter;

import com.wisteria.util.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 数据库操作过滤器
 */
@WebFilter("/*")
public class DBFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, @NotNull FilterChain filterChain) throws IOException, ServletException {
        System.out.println("access filter");
        try {
            DBUtil.beginTransaction();
            filterChain.doFilter(servletRequest, servletResponse);
            DBUtil.commitTransaction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
