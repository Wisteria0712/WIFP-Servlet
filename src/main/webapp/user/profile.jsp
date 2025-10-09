<%--
  Date: 2021/11/26
  Time: 16:56
  Author: Amnotgcs
  Description: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<div class="layui-card-header">
    <img id="userPhoto" height="50" width="50" class="layui-upload-img layui-circle"
         src="${pageContext.request.contextPath}/ShowUserPhotoServlet?fileName=${user.photo}"/>
    ${user.userName}
    <a href="${pageContext.request.contextPath}/user/LogoutServlet" class="layui-btn layui-btn-sm layui-bg-cyan">
        <i class="layuit-icon layui-icon-close-fill"></i>
        注销
    </a>
</div>

<div class="layui-card-body">
    <div class="layui-form-mid">
        注册时间：${user.createTime}
    </div>
    <div class="layui-btn-container">
        <button type="button" class="layui-btn layui-btn-sm layui-bg-cyan"
                id="uploadUserPhoto" onchange="uploadUserPhoto(this)">
            <i class="layui-icon layui-icon-picture"></i>修改头像
        </button>
        <a href="${pageContext.request.contextPath}/IndexServlet.tran?url=/user/changePassword.jsp"
           class="layui-btn layui-btn-sm layui-bg-cyan">
            <i class="layui-icon layui-icon-password"></i>修改密码
        </a>
    </div>
</div>

<script type="text/javascript">
    layui.use('upload', function () {
        var upload = layui.upload;
        var $ = layui.jquery;
        upload.render({
            elem: '#uploadUserPhoto'
            , size: 1024 * 2
            , accept: 'images'
            , exts: 'jpg|png|gif|bmp|jpeg'
            , field: 'upload'
            , url: '${pageContext.request.contextPath}/user/UploadUserPhotoServlet.tran'
            , done: function (res) {
                $("#userPhoto").attr('src', res.url);
            }
        });
    });
</script>