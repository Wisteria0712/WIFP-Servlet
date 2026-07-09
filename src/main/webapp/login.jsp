<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div class="layui-card-header">
    <i class="layui-icon layui-icon-friends"></i>
</div>
<div class="layui-card-body">
    <form class="layui-form" action="${pageContext.request.contextPath}/LoginServlet.tran" method="post">
        <input type="text" name="userName" lay-verify="required" placeholder="用户名" class="layui-input"
               value="${cookie.userName.value}"/>
        <input type="password" name="password" lay-verify="required" placeholder="密码" class="layui-input"
               value="${cookie.password.value}"/>
        <input type="checkbox" name="autoLogin" lay-skin="switch" lay-text="记住我|忘掉我" checked/>
        <button lay-submit class="layui-btn layui-btn-radius layui-btn-sm layui-bg-cyan">
            <i class="layui-icon layui-icon-auz">登录</i>
        </button>
        <button type="reset" class="layui-btn layui-btn-radius layui-btn-sm layui-bg-cyan">
            <i class="layui-icon layui-icon-fonts-clear">重置</i>
        </button>
    </form>
</div>
<script>
    layui.use('form', function () {
    });
</script>
