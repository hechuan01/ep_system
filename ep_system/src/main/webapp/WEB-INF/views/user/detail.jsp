<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/21
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="col-sm-12">
    <table class="table table-user-detail" style="margin-top: 20px">
        <tbody>
        <tr>
            <td width="100px" class="text-right">姓名</td>
            <td class="col-sm-9">${user.fullName}</td>
        </tr>
        <tr>
            <td width="100px" class="text-right">角色</td>
            <td class="col-sm-9">${user.role.rolename}</td>
        </tr>
        <tr>
            <td width="100px" class="text-right">账户</td>
            <td class="col-sm-9">${user.loginId}</td>
        </tr>
        <tr>
            <td width="100px" class="text-right">部门</td>
            <td class="col-sm-9">${user.deptName}</td>
        </tr>
        <tr>
            <td width="100px" class="text-right">联系方式</td>
            <td class="col-sm-9">${user.phone}</td>
        </tr>
        <tr>
            <td width="100px" class="text-right">邮箱</td>
            <td class="col-sm-9">${user.email}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
