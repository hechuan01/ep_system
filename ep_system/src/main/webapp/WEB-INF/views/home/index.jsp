<%@page import="com.zx.system.model.UserLogin"%>
<%@ page import="com.zx.system.model.SysModule" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%!
    List<Integer> visibleModules = new ArrayList<Integer>();
    String ids = "";
%>
<%
    ids = "";
    UserLogin loginInfo=(UserLogin)session.getAttribute("userLogin");
    visibleModules = new ArrayList<Integer>();
    List<SysModule> list = loginInfo.getModules();
    for (SysModule item : list) {
        visibleModules.add(item.getId());
    }
    ids = StringUtils.join(visibleModules, ",");
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <link href="${ctx}/favicon.ico" rel="shortcut icon"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${ctx}/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="${ctx}/assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <!-- fonts -->

    <!---->

    <!-- ace styles -->
    <link href="${ctx}/scripts/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/ace-skins.min.css"/>
    <script src="${ctx}/assets/js/ace-extra.min.js"></script>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="${ctx}/assets/js/html5shiv.js"></script>
    <script src="${ctx}/assets/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="${ctx}/scripts/plugins/layer/skin/layer.css" id="layui_layer_skinlayercss">
    <link href="${ctx}/scripts/plugins/alertifyjs/css/alertify.min.css" rel="stylesheet"/>
    <link href="${ctx}/scripts/plugins/alertifyjs/css/themes/default.min.css" rel="stylesheet"/>
    <link href="${ctx}/css/new_Top_Left.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/style.css" rel="stylesheet"/>


</head>
<body style="overflow:hidden;">

