package com.wisteria.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/my2")
public class MyServlet2 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("my2");
        //resp.getWriter().write("name:" + req.getAttribute("name") + "\n");
        //resp.getWriter().write("age:" + req.getAttribute("age") + "\n");
        //resp.setStatus(302);
        //resp.setHeader("Location", "my3");
        resp.sendRedirect("https://www.baidu.com");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
