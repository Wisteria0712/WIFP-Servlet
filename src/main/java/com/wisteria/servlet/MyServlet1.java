package com.wisteria.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "my", value = "/my")
public class MyServlet1 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("Hello world!");
        System.out.println("service");
        request.setAttribute("name", "wisteria");
        request.setAttribute("age", 18);
        //request.getRequestDispatcher("login.jsp").forward(request, response);
        request.getRequestDispatcher("/my2").forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
