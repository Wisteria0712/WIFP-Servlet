<%--
  Date: 2021/12/21
  Time: 12:59
  Author: Amnotgcs
  Description: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px">
    <legend>修改<span>${requestScope.userName}</span>信息</legend>
    <div class="layui-field-box">
        <form class="layui-form" method="post"
              action="${pageContext.request.contextPath}/author/ChangeAuthorInfoServlet.tran">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="userName" value="${requestScope.userName}">
                    <input class="layui-input layui-font-gray" type="text" name="newUserName" layui-verify="required"
                           value="${requestScope.userName}" readonly/>
                    <span class="layui-font-gray">用户名不能修改</span>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="text" name="nickName" layui-verify="required"
                           value="${requestScope.nickName}"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="text" name="telephone" layui-verify="required"
                           value="${requestScope.telephone}"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">简介</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="text" name="brief" layui-verify="required"
                           value="${requestScope.brief}"/>
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