package com.wisteria.filter;

import com.wisteria.domain.vo.UserVO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter("/RegisterUserServlet.tran")
public class User4ValidateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("assess register4Validatefilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String telephone = request.getParameter("telephone");
        String brief = request.getParameter("brief");
        String checkCodeInput = request.getParameter("checkCodeInput");
        Object checkCodeObj = session.getAttribute("checkCode");
        String checkCode = (checkCodeObj != null) ? checkCodeObj.toString() : null;
        UserVO userVO = UserVO.builder()
                .userName(userName)
                .nickname(nickName)
                .password(password)
                .confirmPassword(confirmPassword)
                .telephone(telephone)
                .brief(brief)
                .checkCodeInput(checkCodeInput)
                .generateCheckCode(checkCode)
                .build();
        List<String> validateResult = userVO.validateUserForm();
        if (!validateResult.isEmpty()) {
            session.setAttribute("msgs", validateResult);
            response.sendRedirect("IndexServlet.tran?url=/register.jsp");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}