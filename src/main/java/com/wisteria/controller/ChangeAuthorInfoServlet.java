package com.wisteria.controller;

import com.wisteria.domain.User;
import com.wisteria.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet("/author/ChangeAuthorInfoServlet.tran")
public class ChangeAuthorInfoServlet extends HttpServlet {
    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ChangeAuthorInfoServlet.doPost access");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String userName = req.getParameter("userName");
        String nickName = req.getParameter("nickName");
        String telephone = req.getParameter("telephone");
        String brief = req.getParameter("brief");
        if (user.getNickName().equals(nickName) && user.getTelephone().equals(telephone) && user.getBrief().equals(brief)) {
            System.out.println("userInfo same don't operate");
        } else {
            User update = User.builder()
                    .userName(userName)
                    .nickName(nickName)
                    .telephone(telephone)
                    .brief(brief)
                    .build();
            userService.changeUserInfo(update);
            //更新Session的信息
            User curUser = userService.updateUsetInfo4Session(userName);
            session.setAttribute("user", curUser);
        }
        resp.sendRedirect(req.getContextPath() + "/IndexServlet.tran");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
