<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/11
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="padding">
    <form:form modelAttribute="sysModule" id="form" class="col-sm-12" method="post">
        <input type="hidden" name="id" value="${sysModule.id}">
        <input type="hidden" name="parentCode" value="${sysModule.parentCode}">
        <div class="row">
            <div class="form-group form-group-sm col-xs-6">
                <label>模块名称: <span class="red">*</span></label>
                <form:input path="mname" cssClass="form-control"/>
            </div>
            <div class="form-group form-group-sm col-xs-6">
                <label>上级模块: </label>
                <div class="form-control"> ${sysModule.parentName==""?"无":sysModule.parentName}</div>
            </div>
        </div>
        <div class="row">
            <div class="form-group form-group-sm col-xs-6">
                <label>模块分类: <span class="red">*</span> </label>
                <div>
                    <select id="mtype" name="mtype" class="form-control">
                        <option value="0" ${sysModule.mtype eq 0 ?"selected=selected":""}>菜单</option>
                        <option value="1" ${sysModule.mtype eq 1 ?"selected=selected":""}>功能</option>
                    </select>
                </div>
            </div>
            <div class="form-group form-group-sm col-xs-6">
                <label>Icon图标: </label>
                <form:input path="micon" cssClass="form-control" placeholder="Font Awesome图标名称"/>
            </div>
        </div>

        <div class="row">

            <div class="form-group form-group-sm col-xs-6">
                <label>显示顺序: </label>
                <form:input type="number" path="sortnum" cssClass="form-control"/>
            </div>
        </div>

        <div class="row">
            <div class="form-group form-group-sm col-xs-12">
                <label>访问地址: </label>
                <div>
                    <form:input path="murl" cssClass="form-control"/>
                </div>
            </div>
        </div>
        <div class="form-group form-group-sm">
            <label>备注说明: </label>
            <form:textarea path="remark" cssClass="form-control" cols="5" rows="5"
                           data-importability="250"></form:textarea>
        </div>
        <button type="submit" class="btn btn-primary pull-right">保存</button>
    </form:form>
</div>
<jsp:include page="../shared/footer.jsp"></jsp:include>
<script type="text/javascript">

    $(function () {
        //提交表单并验证合法性
        $("#form").validate({
            rules: {
                mname: {required: true}
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: 'post',
                    url: '${ctx}/module/submit.html',
                    data: $('#form').serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.state) {
                            dialog.success(data.message);
                            dialog.closeAll(true);
                        } else {
                            dialog.error(data.message);
                        }
                    }
                });
                return false; // 阻止表单自动提交事件
            }
        });
    });
</script>

</body>
</html>