<%@ page import="com.zx.system.model.SysModule" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/17
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<form id="form" class="form" style="padding: 20px 30px;" method="post">
    <input type="hidden" id="id" name="id" value="${id}">
    <input type="hidden" id="checkedIDs" name="checkedIDs" value="${mids}">
    <div class="dialog-body">
        <div class="form-group form-group-sm">
            <div class="tree-list">
                <!-- ko foreach:items() -->
                <div class="row">
                    <div class="checkbox">
                        <input class="icheckbox" type="checkbox"
                               data-bind="attr:{id:id,value:id,checked:checkSelected(id)}" name="character">
                        <label data-bind="text:text"></label>
                    </div>
                </div>
                <div class="child">
                    <!-- ko foreach:childNodes -->
                    <div class="row">
                        <div class="checkbox indent">
                            <input class="icheckbox" type="checkbox"
                                   data-bind="attr:{id:id,value:id,checked:checkSelected(id)}" name="character">
                            <label data-bind="text:text"></label>
                        </div>
                    </div>
                    <div class="child">
                        <!-- ko foreach:childNodes -->
                        <div class="row">
                            <div class="checkbox indent indent">
                                <input class="icheckbox" type="checkbox"
                                       data-bind="attr:{id:id,value:id,checked:checkSelected(id)}" name="character">
                                <label data-bind="text:text"></label>
                            </div>
                        </div>
                        <!-- /ko -->
                    </div>
                    <!-- /ko -->
                </div>
                <!-- /ko -->
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <button type="submit" class="btn btn-primary pull-right">保存</button>
        </div>
    </div>
</form>
<jsp:include page="../shared/footer.jsp"></jsp:include>
<script type="text/javascript">
    function ViewModel() {
        var self = this;
        self.items = ko.observableArray([]);
    }

    //绑定数据
    var viewModel = new ViewModel();
    $.getJSON("${ctx}/module/treeJson.html", function (data) {
        viewModel.items(data);
    });
    ko.applyBindings(viewModel);
    window.onload = function () {
        $('.checkbox').find('input:checkbox').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue',
            increaseArea: '20%'
        });
        $('input[type=checkbox]').on('ifChecked', function (event) {
            $(this).closest(".row").next(".child").find("input:checkbox").iCheck('check');
        });
        $('input[type=checkbox]').on('ifUnchecked', function (event) {
            $(this).closest(".row").next(".child").find("input:checkbox").iCheck('uncheck');
        });
    }

    var selectedList = new Array();
    selectedList = "${mids}".split(',');
    //判断是否已被选中
    function checkSelected(id) {
        if ($.inArray(id.toString(), selectedList) < 0) {
            return false;
        } else
            return true;
    }

    //提交表单
    $("#form").validate({
        submitHandler: function (form) {
            debugger
            var checkedIDs = getChkBoxIds("character");
            $('#checkedIDs').val(checkedIDs);
            var url = '${ctx}/role/submitModule.html';
            $("#form").ajaxSubmit({
                type: 'post',
                url: url,
                dataType: "json",
                data: $('#form').serialize(),
                success: function (data) {
                    debugger
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

</script>
</body>
</html>
