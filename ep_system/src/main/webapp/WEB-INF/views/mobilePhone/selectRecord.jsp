<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html style="touch-action: none">
<head>
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<meta name="apple-moblie-web-app-capable" content="yes">
<meta name="apple-moblie-web-app-status-bar-style" content="black">
<title>停车记录</title>
<link href="${ctx}/scripts/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${ctx}/css/style.css" rel="stylesheet"/>
<link href="${ctx}/css/mobile.css" type="text/css" rel="stylesheet"/>
<script src="${ctx}/scripts/common/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" href="${ctx}/css/reset.css" />
<link rel="stylesheet" href="${ctx}/css/pullToRefresh.css" />
<style type="text/css" media="all">
body, html {
	padding: 0;
	margin: 0;
	height: 100%;
	font-family: Arial, Microsoft YaHei;
}
.scroller li {
	height:60px;
	border-bottom: 1px solid #eee;
	background-color: #fff;
	font-size: 14px;
	
}
.pullDownLabel img {
	width: 13px;
	height: 13px;
	margin-top: -1px;
	vertical-align: -2px;
	margin-right: 5px;
}
</style>
</head>

<body>
	<div id="wrapper">
		<ul class="list-group" id="body-li">
		</ul>
  	</div>
<script src="${ctx}/scripts/common/iscroll.js"></script>
<script src="${ctx}/scripts/common/pullToRefresh.js"></script>
<script type="text/javascript">
	$(function () {
		selectRecord();
    });
	refresher.init({
		id:"wrapper",
		pullDownAction:Refresh,                                                            
		pullUpAction:Load 																			
	});																																							
	function Refresh() {																
		setTimeout(function () {
			var el, li, i;																		
			el =document.querySelector("#wrapper ul");					
			//这里写你的刷新代码				
			document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";		
			document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='${ctx}/images/ok.png'/>刷新成功";																					 
			setTimeout(function () {
				wrapper.refresh();
				document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";								
				},250);//模拟qq下拉刷新显示成功效果
		}, 250);
	}
	function Load() {
		selectRecord();
		setTimeout(function () {
			wrapper.refresh();
		},500);	
	}
	
    function selectRecord() {
    	var el, li, i;
		el =document.querySelector("#wrapper ul");
		$.ajax({
	        type: 'POST',
	        url: "${ctx}/mobile/recordList.html",
	        dataType: "json",
	        success: function (data) {
	          json = $.parseJSON(data);
	          for (i=0; i<10; i++) {
	        	  li = document.createElement('li');
			      li.innerHTML="<span class='record-li'>-￥"+json[i].cost+"</span>"+
		            "<h4 class='list-group-item-heading' align='left'>"+json[i].carNumber+"</h4>"+
		            "<p class='list-group-item-text' align='left'>"+json[i].parkDate+"~"+json[i].parkDate+"</p>"
				  el.appendChild(li, el.childNodes[0]);
	          }
	        }
	    });
    }
</script>
</body>
</html>