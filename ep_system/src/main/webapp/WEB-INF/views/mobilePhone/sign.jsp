<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<title>手机签约</title>
<link href="${ctx}/scripts/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${ctx}/css/style.css" rel="stylesheet"/>
<link href="${ctx}/css/mobile.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<div class="container" style="position: inherit;margin-top: 20px;">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12 col-xs-12">
				<div class="posi box_name" >
					<i></i>
					<input class="box" type="text" value="" style="width: 100%;" disabled="disabled" placeholder="账号"/>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12 col-xs-12">
				<div class="posi box_pwd">
					<i></i>
					<input class="box" type="text" value="" style="width: 100%;" placeholder="车牌号"/>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12 col-xs-12" style="margin-top: 20px;">
				<input type="submit" value="绑&nbsp;定" class="login_btn2" disabled="disabled" id="btnLogin"/>
			</div>
		</div>
	</div>
</body>
</html>