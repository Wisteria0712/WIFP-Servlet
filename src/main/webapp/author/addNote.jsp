<%--
  Date: 2021/12/16
  Time: 13:29
  Author: Amnotgcs
  Description: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加巡检记录</legend>
    <div class="layui-field-box">
        <form class="layui-form" method="post" id="note" name="note">
            <div class="layui-form-item">
                <label class="layui-form-label">巡检记录标题</label>
                <div class="layui-input-block">
                    <input name="noteTitle" class="layui-input" value="${noteForm.noteTitle}" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">巡检记录类别</label>
                <div class="layui-btn-container">
                    <c:forEach items="${categoryNameList}" var="categoryName">
                        <label class="layui-btn layui-btn-xs layui-bg-cyan" onclick="addCategory(this)">
                                ${categoryName}
                        </label>
                    </c:forEach>
                </div>
                <div class="layui-input-block">
                    <input name="categoryName" id="categoryName" class="layui-input" value="${noteForm.categoryName}" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">巡检记录标签</label>
                <div class="layui-btn-container">
                    <c:forEach items="${tagNameList}" var="tagName">
                        <label class="layui-btn layui-btn-xs layui-bg-cyan" onclick="addTag(this)">
                                ${tagName}
                        </label>
                    </c:forEach>
                </div>
                <div class="layui-input-block">
                    <input name="tagNames" id="tagNames" class="layui-input" value="${noteForm.tagNames}" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">巡检记录内容</label>
                <div class="layui-input-block">
                    <textarea name="noteContent" id="noteContent">${noteForm.noteContent}</textarea>
                    <c:remove var="noteForm"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button layui-submit class="layui-btn layui-btn-sm layui-bg-cyan" onclick="saveNote()">
                        <i class="layui-icon layui-icon-add-circle">保存巡检记录内容</i>
                    </button>
                    <button type="reset" class="layui-btn layui-btn-sm layui-bg-cyan">
                        <i class="layui-icon layui-icon-fonts-clear">重置巡检记录内容</i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</fieldset>
<script src="${pageContext.request.contextPath}/resources/ckeditor5/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/resources/ckeditor5/zh-cn.js"></script>
<script>
    ClassicEditor.create(document.querySelector("#noteContent"),{
        toolbar: ['heading','|', 'bold', 'italic', 'link', 'imageUpload', 'blockQuote'],
        language: 'zh-cn',
        ckfinder: {
            uploadUrl: '${pageContext.request.contextPath}/author/UploadNotePhotoServlet'
        }
    });
    let saveNote = function () {
        document.note.action = "${pageContext.request.contextPath}/author/SaveNoteServlet.tran";
        document.node.submit();
    }
    let addCategory = function (btn) {
        let value = btn.innerText;
        let categoryName = document.getElementById("categoryName");
        categoryName.value = value;
    }
    let addTag = function (btn) {
        let value = btn.innerText;
        let tagNames = document.getElementById("tagNames");
        let tagNameValues = tagNames.value;
        if (tagNameValues.indexOf(value) > -1) {
            return;
        }
        tagNames.value = tagNameValues + value + " # ";
    }
</script>