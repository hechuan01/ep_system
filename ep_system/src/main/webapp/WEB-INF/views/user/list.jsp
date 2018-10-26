<%--
  Created by IntelliJ IDEA.
  User: V.E.
  Date: 2017/3/10
  Time: 11:30
  knockoutjs 绑定及分页
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="MyPosition"><span class="gray">我的位置：</span> 系统管理 / 用户管理</div>
<div class="operation-bar">
    <div class="row">
        <div class="col-xs-6 col-sm-3 pull-left">
            <div class="input-group">
                <input type="text" id="q" name="q" class="form-control" placeholder="按用户姓名检索...">
                <div class="btn btn-primary input-group-addon" onclick="searchList()">搜索</div>
            </div>
        </div>
        <div class="col-xs-6 col-sm-9">
            <div class="pull-right">
                <span class="btn btn-sm btn-primary" onclick="edit()"><i class="fa fa-plus"></i> 新增</span>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
<div class="clearfix"></div>
<div id="userList" class="col-sm-12">
    <table class="table-xt table-layout">
        <thead>
        <tr>
            <th class="width-40">序号</th>
            <th>姓名</th>
            <th>登录账户</th>
            <th>性别</th>
            <th>部门</th>
            <th>联系方式</th>
            <th style="vertical-align: middle;" class="text-center">操作</th>
        </tr>
        </thead>
        <tbody data-bind="foreach:Items()">
        <tr>
            <td data-bind="text:$index()+1+$root.PageSize()*($root.CurrentPage()-1)"></td>
            <td>
                <span data-bind="text:fullname"></span>
            </td>
            <td>
                <span data-bind="text:loginid"></span>
            </td>
            <td>
                <span data-bind="text:sex()=='0'?'保密':(sex()=='1'?'男':'女')"></span>
            </td>
            <td>
                <span data-bind="text:sysDepartment!=null ? sysDepartment.deptname:''"></span>
            </td>
            <td>
                <span data-bind="text:mobile"></span>
            </td>
            <td class="text-center">
                <a title="编辑" data-toggle="tooltip" data-placement="top" data-bind="click:edit.bind($data,id())">
                    <i class="fa fa-edit"></i>
                </a>
                <a title="删除" data-toggle="tooltip" data-placement="top" data-bind="click:deleteUser">
                    <i class="fa fa-trash-o"></i>
                </a>
            </td>
        </tr>
        </tbody>
    </table>
    <ko-pager-state params="model: $data"></ko-pager-state>
    <ko-pager params="model: $data"></ko-pager>
</div>
<jsp:include page="../shared/footer.jsp"></jsp:include>
<script>
    var viewModel = new PagerModel("${ctx}/user/getList.html");

    $(function () {
        ko.applyBindings(viewModel, $("#userList")[0]);
    });

    function edit(id) {
        dialog.iframe('${ctx}/user/edit.html?id=' + (id == undefined ? "" : id), {
            title: id != undefined ? '编辑用户' : '添加用户',
            size: [600, 500]
        }, function (result) {
            if (result) {
                viewModel.Reload();
            }
        });
    }
    function deleteUser(item) {
        dialog.confirm('确认删除《' + item.loginid() + '》用户吗？', function () {
            $.post('${ctx}/user/delete.html', {id: item.id()}, function (result) {
                dialog.closeAll();
                if (result.state) {
                    dialog.success(result.message);
                    viewModel.Reload();
                } else {
                    dialog.error(result.message);
                }
            }, 'json');
        });

    }
    function searchList() {
        var q = $('#q').val();
        viewModel.Reload('fullName=' + q);
    }
</script>
</body>
</html>