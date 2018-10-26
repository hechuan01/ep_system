<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link href="${ctx}/favicon.ico" rel="shortcut icon" />
    <link href="${ctx}/scripts/plugins/alertifyjs/css/alertify.min.css" rel="stylesheet"/>
    <link href="${ctx}/css/login.css" type="text/css" rel="stylesheet"/>
    <script src="${ctx}/scripts/common/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            $("body").css('height', document.documentElement.clientHeight);
        };
    </script>
</head>
<body>
<form action="" id="form">
    <div class="container">
        <div class="content">
            <div class="content_in">
                <div class="logo"><img src="../images/login/logo.png"/>作战任务协同及辅助决策系统<br><span>&nbsp;Combat task coordination and decision support system</span></div>
                <div class="posi box_name">
                    <i></i>
                    <input id="loginid" name="loginid" type="text" class="box" value="" placeholder="用户名"/>
                </div>
                <div class="posi box_pwd">
                    <i></i>
                    <input id="psd" name="psd" type="password" class="box" value="" placeholder="密码"/>
                </div>
                <div class="posi box_code">
                    <i></i>
                    <div class="code">
                        <img title="看不清楚,换一张？" style="cursor: pointer;" id="captchaRefresh" src="${ctx}/ImageServlet">
                    </div>
                    <input name="captcha" id="captcha" type="text" class="box" value="" placeholder="验证码">
                </div>
                <div class="clear"></div>
                <div class="errMsg" id="errMsg"></div>
                <input type="submit" value="登&nbsp;录" class="login_btn disabled" disabled="disabled" id="btnLogin"/>
            </div>
        </div>
    </div>
</form>

<script src="${ctx}/scripts/plugins/alertifyjs/alertify.js"></script>
<script src="${ctx}/scripts/plugins/layer/layer.js"></script>
<script src="${ctx}/scripts/common/jquery.form.js"></script>
<script src="${ctx}/scripts/common/jquery.validate.js"></script>
<script src="${ctx}/scripts/common/messages_zh.js"></script>
<script src="${ctx}/scripts/common/dialog.js"></script>

<script type="text/javascript">
    $(function () {

        $(function () {
            $('#loginid').focus();
        });
        $('#form input').on('click input propertychange', function () {
            $('#errMsg').text('');
            checkSubmit();
        });

        $('#captchaRefresh').click(function () {
            captchaRefresh();
        });
        function captchaRefresh() {
            $('#captchaRefresh').attr('src', "${ctx}/ImageServlet?" + Math.random());
        }

        function checkSubmit() {
            if ($('#loginid').val() != '' && $('#psd').val() != '' && $('#captcha').val() != '') {
                $('#btnLogin').removeClass('disabled');
                $('#btnLogin').removeAttr('disabled');
            }
        }

        //提交表单并验证合法性
        $("#form").validate({
            rules: {
                loginid: {required: true},
                psd: {required: true},
                captcha: {required: true}
            },
            submitHandler: function (form) {
                $('#btnLogin').text('登录中...');
                $('#btnLogin').addClass('disabled');
                $('#form').ajaxSubmit({
                    type: 'post',
                    url: '${ctx}/home/loginSubmit.html',
                    data: $('#form').serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.state) {
                            $('#errMsg').css('color', '#187f8e');
                            $('#errMsg').text(data.message);
                            location.href = '${ctx}/home/index.html';
                        } else {
                            $('#errMsg').text(data.message);
                            captchaRefresh();
                        }
                        $('#btnLogin').text('登录');
                        $('#btnLogin').removeClass('disabled');
                    },
                    error: function () {
                        $('#errMsg').text('系统异常，请重新登录！');
                        $('#btnLogin').text('登录');
                        $('#btnLogin').removeClass('disabled');
                    }
                });
                return false; // 阻止表单自动提交事件
            }
        });
    });
</script>
</body>
</html>
