package com.wisteria.controller;

import com.wisteria.domain.User;
import com.wisteria.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet("/user/UploadUserPhotoServlet.tran")
public class UploadUserPhotoServlet extends HttpServlet {

    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("access UploadUserPhotoServlet.tran");
        resp.setContentType("application/json;charset=utf-8");
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.getWriter().write("{\"code\": 1, \"msg\": \"用户未登录\"}");
            return;
        }
        Part part = req.getPart("upload");
        if (part != null) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null && !fileName.isEmpty()) {
                String address = fileName.substring(fileName.lastIndexOf("."));
                String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + address;
                String filePath = req.getServletContext().getRealPath("/resources/photo/") + uniqueFileName;
                part.write(filePath);
                String fileUrl = req.getContextPath() + "/resources/photo/" + uniqueFileName;
                System.out.println("filePath: " + filePath);
                resp.getWriter().write("{\"code\": 0, \"msg\": \"上传成功\", \"url\": \"" + fileUrl + "\"}");
                user.setPhoto(uniqueFileName);
                userService.changePhoto(user);
                req.getSession().setAttribute("user", user);
            } else {
                resp.getWriter().write("{\"code\": 1, \"msg\": \"文件名为空\"}");
            }
        } else {
            resp.getWriter().write("{\"code\": 1, \"msg\": \"未找到上传文件\"}");
        }
    }
}
