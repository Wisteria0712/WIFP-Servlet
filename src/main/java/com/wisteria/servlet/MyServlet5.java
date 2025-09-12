package com.wisteria.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/my5")
public class MyServlet5 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("my5");
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write("Hello, I am my5!".getBytes());
        outputStream.write("<hr>".getBytes());
        outputStream.write("你好，我是my5".getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
