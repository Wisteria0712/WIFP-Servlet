<%--
  Author: Amnotgcs
  Date: 2021/11/26
  Time: 15:48
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--登录或个人简介面板--%>
<div class="layui-card">
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <jsp:include page="login.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="/user/profile.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>
</div>
<%--后台管理面板--%>
<c:if test="${sessionScope.user.isAuthor=='Y'}">
    <jsp:include page="/author/author.jsp"></jsp:include>
</c:if>
<%--信息类别列表面板--%>
<div class="layui-card">
    <div class="layui-card-header">
        <i class="layui-icon layui-icon-table"></i>
        巡检反馈类别
    </div>
    <div class="layui-card-body layui-btn-container">
        <c:forEach items="${categoryNameMap}" var="category">
            <a class="layui-btn layui-bg-gray" href="${pageContext.request.contextPath}/FetchAllNoteByCategoryNameServlet.tran?categoryName=${category.key}">
                ${category.key}
                <span class="layui-badge">${category.value}</span>
            </a>
        </c:forEach>
    </div>
</div>
<%--信息标签列表面板--%>
<div class="layui-card">
    <div class="layui-card-header">
        <i class="layui-icon layui-icon-note"></i>
        巡检反馈标签
    </div>
    <div class="layui-card-body layui-btn-container">
        <c:forEach items="${tagNameMap}" var="tag">
            <a class="layui-btn layui-bg-gray" href="${pageContext.request.contextPath}/FetchAllNoteByTagNameServlet.tran?tagName=${tag.key}">
                ${tag.key}
                <span class="layui-badge">${tag.value}</span>
            </a>
        </c:forEach>
    </div>
</div>
<%--其他功能的面板--%>
<div class="layui-card">
    <div class="layui-card-header">
        <i class="layui-icon layui-icon-link"></i>
        资源分享
    </div>
    <div class="layui-card-body layui-btn-container">
        <a class="layui-btn layui-bg-gray" href="">工业园大事件</a>
        <a class="layui-btn layui-bg-gray" href="">技术乐园</a>
    </div>
</div>
