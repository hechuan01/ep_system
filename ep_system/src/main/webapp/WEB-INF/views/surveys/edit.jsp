<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2017/3/14
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="MyPosition"><span class="gray">我的位置：</span> 调研 / 调研管理 / 修改调研</div>
<div class="margin-top-25">

    <form role="form" id="form"  name="form"  enctype="multipart/form-data" class="col-sm-12 col-md-12 col-lg-12"
          method="post">
        <input type="hidden"  data-bind="value:id" name="id">
        <div class="row margin-top-25">
            <div class="col-sm-2 col-md-2 col-lg-2 form-group text-right">
                <label>调研对象：<span class="red">*</span></label>
            </div>
            <div class="col-sm-6 col-md-6 col-lg-6 form-group">
                <input type="checkbox" id="cbSelectAll" onclick="selectAll(this)" value="all">全部
                <!-- ko foreach: allRole -->
                <input type="checkbox" name="receives"
                       data-bind="value:id,checked:CheckSelected(id)" onclick="Selected(this)">
                <text data-bind="text:rolename"></text>
                <!-- /ko -->
            </div>
        </div>
        <div class="row margin-top-25">
            <div class="col-sm-2 col-md-2 col-lg-2 form-group text-right">
                <label>调研标题：<span class="red">*</span></label>
            </div>
            <div class="col-sm-6 col-md-6 col-lg-6 form-group">
                <input type="text" name="title" data-bind="value:title" class="form-control">
            </div>
        </div>
        <div class="row margin-top-25">
            <div class="col-sm-2 col-md-2 col-lg-2 form-group text-right">
                <label>结束时间：<span class="red">*</span></label>
            </div>
            <div class="col-sm-6 col-md-6 col-lg-6 form-group">
                <input type="text" id="time" name="time" readOnly="true" class="form-control " size="80">
            </div>
        </div>
        <div class="row margin-top-25">
            <div class="col-sm-2 col-md-2 col-lg-2 form-group text-right">

                <label>调研附件：<span class="red">*</span></label>
            </div>
            <div class="col-sm-6 col-md-6 col-lg-6 form-group text-right">
                <input type="file" id="file" name="file" style="display:none">
                <div class="input-group">
                    <input id="fileCover" name="fileCover" readonly="readonly"
                           onclick="$('input[id=file]').click();"
                           type="text" class="form-control">
                    <a class="input-group-addon" onclick="$('input[id=file]').click();">浏览...</a>
                </div>
            </div>
        </div>
        <div class="row margin-top-25">
            <div class="col-sm-2 col-md-2 col-lg-2 form-group text-right">
                <label>公告内容：<span class="red">*</span></label>

            </div>

            <div class="col-sm-6 col-md-6 col-lg-6 form-group">
            <textarea id="content" name="content" rows="20" data-bind="value:content">
            </textarea>
            </div>

        </div>
        <div class="row margin-top-25">
            <div class="text-center col-sm-2 col-md-2 col-lg-2">
            </div>
            <div class="text-center col-sm-6 col-md-6 col-lg-6">

                <button type="button" class=" btn btn-sm btn-primary" onclick="submitSurveys('保存')"
                        class="btn btn-default">保存
                </button>
                <button type="button" class=" btn btn-sm btn-primary" onclick="submitSurveys('发布')"
                        class="btn btn-default">发布
                </button>
                <button type="button" class=" btn btn-sm btn-primary" onclick="window.parent.ContentTabs.removePage('修改调研','调研管理')"
                        class="btn btn-default">关闭
                </button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
