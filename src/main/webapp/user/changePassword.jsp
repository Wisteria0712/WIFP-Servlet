<%--
  Date: 2021/12/15
  Time: 18:42
  Author: Amnotgcs
  Description: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px">
    <legend>修改密码</legend>
    <div class="layui-field-box">
        <form class="layui-form" method="post"
              action="${pageContext.request.contextPath}/user/ChangePasswordServlet.tran">
            <div class="layui-form-item">
                <label class="layui-form-label">旧密码</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="password" name="oldPassword" layui-verify="required"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="password" name="password" layui-verify="required"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="password" name="confirmPassword" layui-verify="required"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button layui-submit class="layui-btn layui-btn-radius layui-btn-sm layui-bg-cyan">
                        <i class="layui-icon layui-icon-set-fill"></i>确认修改
                    </button>
                </div>
            </div>
        </form>
    </div>
</fieldset>