<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <div class="logo">作战任务协同及辅助决策系统
            </div><!-- /.brand -->
        </div><!-- /.navbar-header -->
        <input type="hidden" id="ids" value="<%=ids%>">
        <div class="navbar-header nav-pull-right" role="navigation">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse nav-pull-right" id="bs-example-navbar-collapse-1">
            <ul class="nav ace-nav nav-pull-right-position">
                <li class="light-blue dropdown txta">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><span class="hi">Hi,</span>&nbsp;${sessionScope.userLogin.fullName} <span
                            class="role">【${sessionScope.userLogin.role.rolename}】</span><span
                            class="caret"></span></a>
                    <ul class="dropdown-menu dropdown-mymenu">
                        <li><a class="clickable" onclick="ContentTabs.showUserDetail()">个人资料</a></li>
                        <li><a class="clickable" onclick="ContentTabs.changePwd()">密码管理</a></li>
                    </ul>
                </li>
            </ul>

            <div class="assets">
                <div class="assets_content dropdown" id="notificationCount">
                    <a id="dropdownMenu" href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${ctx}/images/xt_icon2.png">
                        <span class="badge" id="allCount" style="display: none"></span>
                        消息
                    </a>
                    <ul class="dropdown-menu dropdown-mymenu" aria-labelledby="dropdownMenu">
                        <li class="padding-v-5">
                            <a onclick="ContentTabs.showNotification(0)">流程:
                                <span id="appliesCount" class="red">0</span>
                                条未处理</a>
                        </li>
                        <li class="padding-v-5">
                            <a onclick="ContentTabs.showNotification(1)">公告:
                                <span id="noticesCount" class="red">0</span>
                                条未阅读</a>
                        </li>
                        <li class="padding-v-5">
                            <a onclick="ContentTabs.showNotification(2)">调研:
                                <span id="surveysCount" class="red">0</span>
                                条未反馈</a>
                        </li>
                        <li class="text-center padding-v-10">
                            <a onclick="ContentTabs.showNotification()">更多消息</a></li>
                    </ul>
                </div>
                <%--<div class="assets_content"><a href="#"><img src="${ctx}/images/xt_icon3.png">待办</a></div>--%>
                <%--<div class="assets_content"><a href="#"><img src="${ctx}/images/xt_icon4.png">设置</a></div>--%>
                <div class="assets_content"><a href="#" onclick="ContentTabs.logout()"><img
                        src="${ctx}/images/xt_icon5.png">退出</a></div>
            </div>
        </div>
        <!-- /.ace-nav -->
        <!-- /.navbar-header -->
    </div><!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span> </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <ul id="navul" class="nav nav-list">
                <li>
                    <a href='javascript:void(0)' onclick="ContentTabs.showIndex()" target="fright"><i
                            class="icon-home"></i><span
                            class="menu-text">系统首页</span></a>
                </li>
                <!-- ko foreach:items() -->
                <li data-bind="visible:ContentTabs.auth(id)">
                    <a href='javascript:void(0)' class='dropdown-toggle'><i
                            data-bind="attr:{'class':'fa fa-'+img}"></i><span
                            class="menu-text" data-bind="text:text"></span><b class="arrow icon-caret-down"></b></a>
                    <ul class="submenu">
                        <!-- ko foreach:childNodes -->
                        <li>
                            <a data-bind="text:text,click:ContentTabs.addPage.bind($data,text,'${ctx}'+extra.murl,true),visible:ContentTabs.auth(id)"
                               target="fright"></a></li>
                        <!-- /ko -->
                    </ul>
                </li>
                <!-- /ko -->

                <%--<li>--%>
                <%--<a href='javascript:void(0)' class='dropdown-toggle'><i class="icon-cogs"></i><span--%>
                <%--class="menu-text">系统管理</span><b class="arrow icon-caret-down"></b></a>--%>
                <%--<ul class="submenu">--%>

                <%--<li><a href="/module/list.html"--%>
                <%--onclick="ContentTabs.addPage('菜单管理', '/module/list.html', true)"--%>
                <%--target="fright">菜单管理</a></li>--%>
                <%--<li><a href="/module/list.html"--%>
                <%--onclick="ContentTabs.addPage('角色管理', '/role/list.html', true)"--%>
                <%--target="fright">角色管理</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>
            </ul>
            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i></div>
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>
        <div id="rightContent">
            <div id="contentBreads" class="row breadcrumb">
                <ul class="tabs pull-left">
                    <li class="current" style="padding-left: 15px; padding-right: 15px;">
                        <a href="javascript:;" name="系统首页">
                            <i class="fa fa-home" style="margin-left: 0;"></i>
                            系统首页
                        </a>
                    </li>
                </ul>
                <a class="btnRefresh" title="" data-toggle="tooltip" data-placement="left"
                   onclick="ContentTabs.refreshTab(this)" data-original-title="刷新当前页面">
                    <i class="fa fa-refresh fa-fw" title="刷新当前页面"></i>
                </a>
            </div>
            <div class="content-wrapper" id="contentArea" style="margin-top: -2px">
                <div class="overlay-wrapper" id="overlayDiv" style="display:none">
                </div>
                <div class="area-content" name="系统首页" id="tab-page-index-0">
                    <iframe id="fright" class="fright" name="fright" src="${ctx}/home/center.html"></iframe>
                </div>
            </div>
            <!--内容页frame-->
            <%--<iframe id="fright" name="fright" src="/home/center.html" scrolling="auto"--%>
            <%--style="float:left;border:0 none;"--%>
            <%--border="0"></iframe>--%>
        </div>
    </div>


</div><!-- /#ace-settings-container -->
</div><!-- /.main-container-inner -->

</div>

<!-- basic scripts -->

<!--[if !IE]> -->

<!---->

<!-- <![endif]-->

<!--[if IE]>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctx}/assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctx}/assets/js/jquery-1.10.2.min.js'>" + "<" + "script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='${ctx}/assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
</script>
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="/assets/js/excanvas.min.js"></script>
<![endif]-->

<script src="${ctx}/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/assets/js/jquery.slimscroll.min.js"></script>
<script src="${ctx}/assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="${ctx}/assets/js/jquery.sparkline.min.js"></script>
<script src="${ctx}/assets/js/flot/jquery.flot.min.js"></script>
<script src="${ctx}/assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="${ctx}/assets/js/flot/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->

