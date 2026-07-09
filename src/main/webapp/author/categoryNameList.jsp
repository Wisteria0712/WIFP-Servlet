<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<fieldset class="layui-elem-field">
    <legend>巡检类别管理（双击巡检类别名即可编辑）</legend>
    <div class="layui-field-box">
        <c:forEach items="${categoryNameList}" var="categoryName">
            <blockquote class="layui-elem-quote layui-quote-nm">
                <form action="${pageContext.request.contextPath}/author/ChangeCategoryNameServlet.tran"
                      class="layui-form" name="${categoryName}" method="post">
                    <input type="hidden" name="oldCategoryName" value="${categoryName}"/>
                    <div class="layui-form-item layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" name="categoryName" layui-verify="required"
                                   value="${categoryName}" readonly="true" ondblclick="this.removeAttribute('readonly')"
                                   onblur="this.setAttribute('readonly', 'true')"/>
                        </div>
                        <div class="layui-input-inline">
                            <button class="layui-btn layui-btn-sm layui-bg-cyan" lay-submit>
                                <i class="layui-icon layui-icon-edit"></i>更新
                            </button>
                        </div>
                    </div>
                </form>
            </blockquote>
        </c:forEach>
    </div>
</fieldset>