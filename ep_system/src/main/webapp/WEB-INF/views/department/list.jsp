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
<div class="MyPosition"><span class="gray">我的位置：</span> 系统管理 / 部门管理</div>
<div class="col-sm-12 margin-top-15">
    <div class="tree-list">
        <div class="row header">
            <div class="col-xs-4">名称</div>
            <div class="col-xs-7">说明</div>
            <div class="col-xs-1 text-center">操作</div>
        </div>
        <div data-bind="template: {name: 'node-template', foreach: items}"></div>
    </div>
    <script type="text/html" id="node-template">
        <div class="row" data-bind="attr: {id: 'node_' + code,'data-code':value}">
            <div class="col-xs-4">
                <div class="indent">
                    <a data-bind="visible: childNodes.length > 0,click: $root.toggleChildren">
                        <i class="fa fa-fw fa-angle-down"></i>
                    </a>
                    <span style="color: #54b4eb"><i data-bind="attr: { 'class' : 'fa fa-fw fa-' + img }"></i></span>
                    <span data-bind="text:text"></span>
                </div>
            </div>
            <div class="col-xs-7" data-bind="text:extra.remark"></div>
            <div class="col-xs-1 text-center">
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
        //self.selectedItem = ko.observable({ text: '' });
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
            dialog.iframe('${ctx}/department/edit.html?parentCode=' + item.code + '&deptname=' + encodeURIComponent(item.text), {
                title: "新增部门",
                size: [500, 350]
            }, function (result) {
                if (result) {
                    viewModel.reload();
                }
            });
        };
        //编辑
        self.edit = function (item) {
            dialog.iframe('${ctx}/department/edit.html?id=' + item.id + '&parentCode=' + item.code, {
                title: "编辑部门",
                size: [500, 350]
            }, function (result) {
                if (result) {
                    viewModel.reload();
                }
            });
        };
        //删除
        self.delete = function (item, obj) {
            dialog.confirm('确认删除《' + item.text + '》部门吗？', function () {
                $.getJSON('${ctx}/department/delete.html?id=' + item.id, function (data) {
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
            $.getJSON("${ctx}/department/treeJson.html", function (data) {
                viewModel.items(data);
            });
        }
    }

    //绑定数据
    var viewModel = new ViewModel();
    $.getJSON("${ctx}/department/treeJson.html", function (data) {
        viewModel.items(data);
    });
    ko.applyBindings(viewModel);
</script>

</body>
</html>