<jsp:include page="../shared/footer.jsp"></jsp:include>
<script type="text/javascript">
    var selectedList = new Array();


    //判断是否已被选中
    function CheckSelected(id) {
        if ($.inArray(id, selectedList) < 0) {
            return false;
        } else
            return true;
    }

    //勾选
    function Selected(obj) {
        var $this = $(obj);
        var id = $this.val();
        if ($this.prop('checked')) {
            selectedList.push(id);
            checkAllSelected();
        } else {
            selectedList.splice($.inArray(id, selectedList), 1);
            $('#cbSelectAll').prop('checked', false);
        }
    }

    //监测是否全选
    function checkAllSelected() {
        var allItem = $('#form').find('input[name="receives"]');
        var notCheckedItems = $('#form').find('input[name="receives"]').not("input:checked");
        if (allItem.length == 0 || notCheckedItems.length > 0) {
            $('#cbSelectAll').prop('checked', false);
        } else {
            $('#cbSelectAll').prop('checked', true);
        }
    }

    //全选
    function selectAll(obj) {
        var all = $(obj);
        var list = $('#form').find('input[name="receives"]');
        $.each(list, function () {
            var $this = $(this);
            //全选
            if (all.prop('checked')) {
                if (!$this.prop('checked')) {
                    $this.prop('checked', true);
                    selectedList.push($this.val());
                }
            } else {
                if ($this.prop('checked')) {
                    $this.prop('checked', false);
                    selectedList.splice($.inArray($this.val(), selectedList), 1);
                }
            }

        });
    }


    var sysSurveys = {
        id: ko.observable(""),
        title: ko.observable(""),
        content: ko.observable(""),
        starttime: ko.observable(""),
        endtime: ko.observable(""),
        file: ko.observable(""),
        allRole: ko.observableArray([]),
    }
    ko.applyBindings(sysSurveys, $("#form")[0]);

    /*
     * 提交表单方法
     * */
    function submitSurveys(type) {
        if (check().form()) {
            debugger
            ///js表单对象，可向后台传递文件
            var form = new FormData(document.getElementById("form"));
            if (type == '发布') {
                form.append("starttime", new Date());
            }
            form.append("endtime", sysSurveys.endtime());
            form.append("selectedList", selectedList);
            $.ajax({
                url: "${ctx}/surveys/update.html",
                type: "post",
                data: form,
                processData: false,
                contentType: false,
                dataType:"json",
                success: function (data) {
                    if (data.state) {
                        dialog.success(data.message);
                        window.parent.ContentTabs.removePage('修改调研','调研管理')
                    } else {
                        dialog.error(data.message);
                    }
                },
                error: function (e) {
                    alert("错误！！");
                    window.clearInterval(timer);
                }
            });
        }
    }


    $(function () {
        //初始化富文本编辑器
        var editor = new wangEditor('content');
        editor.create();
        /*
         * 初始化页面信息
         *
         * */
        $.ajax({///获取调研信息
            type: "POST",
            url: "${ctx}/surveys/${surveysid}/getById.html",
            //返回数据的格式
            dataType: "json",
            success: function (data) {
                ///初始化富文本编辑器内容
                editor.$txt.html(data.content);
                sysSurveys.id(data.id);
                sysSurveys.title(data.title);
                sysSurveys.endtime(new Date(data.endtime));
                ///初始化文件选择内容
                if (undefined != data.sysAttachment && undefined != data.sysAttachment.attname) {
                    $("#fileCover").attr("value", data.sysAttachment.attname);
                }
                /*
                 * 时间控件设置///初始化时间控件
                 * */
                $("#time").datetimepicker({
                    todayHighlight: true,
                    minView: "month",//设置只显示到月份
                    format: "yyyy-mm-dd",//日期格式
                    format: "yyyy-mm-dd",
                    autoclose: true,
                    todayBtn: true,
                    startDate: moment().format('YYYY-MM-DD'),
                    cssStyle: 'width:500px',
                })
                    .on('changeDate', function (ev) {
                        sysNotices.endtime(ev.date);
                    });

                $("#time").val(stringToDate(data.endtime));


            },
            error: function (e) {
                console.log(e);
                //请求出错处理
            }
        });
        /*
         * 获取调研关联用户角色
         *
         * */
        $.ajax({///
            type: "POST",
            url: "${ctx}/surveys/${surveysid}/getSurveysRole.html",
            //返回数据的格式
            dataType: "json",//"xml", "html", "script", "json", "jsonp", "text".
            success: function (data) {
                selectedList = data;
                $.ajax({///先获取已选角色后获取所有用户角色 防止默认选中有问题
                    type: "POST",
                    url: "${ctx}/role/getAllRole.html",
                    //返回数据的格式
                    dataType: "json",//"xml", "html", "script", "json", "jsonp", "text".
                    success: function (data) {
                        sysSurveys.allRole(data);
                        checkAllSelected();
                    },
                    error: function (e) {
                        console.log(e);
                        //请求出错处理
                    }
                });

            },
            error: function (e) {
                console.log(e);
                //请求出错处理
            }
        });


    });

    /*
     * 验证表单
     * */
    function check() {
        return $("#form").validate({
            rules: {
                receives: {
                    required: true,
                },
                title: {
                    required: true,
                },
                time: {
                    required: true,

                },
                content: {
                    required: true,
                },

            },
            messages: {
                receives: {
                    required: "请选择调研对象",
                },
                title: {
                    required: "调研标题不能为空",


                },
                time: {
                    required: "调研时间不能为空",


                },
                content: {
                    required: "调研内容不能为空",


                },
            }
        });
    }
    $('input[id=file]').change(function () {
        $('#fileCover').val($(this).val());
    });
</script>