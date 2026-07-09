<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<fieldset class="layui-elem-field layui-field-title">
    <legend>我的评论</legend>
    <div class="layui-field-box">
        <ul class="layui-timeline">
            <c:forEach items="${commentList}" var="comment">
                <li class="layui-timeline-item">
                    <i class="layui-icon layui-icon-date layui-timeline-axis"></i>
                    <div class="layui-timeline-content layui-text">
                        <h3 class="layui-timeline-content layui-text">
                                ${comment.createTime} | ${comment.remoteIP}
                        </h3>
                        <div>
                            <fieldset class="layui-elem-field">
                                <legend>${comment.commentTitle}</legend>
                                <div class="layui-field-box">
                                        ${comment.commentContent}
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </li>
            </c:forEach>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-icon-date layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">过去</h3>
                </div>
            </li>
        </ul>
    </div>
</fieldset>