<%--
  Author: Amnotgcs
  Date: 2021/11/26
  Time: 15:45
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!--左列logo-->
<div class="layui-col-md4" style="text-align: center">
    <div class="layui-row">
        <div class="layui-col-md2 layui-col-md-offset1 layui-anim layui-anim-rotate">
            <div class="layui-row" id="logo">
                <img src="01.png" width="90" height="90">

            </div>
        </div>
        <div class="layui-col-md9">
            <div class="layui-row layui-elip" style="font-size: 28px;margin-top:20px" >
                安巡互动反馈台
            </div>
        </div>
    </div>
</div>
<!--中列导航-->
<div class="layui-col-md5">
    <ul class="layui-nav layui-bg-gray">
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/FetchAllNoteServlet.tran" style="color: gray;font-size: 20px;margin-top:15px">
            <i class="layui-icon layui-icon-release" style="color: black; font-size: 20px;margin-top:20px"></i>
            巡检反馈
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/IndexServlet.tran" style="color: gray;font-size: 20px;margin-top:15px">
            <i class="layui-icon layui-icon-tips" style="color: black; font-size: 20px"></i>
            关于系统
            </a>
        </li>
        <li class="layui-nav-item">
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/IndexServlet.tran?url=/register.jsp" style="color: gray;font-size: 20px;margin-top:15px">
                    <i class="layui-icon layui-icon-add-circle" style="color: black; font-size: 20px;margin-top:15px"></i>
                    注册
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/FetchMyCommentServlet.tran" style="color: gray;font-size: 20px;margin-top:15px">
                    <i class="layui-icon layui-icon-reply-fill" style="color: black; font-size: 20px;margin-top:20px"></i>
                    我的反馈
                    </a>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</div>
<!--右列模糊查询表单-->
<div class="layui-col-md3">
    <form action="${pageContext.request.contextPath}/FetchAllNoteByKeywordServlet.tran" class="layui-form">
        <div class="layui-form-mid">
            <input type="text" class="layui-input" name="keyword" lay-verify="required" placeholder="工作搜索"/>
        </div>
        <div class="layui-form-mid">
            <button class="layui-btn layui-bg-gray" lay-submit />
            <i class="layui-icon layui-icon-search"></i>
            搜索
        </div>
        <div class="layui-form-mid">
            <span id="today" style="color: gray; font-size: 12px;"></span>
        </div>
    </form>
</div>
<script>
    layui.use(['util', 'element'], function() {
        var util = layui.util;
        var $ = layui.jquery;

        // 更新当前时间的函数
        function updateCurrentTime() {
            var now = new Date();
            var timeStr = util.toDateString(now, 'yyyy-MM-dd HH:mm:ss');
            $("#today").html('当前时间: ' + timeStr);
        }

        // 初始调用一次
        updateCurrentTime();

        // 每秒更新一次时间
        setInterval(updateCurrentTime, 1000);

        // 保持logo动画效果不变
        setTimeout(function () {
            $('#logo').removeClass('layui-anim-rotate');
        }, 1000);

        $('#logo').on('click', function () {
            var othis = $(this);
            othis.addClass('layui-anim-rotate');
            setTimeout(function () {
                othis.removeClass('layui-anim-rotate')
            }, 1000);
        });
    });
</script>