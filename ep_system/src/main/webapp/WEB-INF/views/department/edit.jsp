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
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="padding">
    <form:form modelAttribute="sysDepartment" id="form" class="col-sm-12" method="post">
        <input type="hidden" name="id" value="${sysDepartment.id}">
        <input type="hidden" name="parentCode" value="${parentCode}">
        <div class="row">
            <div class="col-xs-6 form-group">
                <label>部门名称：<span class="red">*</span></label>
                <form:input path="deptname" cssClass="form-control"/>
            </div>
            <div class="col-xs-6 form-group">
                <label>上级部门：</label>
                <span class="form-control">${parentName}</span>
            </div>

            <div class="col-xs-12 form-group form-group-sm">
                <label>描述：</label>
                <form:textarea path="remark" cssClass="form-control" cols="5" rows="5"
                               data-importability="250"></form:textarea>
            </div>
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
                deptname: {required: true}
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: 'post',
                    url: '${ctx}/department/submit.html',
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