<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/14
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="MyPosition"><span class="gray">我的位置：</span> 系统管理 / 菜单管理</div>
<div class="operation-bar">
    <div class="pull-right">
        <span class="btn btn-sm btn-primary" onclick="viewModel.add()"><i class="fa fa-plus"></i> 新增</span>
    </div>
    <div class="clearfix"></div>
</div>
<div class="col-sm-12">
    <div class="tree-list">
        <div class="row header">
            <div class="col-xs-3">名称</div>
            <div class="col-xs-4">访问地址</div>
            <div class="col-xs-2">序号</div>
            <div class="col-xs-1">分类</div>
            <div class="col-xs-2 text-center">操作</div>
        </div>
        <div data-bind="template: {name: 'node-template', foreach: items}"></div>
    </div>
    <script type="text/html" id="node-template">
        <div class="row" data-bind="attr: {id: 'node_' + code,'data-code':value}">
            <div class="col-xs-3">
                <div class="indent">
                    <a data-bind="visible: childNodes.length > 0,click: $root.toggleChildren">
                        <i class="fa fa-fw fa-angle-down"></i>
                    </a>
                    <span style="color: #54b4eb"><i data-bind="attr: { 'class' : 'fa fa-fw fa-' + img }"></i></span>
                    <span data-bind="text:text"></span>
                </div>
            </div>
            <div class="col-xs-4" data-bind="text:extra.murl"></div>
            <div class="col-xs-2" data-bind="text:extra.sortNum"></div>
            <div class="col-xs-1" data-bind="text:extra.moduleType"></div>
            <div class="col-xs-2 text-center">
                <a data-bind="click: $root.add" title="新增" data-toggle="tooltip" data-placement="top"><i
                        class="fa fa-plus"></i></a>
                <a data-bind="click: $root.edit" title="编辑" data-toggle="tooltip" data-placement="top"><i
                        class="fa fa-edit"></i> </a>
                <a data-bind="click: $root.delete" title="删除" data-toggle="tooltip" data-placement="top"><i
                        class="fa fa-trash-o"></i></a>

            </div>
        </div>
        <div class="child"
             data-bind="attr: {id: 'children_' + code }, template: {name: 'node-template', foreach: childNodes} ">
        </div>
    </script>
</div>
<jsp:include page="../shared/footer.jsp"></jsp:include>
<script type="text/javascript">
    function ViewModel() {
        var self = this;
        self.items = ko.observableArray([]);
        self.toggleChildren = function (item) {
            var subEle = $('#children_' + item.code);
            var nodeEle = $('#node_' + item.code);
            var expand = subEle.is(':visible');
            if (expand) {
                nodeEle.find('.fa-angle-down').removeClass('fa-angle-down').addClass('fa-angle-right');
            } else {
                nodeEle.find('.fa-angle-right').removeClass('fa-angle-right').addClass('fa-angle-down');
            }
            subEle.slideToggle();
        };
        //新增
        self.add = function (item) {
            var pCode = "";
            var pName = "";
            if (item != undefined) {
                pCode = item.code;
                pName = item.text;
            }
            dialog.iframe('${ctx}/module/add.html?parentCode=' + pCode + '&parentName=' + pName, {
                title: "新增模块",
                size: [600, 550]
            }, function (result) {
                if (result) {
                    viewModel.reload();
                }
            });
        };
        //编辑
        self.edit = function (item) {
            dialog.iframe('${ctx}/module/edit.html?id=' + item.id, {
                title: "编辑模块",
                size: [600, 550]
            }, function (result) {
                if (result) {
                    viewModel.reload();
                }
            });
        };
        //删除
        self.delete = function (item, obj) {
            dialog.confirm('确认删除《' + item.text + '》菜单吗？', function () {
                $.getJSON('${ctx}/module/delete.html?id=' + item.id, function (data) {
                    dialog.closeAll();
                    if (data.state) {
                        dialog.success(data.message);
                        viewModel.reload();
                    } else {
                        dialog.error(data.message);
                    }
                });
            });
        };

        self.reload = function () {
            $.getJSON("${ctx}/module/treeJson.html", function (data) {
                viewModel.items(data);
            });
        }
    }

    //绑定数据
    var viewModel = new ViewModel();
    $.getJSON("${ctx}/module/treeJson.html", function (data) {
        viewModel.items(data);
    });
    ko.applyBindings(viewModel);


    function addFirstLevel() {
        viewModel.add();
    }
</script>

</body>
</html>