<script src="${ctx}/assets/js/ace-elements.min.js"></script>
<script src="${ctx}/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/scripts/common/jquery.validate.js"></script>
<script src="${ctx}/scripts/plugins/alertifyjs/alertify.js"></script>
<script src="${ctx}/scripts/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${ctx}/scripts/plugins/layer/layer.js"></script>
<script src="${ctx}/scripts/common/knockout-3.3.0.js"></script>
<script src="${ctx}/scripts/common/knockout.mapping.js"></script>
<script src="${ctx}/scripts/common/ko.pager.js"></script>
<script src="${ctx}/scripts/common/dialog.js"></script>
<script src="${ctx}/scripts/common/moment.js"></script>
<script src="${ctx}/scripts/_init.js"></script>

<script type="text/javascript">
    //模块图标定义
    var iconList = {
        "001001": "icon-home",//系统首页
        "001003": " icon-exclamation-sign",//威胁感知
        "001004": "icon-eye-open",//案件追踪
        "001009": "icon-globe",//历史数据
        "001010": "icon-cogs" //系统管理
    }

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
        selectChange();
    }

    var ids = $('#ids').val().split(",");


    $(document).ready(function () {
        try {
            $('#sidebar-collapse').bind('click', function () {
                iFrameHeight();
            });
            loadIcon();
            iFrameHeight();
            selectFirstNode();
            selectChange();

        } catch (e) {

        }
    });

    window.onresize = iFrameHeight;

    /*计算左边菜单高度、iframe高度以及宽度*/
    function iFrameHeight() {
        //高度是 整体高度- 头部 - menu
        var height = document.body.parentNode.clientHeight - document.getElementById("navbar").clientHeight;
        document.getElementById("fright").style.height = (height - 40) + 'px';
        $('#rightContent').attr("height", height - 35);
        $('#contentArea iframe.area-content').attr("height", height - 40);
        $("#sidebar").css('height', height + "px");
        //宽度是  整体宽度-左边-1px ；-1px是为避免在火狐浏览器下缩放窗体时frame会跳动到下方
        if ($("#menu-toggler").css('display') != 'none') {//宽度变窄时菜单完全收起不在同一层，不占当前层宽度
            document.getElementById("fright").style.width = document.body.parentNode.clientWidth - 1 + "px";
            $('#rightContent').attr("width", document.body.parentNode.clientWidth - 1).css('margin-left', 0);
            $('#contentBreads').find('ul.tabs').css('padding-left', '100px');
        }
        else {
            document.getElementById("fright").style.width = document.body.parentNode.clientWidth - document.getElementById("sidebar").offsetWidth - 1 + "px";
            $('#rightContent').attr("width", document.body.parentNode.clientWidth - 1)
                .css('margin-left', document.getElementById("sidebar").offsetWidth + 'px');
            $('#contentBreads').find('ul.tabs').css('padding-left', '');
        }
        ContentTabs.autoSizeTabbar();
        //document.getElementById("fright").style.width = document.body.parentNode.clientWidth - document.getElementById("sidebar").offsetWidth + "px";

    }


    /*加载菜单图标*/
    function loadIcon() {
        var icon = $(".icon");
        icon.each(function (i) {
            var ids = $(this).attr("id");
            $(this).addClass(iconList[ids]);
        });
    }
    /*首次加载默认选中项*/
    function selectFirstNode() {
        //$("#sidebar .nav nav-list").find('li').first().addClass('active open');
        $("#navul").find('li').first().toggleClass('open');
        $("#navul").find('li').first().find('ul').css('display', 'block');
        var lis = $('.submenu').first().find('li');


    }

    /*绑定二级菜单切换时样式响应*/
    function selectChange() {
        var lis = $('.submenu').find('li');
        if (lis.length > 0) {
            lis.each(function (i) {
                var li = $(lis[i]);
                li.click(function (i) {
                    lis.removeClass('active');
                    li.addClass('active');
                });
            });
        }
    }
    //标签页
    var ContentTabs = function () {
        var divBreads = $('#contentBreads');
        var divContents = $('#contentArea');
        var marginTop = 40;
        var alertWinMore = false;
        return {
            showNotification: function (type) {
                debugger
                if (type == undefined) {
                    type = "";
                }
                this.addPage("消息中心", "${ctx}/notification/list.html?type=" + type, true);
            },
            logout: function () {
                dialog.confirm('确认退出系统么？', function () {
                    $.getJSON('${ctx}/home/logout.html', function (result) {
                        if (result.state) {
                            dialog.success(result.message);
                            location.href = '${ctx}/home/login.html';
                        } else {
                            dialog.error(result.message);
                        }

                    });
                });
            },
            showUserDetail: function () {
                dialog.iframe("${ctx}/user/detail.html?id=<%=loginInfo.getID()%>", {
                    title: "个人资料",
                    maxmin: false,
                    size: [500, 320]
                });
            },
            changePwd: function () {
                dialog.iframe("${ctx}/user/password.html?id=<%=loginInfo.getID()%>", {
                    title: "修改密码",
                    maxmin: false,
                    size: [400, 320]
                }, function (result) {
                    if (result) {
                        dialog.alert("密码修改成功，请重新登录！", function () {
                            $.getJSON('${ctx}/home/logout.html', function () {
                                location.href = '${ctx}/home/login.html';
                            });
                        })
                    }
                });
            },
            auth: function (id) {
                var isAdmin = <%=loginInfo.isAdmin()%>;
                if (isAdmin) {
                    return true;
                }
                if ($.inArray(id.toString(), ids) >= 0) {
                    return true;
                }
                return false;
            },
            showIndex: function () {
                $('#contentBreads ul li').removeClass('current');
                $('#contentBreads ul li:first').addClass('current');
                $('#contentArea>.area-content').hide();
                $('#tab-page-index-0').scrollTop("0px").show();
            },
            refreshTab: function (obj) {
                $(obj).find('i').addClass('fa-spin');
                var iframe = $("iframe.area-content:visible", document.body).attr('name');
                if (iframe != undefined) {
                    parent.frames[iframe].location.reload();
                } else {
                    var index = $("div.area-content:visible", document.body).attr('id');
                    if (index == 'tab-page-index-0') {
                        location.reload();
                    }
                }
                setTimeout(function () {
                    $(obj).find('i').removeClass('fa-spin');
                }, 1000);

            },
            //添加标签页：isIframe为true时标签页内容使用iframe
            addPage: function (name, url, isIframe) {
                $('#overlayDiv').show();
                if (name != "" && url != "" && $.trim(name) != "" && $.trim(url) != "") {
                    name = $.trim(name);
                    var tabname = $('#contentBreads ul li a');
                    var newPage = true;
                    tabname.each(function () {
                        if ($.trim($(this).attr('name')) == name) {
                            newPage = false;
                            if (!$(this).parent().hasClass('current')) {
                                $('#contentArea>.area-content').hide();
                            }
                            $(this).parent().addClass('current').siblings().removeClass('current');

                            if (isIframe != undefined && isIframe) {
                                var h = $(window).height() - marginTop;
                                $('#contentArea>.area-content[name="' + name + '"]').attr('src', $.trim(url));
                            } else {
                                $('#contentArea>.area-content[name="' + name + '"]').load($.trim(url));
                            }
                            $('#contentArea>.area-content[name="' + name + '"]').show();
                        }
                    });
                    if (newPage) {
                        if (tabname.length >= 20) {
                            dialog.alert('您已打开的窗口过多，为保证系统的体验效果，请您关闭部分窗口后再打开新的窗口。');
                            $('#overlayDiv').hide();
                            return;
                        } else {
                            var id = 'tab-page-index-' + (tabname.length + 1);

                            $('#body').css('overflow', 'hidden');
                            $('#contentBreads ul li').removeClass('current');
                            var add_li = $('<li class="current" data-toggle="tooltip" data-placement="top" title="' + name + '"><a href="javascript:" name="' + name + '">' + name + '</a><span class="btn-icon" onclick="ContentTabs.removePage(\'' + name + '\')">&times;</span></li>');
                            $('#contentArea>.area-content').hide();
                            $('#contentBreads ul.tabs').append(add_li);
                            if (isIframe != undefined && isIframe) {
                                var h = document.body.parentNode.clientHeight - document.getElementById("navbar").clientHeight - marginTop;
                                $('#contentArea').append('<iframe class="area-content fright" width="100%" height="' + h + '" name="' + name + '" id="' + id + '" src="' + url + '"></iframe>');
                            } else {
                                $('#contentArea').append('<div class="area-content" name="' + name + '" id="' + id + '"></div>');
                                $('#contentArea>.area-content[name="' + name + '"]').load($.trim(url));
                            }
                            $('body').css('overflow', '');
                        }
                    }

                }
                ContentTabs.autoSizeTabbar();
                $('#overlayDiv').hide();
            },
            autoSizeTabbar: function () {
                var eleTabbar = $('#contentBreads .tabs');
                var tabsCount = eleTabbar.find('li').length;
                var eleTabbarWidth = $('#contentBreads').width() - 100;
                eleTabbar.find('li').css('width', '');
                eleTabbar.find('li').attr('data-toggle', '');
                if (eleTabbar.height() > 40) {
                    if (!alertWinMore) {
                        dialog.warning('您打开的窗口过多，可能会影响到您的体验效果。');
                        alertWinMore = true;
                    }
                    eleTabbar.find('li').css('width', eleTabbarWidth / (tabsCount + 1));
                    eleTabbar.find('li').attr('data-toggle', 'tooltip');
                }
            },
            removePage: function (name, activeName) {
                debugger
                if (name != "" && $.trim(name) != "") {
                    name = $.trim(name);
                    var delPage = $('#contentBreads ul li a[name="' + name + '"]');
                    if (delPage != undefined) {
                        if (delPage.parent().hasClass('current')) {
                            delPage.parent().remove();
                            $('#contentArea>.area-content[name="' + name + '"]').remove();
                            var f;
                            if (activeName != undefined && activeName != null && activeName != "") {//关闭时打开指定页面
                                f = $('#contentBreads ul li a[name="' + activeName + '"]').parent();
                            } else
                                f = $('#contentBreads ul li:first-child');
                            if (f.length <= 0) {
                                $('#contentArea>.area-content[name="系统首页"]').scrollTop("0px").show();

                                $$init = $('#tab-page-index-0');
                            } else {
                                f.addClass('current');
                                $('#contentArea>.area-content[name="' + f.find('a').attr('name') + '"]').show();
                            }
                        } else {
                            delPage.parent().remove();
                            $('#contentArea>.area-content[name="' + name + '"]').remove();
                        }
                    }
                }
                $('#contentBreads .tooltip').remove();
                ContentTabs.autoSizeTabbar();
            }
        }
    }();

    //消息定时通知
    function getNotification() {
        $.post('${ctx}/notification/get.html', {}, function (data) {
            var count = parseInt($.trim($('#allCount').text()) == '' ? 0 : $.trim($('#allCount').text()));
            if (data.allCount > count) {
                dialog.error("你有 " + (data.allCount - count ) + " 条新消息");
            }
            if (data.allCount > 0)
                $('#allCount').text(data.allCount > 99 ? '99+' : data.allCount).show();
            else
                $('#allCount').text('').hide();
            $('#noticesCount').text(data.noticesCount);
            $('#surveysCount').text(data.surveysCount);
            $('#appliesCount').text(data.appliesCount);

        }, 'json');
    }
    getNotification();
    /* setInterval(function () {
        getNotification();
    }, 60000); */

</script>
<script>
    $(function () {
        $(".dropdown-toggle").click(function (e) {
            /*切换折叠指示图标*/
            $(this).find("b").toggleClass("icon-caret-down");
            $(this).find("b").toggleClass("icon-caret-up");
        });

        $('#contentBreads').on('click', 'ul li', function () {
            var name = $(this).find('a').attr('name');
            if (name != undefined) {
                var id = $('#contentArea .area-content[name="' + $.trim(name) + '"]').attr('id');
                if (!$(this).hasClass('current')) {
                    $('#contentArea .area-content').hide();
                    $('#contentArea .area-content[name="' + $.trim(name) + '"]').show();
                }
                $(this).addClass('current').siblings().removeClass('current');

            }
        });
    });


</script>
</body>
</html>