package com.wisteria.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/ShowUserPhotoServlet")
public class ShowUserPhotoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("access ShowUserPhotoServlet");
        String fileName = req.getParameter("fileName");
        String filePath = req.getServletContext().getRealPath("/resources/photo/") + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            resp.setContentType("image/jpeg");
            Path path = Paths.get(filePath);
            byte[] imageData = Files.readAllBytes(path);
            resp.getOutputStream().write(imageData);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}