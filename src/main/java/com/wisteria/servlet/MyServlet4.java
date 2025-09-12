package com.wisteria.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/my4")
public class MyServlet4 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("my4");
//        resp.getWriter().write("Hello, I am my4!");
//        resp.getWriter().write("<hr>");
//        resp.getWriter().write("你好，我是my4");
//        resp.getWriter().flush();
//        resp.getWriter().close();
        req.getRequestDispatcher("/mytest.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
