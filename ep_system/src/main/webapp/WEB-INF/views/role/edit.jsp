<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/11
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/shared/head.jsp"></jsp:include>
</head>
<body>
<div class="padding">
    <form:form modelAttribute="sysRole" id="form" action="submit.html" class="col-sm-12" method="post">
        <input type="hidden" name="id" value="${sysRole.id}">
        <div class="row">
            <div class="col-xs-6 form-group">
                <label>角色名：<span class="red">*</span></label>
                    <%--<input type="text" name="rolename" value="${sysRole.rolename}" class="form-control">--%>
                <form:input path="rolename" cssClass="form-control"/>
            </div>
            <div class="col-xs-6 form-group">
                <label>角色类型：<span class="red">*</span></label>
                    <%--<input type="text" name="roletype" value="${sysRole.roletype}" class="form-control">--%>
                <form:select items="${roleType}" path="roletype" itemLabel="Text" itemValue="Value"
                             cssClass="form-control"></form:select>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 form-group form-group-sm">
                <label>描述：</label>
                <form:textarea path="remark" cssClass="form-control" cols="20" rows="5"
                               data-importability="250"></form:textarea>

                    <%--<textarea class="form-control" cols="20" data-importability="250" id="remark" name="remark"--%>
                    <%--rows="4">${sysRole.remark}</textarea>--%>
            </div>
        </div>
        <button type="submit" class="btn btn-primary pull-right">保存</button>
    </form:form>
</div>
<jsp:include page="/WEB-INF/views/shared/footer.jsp"></jsp:include>
<script type="text/javascript">

    $(function () {
        //提交表单并验证合法性
        $("#form").validate({
            rules: {
                rolename: {required: true, maxlength: 20, minlength: 6},
                roletype: {required: true}
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: 'post',
                    url: '${ctx}/role/submit.html',
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