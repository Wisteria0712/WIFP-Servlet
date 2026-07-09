<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-card">
    <div class="layui-card-header">
        <i class="layui-icon layui-icon-set"></i>后台管理
    </div>
    <div class="layui-card-body layui-btn-container">
        <a class="layui-btn layui-bg-gray" href="${pageContext.request.contextPath}/author/AddNoteServlet.tran">
            添加巡检记录
        </a>
        <a class="layui-btn layui-bg-gray"
           href="${pageContext.request.contextPath}/author/FetchAllCategoryNameServlet.tran">
            巡检类别管理
        </a>
        <a class="layui-btn layui-bg-gray" href="${pageContext.request.contextPath}/author/FetchAllTagNameServlet.tran">
            巡检标签管理
        </a>
        <a class="layui-btn layui-bg-gray" href="${pageContext.request.contextPath}/author/EditAuthorInfoServlet.tran">个人信息维护</a>
    </div>
</div>
