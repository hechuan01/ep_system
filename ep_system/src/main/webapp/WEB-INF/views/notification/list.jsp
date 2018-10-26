<%--
  Created by IntelliJ IDEA.
  User: V.E.
  Date: 2017/3/10
  Time: 11:30
  knockoutjs 绑定及分页
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="MyPosition"><span class="gray">我的位置：</span> 消息中心</div>
<div class="operation-bar">
    <form id="form">
        <input data-val="true" data-val-required="The Boolean field is required." id="IsPublic" name="IsPublic"
               type="hidden" value="False">
        <label class="control-label pull-left padding-v-10">状态</label>
        <div class="col-sm-1">
            <select class="form-control" id="state" name="state">
                <option value="">所有</option>
                <option value="0">未读</option>
                <option value="1">已读</option>
            </select>
        </div>
        <label class="control-label pull-left padding-v-10">类型</label>
        <div class="col-sm-1">
            <select class="form-control" id="type" name="type">
                <option value="" ${type==""?'selected':''}>所有</option>
                <option value="0" ${type=="0"?'selected':''}>流程</option>
                <option value="1" ${type=="1"?'selected':''}>公告</option>
                <option value="2" ${type=="2"?'selected':''}>调研</option>
            </select>
        </div>
        <%--<label class="control-label pull-left padding-v-5">专题开始时间</label>--%>
        <%--<div class="col-sm-1">--%>
        <%--<div class="pull-left daterange btn btn-sm btn-default" style="width: 204px;">--%>
        <%--<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp;--%>
        <%--<span class="value">选择时间段...</span> <b class="caret"></b>--%>
        <%--</div>--%>
        <%--</div>--%>
        <a class="btn btn-primary margin-left" onclick="getData()">
            <i class="fa fa-search"></i> 搜索
        </a>
    </form>
    <div class="clearfix"></div>
</div>
<div class="clearfix"></div>
<div id="notificationList" class="col-sm-12">
    <table class="table-xt table-layout">
        <thead>
        <tr>
            <th style="width: 30px;text-align: center;">
            </th>
            <th class="col-xs-3">标题</th>
            <th class="col-xs-1">类型</th>
            <th class="col-xs-1">状态</th>
            <th class="col-xs-2">发送人</th>
            <th class="col-xs-2">时间</th>
            <th style="vertical-align: middle;" class="text-center col-xs-1">操作</th>
        </tr>
        </thead>
        <tbody data-bind="foreach:Items()">
        <tr>
            <td>
                <input type="checkbox" name="checkboxItem" onclick="selected(this)"
                       data-bind="attr:{id:id},value:id,checked:checkSelected(id())"/>
            </td>
            <td>
                <span data-bind="text:title"></span>
            </td>
            <td>
                <span data-bind="text:typeName"></span>
            </td>
            <td>
                <span data-bind="attr:{class:state()==1?'fa fa-envelope-open-o':'fa fa-envelope'}"></span>
            </td>
            <td>
                <span data-bind="text:creater"></span>
            </td>
            <td>
                <span data-bind="text:stringToTime(createtime())"></span>
            </td>
            <td class="text-center">
                <a title="查看" data-toggle="tooltip" data-placement="top"
                   data-bind="click:replyNotification.bind($data)">
                    <i data-bind="attr:{class:type()==1?'fa fa-eye':'fa fa-comments'}"></i>
                </a>
            </td>
        </tr>
        </tbody>
    </table>

    <div class="margin-top-15 pull-left">
        <button type="button" class="btn btn-default" onclick="selectAll()">全选</button>
        <button type="button" class="btn btn-default" onclick="reverseSelect()">反选</button>
        <button type="button" class="btn btn-default" onclick="deleteSelect()">删除所选</button>
        <%--<button type="button" class="btn btn-primary">标记已读</button>--%>
    </div>
    <ko-pager-state params="model: $data"></ko-pager-state>
    <ko-pager params="model: $data"></ko-pager>
</div>

<jsp:include page="../shared/footer.jsp"></jsp:include>
<script>
    var viewModel = new PagerModel("${ctx}/notification/getList.html?type=${type}");
    $(function () {
        ko.applyBindings(viewModel, $("#notificationList")[0]);
    });

    function getData() {
        viewModel.Reload($('#form').serialize());
    }
    var selectedList = new Array();
    // 全选
    function selectAll() {
        var list = $('#notificationList').find('input[name="checkboxItem"]');
        $.each(list, function () {
            var $this = $(this);
            if (!$this.prop('checked')) {
                $this.prop('checked', true);
                selectedList.push($this.val());
            }
        });
    }
    function reverseSelect() {
        var list = $('#notificationList').find('input[name="checkboxItem"]');
        $.each(list, function () {
            var $this = $(this);
            if ($this.prop('checked')) {
                $this.prop('checked', false);
                selectedList.splice($.inArray($this.val(), selectedList), 1);
            } else {
                $this.prop('checked', true);
                selectedList.push($this.val());
            }
        });
    }
    //勾选
    function selected(obj) {
        var $this = $(obj);
        var id = $this.val();
        if ($this.prop('checked')) {
            selectedList.push(id);
        } else {
            selectedList.splice($.inArray(id, selectedList), 1);
        }
    }


    //判断是否已被选中
    function checkSelected(id) {
        if ($.inArray(id.toString(), selectedList) < 0) {
            return false;
        } else
            return true;
    }

    function deleteSelect() {
        dialog.confirm("确定删除已选数据？", function () {
            $.post("${ctx}/notification/deleteNotify.html", {ids: selectedList.toString()}, function (result) {
                dialog.closeAll();
                if (result.state) {
                    dialog.success(result.message);
                    viewModel.Reload();
                } else {
                    dialog.error(result.message);
                }
            }, 'json');
        })
    }

    function replyNotification(notification) {
        if(notification.targettype()==0){//个人的消息不需要操作，查看后即修改状态
            $.post("${ctx}/notification/deleteNotify.html", {ids:notification.id()}, function (result) {
                if (result.state) {
                    viewModel.Reload();
                } else {
                    dialog.error(result.message);
                }
            }, 'json');
        }
        switch (notification.type()) {

            case 0:///流程
                var currentUrl = "${ctx}/flow/flowappliedetail.html?id=" + notification.sourceid();
                window.parent.ContentTabs.addPage('查看申请详情', currentUrl, true);
                break;
            case 1://公告
                window.parent.ContentTabs.addPage('查看公告', '${ctx}/notices/' + notification.sourceid() + '/reply.html?type=user', true);
                break;
            case 2://调研
                window.parent.ContentTabs.addPage('反馈调研', '${ctx}/surveys/' + notification.sourceid() + '/reply.html?type=user', true);
                break;
            default:
                return;
        }


    }
</script>
</body>
</html>