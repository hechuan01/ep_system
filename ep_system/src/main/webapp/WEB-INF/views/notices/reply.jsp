<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2017/3/21
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="MyPosition"><span class="gray">我的位置：</span> 公告 / 公告管理 / 查看公告</div>
<div class="container-fluid">

    <div class="text-center margin-top-25">
        <span style="font-size: 1.7em;font-weight: bold">${model.title}</span>
    </div>
    <div class="text-center margin-top-25">
        <span style="font-size: 1.1em;font-weight: bold;margin-left: 30px">发布时间：</span><span id="time"> </span>
    </div>
    <c:if test="${model.sysAttachment!=null}"><%--当有附件时--%>
        <div class="text-left  margin-top-25">
            <span style="font-size: 1.1em;font-weight: bold">附件：</span> <span>${model.sysAttachment.attname}</span>
            <a type="button" class=" btn btn-sm btn-primary"
               href="${ctx}/notices/downloadFile.html?attachmentid=${model.sysAttachment.id}"
               class="btn btn-default">下载附件
            </a>
        </div>
    </c:if>
    <div class="margin-top-25">
        ${model.content}
    </div>

    <div class="text-center  margin-top-25">
        <button type="button" class=" btn btn-sm btn-primary" onclick="window.parent.ContentTabs.removePage('查看公告','公告管理')"
                class="btn btn-default">关闭
        </button>
    </div>
</div>

</div>
</body>
</html>
<jsp:include page="../shared/footer.jsp"></jsp:include>

<script type="text/javascript">
    $(function () {
        $("#time").html((new Date("${model.endtime}")).format("yyyy-MM-dd"));

    });
    function read() {
        $.post("${ctx}/notices/read.html", {id: notice.id()},
            function (data) {
                if (data.state) {
                    dialog.closeAll();
                } else {
                    dialog.error(data.message);
                }
            }, "json");

    }
</script>