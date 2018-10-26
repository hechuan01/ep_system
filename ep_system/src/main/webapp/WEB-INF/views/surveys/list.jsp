<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2017/3/14
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="MyPosition"><span class="gray">我的位置：</span> 调研 / 调研列表</div>
<div class="condition">
    <div class="operation-bar">
        <div class="row">
            <div class="col-xs-6 col-sm-3">
                <div class="pull-left">
                    <input type="text" id="q" name="q" class="form-control" placeholder="按调研名检索...">
                </div>
                <div class="pull-left">
                    <span class="btn btn-sm btn-primary form-control" style="margin-left: 20px" onclick="searchList()"> 搜索</span>
                </div>
            </div>
            <div class="col-xs-6 col-sm-9">
                 <div class="pull-right">
                     <span class="btn btn-sm btn-primary " onclick="addSurveys()"><i class="fa fa-plus"></i> 新增</span>
                 </div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>


    <div id="NoticesList" class="col-sm-12">
        <table class="table-xt table-layout">
            <thead>
            <tr>
                <th class="col-xs-1" style="width:40px" >序号</th>
                <th>调研编号</th>
                <th>标题</th>
                <th>结束时间</th>
                <th>状态</th>
                <th>发布人</th>
                <th style="vertical-align: middle;" class="text-center col-xs-1">操作</th>
            </tr>
            </thead>
            <tbody data-bind="foreach:Items()">
            <tr>
                <td data-bind="text:$index()+1+$root.PageSize()*($root.CurrentPage()-1)"></td>
                <td data-bind="text:svnumber"></td>
                <td data-bind="text:title"></td>
                <td data-bind="text:stringToDate(endtime())"></td>

                <td data-bind="text:'${type}'=='manager'?stateName():readState()"></td>
                <td data-bind="text:creatername"></td>
                <td>
                    <a title="查看" data-bind="click:replySurveys.bind()">
                        <i class="fa fa-search"></i>
                    </a>
                    <c:if test="${type=='manager'}"><%--当为管理员时显示编辑删除按钮--%>
                        <a title="发布" data-bind="click:releaseSurveys.bind(),visible:state()==0">
                            <i class="fa fa-share"></i>
                        </a>
                        <a title="编辑" data-bind="click:editSurveys.bind(),visible:state()==0">
                            <i class="fa fa-edit"></i>
                        </a>
                        <a title="删除" data-bind="click:delSurveys.bind()">
                            <i class="fa fa-trash-o"></i>
                        </a>
                    </c:if>
                </td>


            </tr>
            </tbody>
        </table>
        <ko-pager-state params="model: $data"></ko-pager-state>
        <ko-pager params="model: $data"></ko-pager>
    </div>
</div>
</body>
</html>
<jsp:include page="../shared/footer.jsp"></jsp:include>
<script type="text/javascript">

    function addSurveys() {
        window.parent.ContentTabs.addPage('添加调研', '${ctx}/surveys/add.html', true);
    }

    function editSurveys(surveys) {
        window.parent.ContentTabs.addPage('修改调研', '${ctx}/surveys/' + surveys.id() + '/edit.html', true);
    }
    function replySurveys(surveys) {
        window.parent.ContentTabs.addPage('反馈调研', '${ctx}/surveys/' + surveys.id() + '/reply.html?type=${type}', true);
    }
    function delSurveys(surveys) {
        dialog.confirm('确认删除《' + surveys.title() + '》调研吗？',
            function () {
                $.post("${ctx}/surveys/deleteById.html", {id: surveys.id()},
                    function (data) {
                        if (data.state) {
                            dialog.closeAll();
                            dialog.success(data.message);
                            viewModel.Reload();
                        } else {
                            dialog.error(data.message);
                        }
                    }, "json");
            }
        );

    }
    function releaseSurveys(surveys) {
        if (moment(surveys.endtime()) < moment() || moment(surveys.endtime()).format('YYYY-MM-DD') == moment().format('YYYY-MM-DD')) {
            dialog.msg("调研截止时间已过，请重新修改后发布。");
            return;
        }
        dialog.confirm('确认发布《' + surveys.title() + '》调研吗？',
            function () {
                $.post("${ctx}/surveys/releaseSurveys.html", {id: surveys.id()},
                    function (data) {
                        if (data.state) {
                            dialog.closeAll();
                            dialog.success(data.message);
                            viewModel.Reload();
                        } else {
                            dialog.error(data.message);
                        }
                    }, "json");
            }
        );
    }
    var viewModel = new PagerModel("${ctx}/surveys/getList.html?type=${type}");
    $(function () {
        ko.applyBindings(viewModel, $("#SurveysList")[0]);
    })
    function searchList() {
        var q = $('#q').val();
        viewModel.Reload('title=' + q);
    }
</script>
