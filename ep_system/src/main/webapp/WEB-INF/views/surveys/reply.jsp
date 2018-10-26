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
<div class="MyPosition"><span class="gray">我的位置：</span> 调研 /反馈调研</div>
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
    <c:if test="${type!='manager'}"><%--当不是管理员时显示新增按钮--%>
        <div class="row margin-top-25">
            <div class="col-md-3 col-lg-3 text-right" style="font-size: 1.2em;font-weight: bold">反馈信息:</div>
            <div class="col-md-6 col-lg-6 text-left">
            <textarea id="content" name="content" rows="20">
            </textarea>
            </div>
        </div>
    </c:if>
    <div class="text-center  margin-top-25">
        <c:if test="${type!='manager'}"><%--当不是管理员时显示新增按钮--%>
            <button class=" btn btn-sm btn-primary" onclick="feedback()">反馈</button>
        </c:if>
        <button type="button" class=" btn btn-sm btn-primary" onclick="window.parent.ContentTabs.removePage('反馈调研','调研管理')"
                class="btn btn-default">关闭
        </button>
    </div>


</div>

</div>
</body>
</html>
<jsp:include page="../shared/footer.jsp"></jsp:include>

<script type="text/javascript">
    <c:if test="${type!='manager'}"><%--当不是管理员时显示新增按钮--%>
    //初始化富文本编辑器
    var editor = new wangEditor('content');
    editor.create();
    <c:if test="${model.sysReceipt!=null}"><%--当有附件时--%>
    editor.$txt.html('${model.sysReceipt.content}');
    </c:if>

    </c:if>
    $(function () {
        $("#time").html((new Date("${model.endtime}")).format("yyyy-MM-dd"));


    });
    function feedback() {
        $.post("${ctx}/surveys/feedback.html", {id: ${model.id}, content: editor.$txt.html()},
            function (data) {
                if (data.state) {
                    dialog.success(data.message);
                    window.parent.ContentTabs.removePage('反馈调研','调研管理')
                } else {
                    dialog.error(data.message);
                }
            }, "json");
    }

</script>