<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2017/3/14
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>
<div class="MyPosition"><span class="gray">我的位置：</span> 公告 / 公告管理 / 添加公告</div>
<div class="margin-top-25">
    <form role="form" id="form"  name="form"  enctype="multipart/form-data" class="col-sm-12 col-md-12 col-lg-12"
          method="post">
        <input type="hidden" name="id">
        <div class="row margin-top-25">
            <div class="col-sm-2 col-md-2 col-lg-2 form-group text-right">
                <label>公告对象：<span class="red">*</span></label>
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
                <label>公告标题：<span class="red">*</span></label>
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

                <label>公告附件：<span class="red">*</span></label>
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

                <button type="button" class=" btn btn-sm btn-primary" onclick="submitNotices('保存')"
                        class="btn btn-default">保存
                </button>
                <button type="button" class=" btn btn-sm btn-primary" onclick="submitNotices('发布')"
                        class="btn btn-default">发布
                </button>
                <button type="button" class=" btn btn-sm btn-primary" onclick="window.parent.ContentTabs.removePage('添加公告','公告管理')"
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


    var sysNotices = {
        title: ko.observable(""),
        content: ko.observable(""),
        starttime: ko.observable(""),
        endtime: ko.observable(""),
        file: ko.observable(""),
        allRole: ko.observableArray([]),
    }
    ko.applyBindings(sysNotices, $("#form")[0]);

    /*
     * 提交表单方法
     * */
    function submitNotices(type) {
        if (check().form()) {

            var form = new FormData(document.getElementById("form"));
            if (type == '发布') {
                form.append("starttime", new Date());
            }
            form.append("endtime", sysNotices.endtime());
            form.append("selectedList", selectedList);
            $.ajax({
                url: "${ctx}/notices/submit.html",
                type: "post",
                data: form,
                processData: false,
                contentType: false,
                dataType: "json",
                success: function (data) {
                    if (data.state) {
                        dialog.success(data.message);
                        window.parent.ContentTabs.removePage('添加公告','公告管理')

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
         * 时间控件设置
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

        /*
         * 初始化页面信息
         *
         * */
        $.ajax({///获取所有用户角色
            type: "POST",
            url: "${ctx}/role/getAllRole.html",
            //返回数据的格式
            dataType: "json",//"xml", "html", "script", "json", "jsonp", "text".
            success: function (data) {
                sysNotices.allRole(data);
            },
            error: function (e) {
                console.log(e);
                //请求出错处理
            }
        });
    })
    ;

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
                    minlength: 30,
                },

            },
            messages: {
                receives: {
                    required: "请选择公告对象",
                },
                title: {
                    required: "公告标题不能为空",
                },
                time: {
                    required: "公告时间不能为空",
                },
                content: {
                    required: "公告内容不能为空",

                    minlength: "公告内容至少30个字！",
                },

            }
        });
    }


    $('input[id=file]').change(function () {
        $('#fileCover').val($(this).val());
    });
</script>