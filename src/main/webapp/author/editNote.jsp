<%--
  Date: 2021/12/19
  Time: 14:21
  Author: Amnotgcs
  Description: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>编辑巡检记录</legend>
    <div class="layui-field-box">
        <form class="layui-form" method="post" id="note" name="note">
            <input type="hidden" name="noteID" value="${noteForm.noteID}" />
            <div class="layui-form-item">
                <label class="layui-form-label">巡检记录标题</label>
                <div class="layui-input-block">
                    <input name="noteTitle" class="layui-input" value="${noteForm.noteTitle}" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">巡检类别</label>
                <div class="layui-btn-container">
                    <c:forEach items="${allCategoryNameList}" var="allCategoryName">
                        <label class="layui-btn layui-btn-xs layui-bg-cyan" onclick="addCategory(this)">
                            ${allCategoryName}
                        </label>
                    </c:forEach>
                </div>
                <div class="layui-input-block">
                    <input name="categoryName" id="categoryName" class="layui-input" value="${noteForm.categoryName}" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">巡检标签</label>
                <div class="layui-btn-container">
                    <c:forEach items="${allTagNameList}" var="allTagName">
                        <label class="layui-btn layui-btn-xs layui-bg-cyan" onclick="addTag(this)">
                            ${allTagName}
                        </label>
                    </c:forEach>
                </div>
                <div class="layui-input-block">
                    <input name="tagNames" id="tagNames" class="layui-input"
                           value="<c:forEach items="${tagNameList}" var="tagName">${tagName} #</c:forEach>" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">巡检内容</label>
                <div class="layui-input-block">
                    <textarea name="noteContent" id="noteContent">
                        ${noteForm.noteContent}
                    </textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button layui-submit class="layui-btn layui-btn-sm layui-bg-cyan" onclick="changeNote()">
                        <i class="layui-icon layui-icon-edit">更新信息巡检内容</i>
                    </button>
                    <button type="reset" class="layui-btn layui-btn-sm layui-bg-cyan" onclick="deleteNote()">
                        <i class="layui-icon layui-icon-fonts-clear">删除巡检内容</i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</fieldset>

<script src="${pageContext.request.contextPath}/resources/ckeditor5/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/resources/ckeditor5/zh-cn.js"></script>
<script>
    ClassicEditor.create(document.querySelector('#noteContent'),{
        toolbar: ['heading', '|', 'bold', 'italic', 'link', 'imageUpload', 'blockQuote'],
        language: 'zh-cn',
        ckfinder: {
            uploadUrl: '${pageContext.request.contextPath}/author/UploadNotePhotoServlet'
        }
    });
function changeNote() {
    document.note.action = "${pageContext.request.contextPath}/author/ChangeNoteServlet.tran";
    document.note.submit();
}
function deleteNote() {
    layui.use(['layer'], function () {
        let layer = layui.layer;
        layer.confirm('确定删除吗？', {
            btn: ['确定删除', '取消删除']
        }, function () {
            document.note.action = "${pageContext.request.contextPath}/author/DeleteNoteServlet.tran";
            document.note.submit();
        }, function () {

        });
    });
}
let addCategory = function (btn) {
    let value = btn.innerText;
    let categoryName = document.getElementById("categoryName");
    categoryName.value = value;
}
let addTag = function (btn) {
    let value = btn.innerText;
    let tagNames = document.getElementById('tagNames');
    let tagNameValues = tagNames.value;
    if (tagNameValues.indexOf(value) > -1) {
        return;
    }
    tagNames.value = tagNameValues + value + "#";
}
</script>
