<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="lombok.Getter" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2025/9/12
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>我的测试</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                border: 0;
                font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
                box-sizing: border-box;
            }

            body, html {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-content: center;
                align-items: center;
                height: 100%;
                width: 100%;
            }

            .main {
                text-align: center;
                font-size: 18px;
                color: #66ccff;
            }
        </style>
    </head>
    <body>
        <div class="main">Hello world！</div>
        <%="<div class=\"main\">你好世界！</div>"%>
        <%
            String name = "Wisteria";
            System.out.println(name);
        %>
        <%!
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            @Getter
            String time = format.format(new Date());
        %>
        <%="<div class=\"main\">当前时间：" + time + "</div>"%>
        <div class="main">前端返回用户名：${name}</div>
        <%-- <%--%>
        <%-- String username = request.getParameter("name");--%>
        <%-- %>--%>
        <%-- <%="<div class=\"main\">前端返回用户名：" + username + "</div>"%>--%>
        <%-- <jsp:include page="login.jsp"/>--%>
        <%-- <jsp:forward page="aboutWeNote.jsp"/>--%>
    </body>
</html>
