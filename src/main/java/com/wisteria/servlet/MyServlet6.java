package com.wisteria.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet("/my6")
public class MyServlet6 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", req.getParameter("name"));
        ArrayList<String> lists = new ArrayList<>();
        Collections.addAll(lists, "张三", "李四", "王五");
        req.setAttribute("lists", lists);
        req.getRequestDispatcher("myTest2.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
