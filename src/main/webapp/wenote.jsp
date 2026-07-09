<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>巡检反馈平台</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
        <link rel="icon" href="./img/favicon.ico">
        <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
        <style>
            body {
                background: #fcfcfc;
            }
        </style>
    </head>
    <body>
        <!--网页页眉-->
        <div class="layui-row layui-bg-gray">
            <jsp:include page="noteHeader.jsp"></jsp:include>
        </div>
        <!--网页主体-->
        <div class="layui-container" style="padding: 10px">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md9">
                    <jsp:include page="noteMain.jsp"></jsp:include>
                </div>
                <div class="layui-col-md3 layui-bg-gray">
                    <jsp:include page="noteCard.jsp"></jsp:include>
                </div>
            </div>
        </div>
        <!--网页页脚-->
        <hr class="layui-bg-cyan" style="height: 5px"/>
        <div class="layui-row layui-bg-cyan" style="text-align: center">
            <jsp:include page="noteFooter.jsp"></jsp:include>
        </div>
        <hr class="layui-bg-cyan" style="height: 10px"/>
        <script>
            layui.use(['layer'], function () {
                var layer = layui.layer;
                var flashMsgs = "";
                <c:forEach items="${msgs}" var="msg">
                flashMsgs = flashMsgs + "${msg}<br />"
                </c:forEach>
                if (flashMsgs !== "") {
                    layer.msg(flashMsgs, {time: 2000, closeBtn: 2});
                }
            })
        </script>
        <c:remove var="flashMsgs"/>
    </body>
</html>
