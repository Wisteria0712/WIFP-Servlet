<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2025/9/18
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>我的测试</title>
        <%--        <style>--%>
        <%--            * {--%>
        <%--                margin: 0;--%>
        <%--                padding: 0;--%>
        <%--                border: 0;--%>
        <%--                font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;--%>
        <%--                box-sizing: border-box;--%>
        <%--            }--%>

        <%--            body, html {--%>
        <%--                display: flex;--%>
        <%--                flex-direction: column;--%>
        <%--                justify-content: center;--%>
        <%--                align-content: center;--%>
        <%--                align-items: center;--%>
        <%--                height: 100%;--%>
        <%--                width: 100%;--%>
        <%--                background: #fcfcfc;--%>
        <%--                font-size: 18px;--%>
        <%--            }--%>
        <%--        </style>--%>
    </head>
    <body>
        <%--        <c:set var="name" value="Wisteria" scope="session"/>--%>
        <%--        <span>你好,${sessionScope.name}</span>--%>
        <%--        <br>--%>
        <%--        <c:set var="age" scope="request"/>--%>
        <%--        <span>年龄:${age}</span>--%>
        <c:if test="${name=='Admin'}">
            <p>你是管理员</p>
        </c:if>
        <c:forEach items="${lists}" var="nav" varStatus="vs">
            <div>第${vs.count}项:${nav}</div>
        </c:forEach>
        <c:choose>
            <c:when test="${name=='Wisteria'|| name=='wisteria'}">
                <p>你是Wisteria</p>
            </c:when>
            <c:when test="${name=='Admin'}">
                <p>你是管理员</p>
            </c:when>
            <c:otherwise>
                <p>你是其他用户</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
