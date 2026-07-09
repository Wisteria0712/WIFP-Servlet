<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div class="layui-card">
    <div class="layui-card-header" style="font-size: 32px">
        <div style='word-break: break-all;word-wrap: break-word'>${note.noteTitle}</div>
        <c:if test="${user.isAuthor == 'Y'}">
            <a class="layui-btn layui-layout-right"
               href="${pageContext.request.contextPath}/author/EditNoteServlet.tran?noteID=${note.noteID}">
                编辑巡检反馈信息
            </a>
        </c:if>
    </div>
    <div class="layui-card-body">
        <span class="layui-badge layui-bg-gray">作者：${note.author}</span> |
        <span class="layui-badge layui-bg-gray">发布时间：${note.createTime}</span> |
        <c:if test="${not empty note.updateTime}">
        <span class="layui-badge layui-bg-gray">修改时间：${note.updateTime}</span> |
        </c:if>
        <span class="layui-badge layui-bg-gray">浏览器次数：${note.visit}</span> |
        <span class="layui-badge layui-bg-gray">巡检反馈类别：${note.categoryName}</span>
        <hr class="layui-bg-gray">
        巡检反馈标签：
        <c:forEach items="${tagNameList}" var="tagName">
        <span class="layui-badge layui-bg-gray">${tagName}</span>
        </c:forEach>
        <hr class="layui-bg-gray" />
        <div style='word-break: break-all;word-wrap: break-word'>${note.noteContent}</div>
        <hr class="layui-bg-green" />上一篇:
        <a href="${pageContext.request.contextPath}/ReadNoteServlet.tran?noteID=${lastNote.noteID}">
            ${lastNote.noteTitle}
        </a>
        <hr class="layui-bg-green" />下一篇:
        <a href="${pageContext.request.contextPath}/ReadNoteServlet.tran?noteID=${nextNote.noteID}">
            ${nextNote.noteTitle}
        </a>
    </div>
</div>
<hr class="layui-bg-cyan">
<div class="layui-card">
    <div class="layui-card-header" style="font-size: 20px">
        反馈列表
    </div>
    <div class="layui-card-body">
        <c:forEach items="${commentList}" var="comment">
            <li class="layui-timeline-item">
                <img id="photo" class="layui-timeline-axis" title="${comment.user.brief}"
                     src="${pageContext.request.contextPath}/ShowUserPhotoServlet?fileName=${comment.user.photo}">
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">
                        <span class="layui-badge layui-bg-gray">用户名：${comment.userName}</span>
                        <span class="layui-badge layui-bg-gray">昵称：${comment.user.nickName}</span>
                        <span class="layui-badge layui-bg-gray">反馈时间：${comment.createTime}</span>
                        <span class="layui-badge layui-bg-gray">IP地址：${comment.remoteIP}</span>
                    </h3>
                </div>
                <div>
                    <fieldset class="layui-elem-field">
                        <legend>${comment.commentTitle}</legend>
                        <div class="layui-field-box">${comment.commentContent}</div>
                    </fieldset>
                </div>
            </li>
        </c:forEach>
    </div>
</div>
<hr class="layui-bg-green" />
<div class="layui-card">
    <c:if test="${empty user}">
    <div class="layui-card-header" style="font-size: 20px">
        登陆后，可反馈信息
    </div>
    </c:if>
    <div class="layui-card-body">
        <c:if test="${not empty user}">
            <form class="layui-form" action="${pageContext.request.contextPath}/user/SaveCommentServlet.tran" method="post">
                <input type="hidden" name="noteID" value="${note.noteID}" />
                <input name="commentTitle" layui-verify="required" placeholder="请输入反馈的标题"
                       class="layui-input" value="${commentForm.commentTitle}" />
                <textarea id="commentContent" name="commentContent">
                    ${commentForm.commentContent}
                </textarea>
                <button type="submit" class="layui-btn layui-bg-green">反馈信息</button>
                <c:remove var="commentForm"/>
            </form>
        </c:if>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/ckeditor5/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/resources/ckeditor5/zh-cn.js"></script>
<script>
    let commentContent = ClassicEditor.create(document.querySelector('#commentContent'),{
        toolbar: ['heading', '|', 'bold', 'italic', 'link', 'blockQuote'],
        language: 'zh-cn',
    });
</script>