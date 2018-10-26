<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/21
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="padding">
    <div class="row">
        <form id="form" class="col-xs-12 form-inline">
            <input type="hidden" name="id" value="${id}">
            <div class="row">
                <div class="form-group">
                    <label for="oldPwd" class="col-sm-2 control-label">原密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="oldPwd" name="oldPwd" placeholder="********">
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="form-group">
                    <label for="newPwd" class="col-sm-2 control-label">新密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="newPwd" name="newPwd" placeholder="********">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="newPwd2" class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="newPwd2" name="newPwd2" placeholder="********">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 text-center">
                    <button type="submit" class="btn btn-sm btn-primary margin-left-5">修改</button>
                    <button type="button" class="btn btn-sm btn-default"
                            onclick="layerClose();">
                        取消
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../shared/footer.jsp"></jsp:include>
<script type="text/javascript">
    $(function () {
        //提交表单并验证合法性
        $("#form").validate({
            rules: {
                oldPwd: {required: true},
                newPwd: {
                    required: true,
                    maxlength: 20
                },
                newPwd2: {
                    required: true,
                    equalTo: "#newPwd"
                }
            },
            messages: {
                newPwd2: "两次密码输入不一致"
            },
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: 'post',
                    url: '${ctx}/user/changePassword.html',
                    data: $('#form').serialize(),
                    dataType: "json",
                    success: function (data) {
                        debugger
                        if (data.state) {
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
