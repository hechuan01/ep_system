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
<form:form id="form" modelAttribute="sysUser" action="submit.html" class="col-sm-12 margin-top-15"
           method="post">
    <input type="hidden" name="id" value="${sysUser.id}">
    <input type="hidden" name="isNew" value="${isNew}">
    <div class="row">
        <div class="col-xs-6 form-group">
            <label>登录账户：<span class="red">*</span></label>
            <input type="text" name="loginid" value="${sysUser.loginid}" class="form-control">
        </div>
        <div class="col-xs-6 form-group">
            <label>密码：</label>
            <input type="password" name="psd"
                   class="form-control ${isNew?"required":""}" placeholder="************">
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6 form-group">
            <label>用户姓名：<span class="red">*</span></label>
            <input type="text" name="fullname" value="${sysUser.fullname}" class="form-control">
        </div>
        <div class="col-xs-6 form-group">
            <label>邮箱：<span class="red">*</span></label>
            <input type="text" name="email" value="${sysUser.email}" class="form-control required email">
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6 form-group">
            <label>角色：<span class="red">*</span></label>
                <%--<input type="text" name="sysUser.sysRole[0]." value="${sysUser.sysRole.}" class="form-control">--%>
            <form:select items="${roles}" path="sysRole.id" itemLabel="rolename" itemValue="id"
                         cssClass="form-control"></form:select>
        </div>
        <div class="col-xs-6 form-group">
            <label>部门：<span class="red">*</span></label>
                <%--<input type="text" name="deptcode" value="${sysUser.deptcode}" class="form-control">--%>
            <form:select items="${departments}" path="deptcode" itemLabel="deptname" itemValue="deptcode"
                         cssClass="form-control"></form:select>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6 form-group">
            <label>手机：<span class="red">*</span></label>
            <input type="text" name="phone" value="${sysUser.phone}" class="form-control">
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 form-group">
            <label class="pull-left" style="margin-top: 11px;">性别：</label>
            <div class="radio pull-left">
                <label class="radio-inline">
                    <input type="radio" name="Sex" value="0" ${sysUser.sex == 0 ? "checked" : ""}> 未知
                </label>
                <label class="radio-inline">
                    <input type="radio" name="Sex" value="1" ${sysUser.sex == 1 ? "checked" : ""}> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="Sex" value="2" ${sysUser.sex == 2 ? "checked" : ""}> 女
                </label>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <button type="submit" class="btn btn-primary pull-right">保存</button>
        </div>
    </div>

</form:form>
<jsp:include page="../shared/footer.jsp"></jsp:include>
<script type="text/javascript">
    $(function () {
        //提交表单并验证合法性
        $("#form").validate({
            rules: {
                loginid: {required: true, maxlength: 20, minlength: 6},
                fullname: {required: true, maxlength: 30},
                psd: {maxlength: 20, minlength: 6},
                email: {email: true},
                mobile: {required: true},
                deptcode: {required: true}
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: 'post',
                    url: '${ctx}/user/submit.html',
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