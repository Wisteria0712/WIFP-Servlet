<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div class="layui-col-md9">
    <c:choose>
        <c:when test="${empty param.url}">
            <jsp:include page="/aboutWeNote.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="${param.url}"></jsp:include>
        </c:otherwise>
    </c:choose>
</div>
