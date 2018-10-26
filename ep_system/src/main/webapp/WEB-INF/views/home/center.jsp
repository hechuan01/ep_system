<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/18
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<jsp:include page="/WEB-INF/views/shared/head2.jsp"></jsp:include>--%>
    <jsp:include page="../shared/head.jsp"></jsp:include>
</head>
<body>

<%--<ol class="breadcrumb">
    <span>我的位置：</span>
    <li><a href="#">统计概况</a></li>
</ol>--%>
<div class="MyPosition"><span class="gray">我的位置：</span>系统首页</div>
<div class="margin-top-25"></div>
<div class="col-sm-12">


        <div class="col-md-8 col-lg-9">

            <ul class="content-top-left-title clearfix" id="applymap">
                <li class="clearfix mygreen">
                    <p><a href="#" data-bind="text:data()[0].name,click:jumpapply.bind($data, data()[0].pram)">待审查工具</a>
                    </p><span class="fa fa-caret-right"></span>
                    <span class="fl"><a href="#"
                                        data-bind="text:data()[0].value,click:jumpapply.bind($data, data()[0].pram)">163</a></span>
                </li>
                <li class="clearfix myred">
                    <p class="myred"><a href="#"
                                        data-bind="text:data()[1].name,click:jumpapply.bind($data, data()[1].pram)"></a>
                    </p><span class="fa fa-caret-right"></span>
                    <span class="fl"><a href="#"
                                        data-bind="text:data()[1].value,click:jumpapply.bind($data, data()[1].pram)"></a></span>
                </li>
                <li class="clearfix myblue">
                    <p class="myblue"><a href="#"
                                         data-bind="text:data()[2].name,click:jumpapply.bind($data, data()[2].pram)"></a>
                    </p><span class="fa fa-caret-right"></span>
                    <span class="fl"><a href="#"
                                        data-bind="text:data()[2].value,click:jumpapply.bind($data, data()[2].pram)"></a></span>
                </li>
                <li class="clearfix myoringe">
                    <p class="myoringe"><a href="#"
                                           data-bind="text:data()[3].name,click:jumpapply.bind($data, data()[3].pram)"></a>
                    </p><span class="fa fa-caret-right"></span>
                    <span class="fl"><a href="#"
                                        data-bind="text:data()[3].value,click:jumpapply.bind($data, data()[3].pram)"></a></span>
                </li>
            </ul>

        </div>
        <div class="col-md-4 col-lg-3">
            <div class="mydate">
                <div class="mydate1">
                    <div class="mydate1_one">
                        <img src="${ctx}/images/number/0.png">
                        <img src="${ctx}/images/number/0.png">
                    </div>
                    <div class="mydate1_one mydate1_two">
                        <img src="${ctx}/images/number/0.png">
                        <img src="${ctx}/images/number/0.png">
                    </div>

                </div>
                <div class="mydate1_flo"><span class="red"></span><span></span></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 col-lg-6">
                <div class="xt_content xt_content_marginl">
                    <div class="xt_title"><span class="icon-bar-chart"></span>IP指纹检索频次</div>
                    <div class="eCharts" id="ipretrievalfrequency">
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-6">
                <div class="xt_content">
                    <div class="xt_title"><span class="icon-bar-chart"></span>军火库类型及数量展示</div>
                    <div class="eCharts">
                        <div class="eCharts" id="weapontypechart">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" id="Ipdnscount">
            <div class="col-lg-12">
                <div class="apply">
                    <div class="mytitle">
                        <div class="mytitle1"><p>
                            <span class="icon-angle-right"></span></p>IP指纹和ISP数据增长&nbsp;
                            <span class="flo_right"><select name="" class="txt1" id="selectime" onchange="changeTime()">
                                                    <option value="day" selected="selected">日</option>
                                                    <option value="week">周</option>
                                                    <option value="month">月</option>
                                                </select></span></div>
                    </div>

                    <div class="data">
                        <div><a href="#">IP数据</a></div>
                        <a href="#" data-bind="text:IP数据"></a></div>
                    <div class="data data1">
                        <div><a href="#">域名数据</a></div>
                        <a href="#" data-bind="text:域名数据"></a></div>
                    <div class="data data2">
                        <div><a href="#">主机数据</a></div>
                        <a href="#" data-bind="text:主机数据"></a></div>
                    <div class="data data3">
                        <div><a href="#">ISP数据</a></div>
                        <a href="#" data-bind="text:ISP数据"></a></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 col-lg-6">
                <div class="mytitle mytitle2 xt_content_marginl">
                    <div class="mytitle1"><p><span class="icon-angle-right"></span></p>最新公告</div>
                </div>
                <ul class="notice xt_content_marginl" id="NoticesList" data-bind="foreach:Items()">
                    <li>
                        <a data-bind="click:replyNotices.bind(),attr:{style: readState() =='未阅读'?'font-weight:bold':'font-weight:normal'}"><span
                                class="flo_right" data-bind="text:stringToDate(starttime())"></span><span
                                class="flo_right"></span><span
                                data-bind="attr:{class:$index()>2?'notice_pre1':'notice_pre'}"><text
                                data-bind="text:$index()>2?$index()+1:''"></text><img data-bind="visible: $index() <3"
                                                                                      src="${ctx}/images/new.png"></span><span
                                data-bind="text:title"></span></a></li>
                </ul>

            </div>
            <div class="col-md-6 col-lg-6">
                <div class="mytitle mytitle2">
                    <div class="mytitle1"><p><span class="icon-angle-right"></span></p>最新调研</div>
                </div>
                <ul class="notice" id="SurveysList" data-bind="foreach:Items()">
                    <li>
                        <a data-bind="click:replySurveys.bind(),attr:{style: readState() =='未反馈'?'font-weight:bold':'font-weight:normal'}"><span
                                class="flo_right" data-bind="text:stringToDate(starttime())"></span><span
                                class="flo_right"></span><span
                                data-bind="attr:{class:$index()>2?'notice_pre1':'notice_pre'}"><text
                                data-bind="text:$index()>2?$index()+1:''"></text><img data-bind="visible: $index() <3"
                                                                                      src="${ctx}/images/new.png"></span><span
                                data-bind="text:title"></span></a></li>
                </ul>

            </div>
        </div>
        <div class="clearfix"></div>
        <div class="apply" id="flowlist">
            <div class="mytitle">
                <div class="mytitle1"><p><span class="icon-angle-right"></span></p>我的申请</div>
            </div>

            <table class="table-xt table-layout">
                <thead>
                <tr>
                    <th style="width: 40px">序号</th>
                    <%-- <th class="col-lg-1">ID</th>--%>
                    <th>名称</th>
                    <%--targetid->targetname--%>
                    <th>类别</th>
                    <%--applytype->applytypename--%>

                    <%-- <th class="col-xs-1">流程</th>--%>
                    <%-- processid--%>
                    <th>内容</th>
                    <%--applycontent--%>
                    <th>原因</th>
                    <%--applyreason--%>
                    <th>状态</th>
                    <th>申请人</th>
                    <%--createid->createname--%>
                    <th>申请时间</th>
                    <%--createtime--%>
                    <th>操作</th>
                    <%--state--%>

                </tr>
                </thead>
                <tbody data-bind="foreach:Items()">
                <tr style="width: 40px">
                    <td data-bind="text:$index()+1+$root.PageSize()*($root.CurrentPage()-1)"></td>
                    <%--<td class="col-lg-1" data-bind="text:id">ID</td>--%>
                    <td data-bind="text:targetname">名称</td>
                    <%--targetid->targetname--%>
                    <td data-bind="text:applytypename">类别</td>
                    <%--applytype->applytypename--%>

                    <%--<td class="col-xs-1"><a href="#"
                                            data-bind="text:processinfo.procname,event:{click:ShowProcessStep.bind($data)}"
                                            title="流程详情" data-toggle="tooltip" data-placement="top"></a>
                    </td>--%>
                    <%-- processid->processname--%>
                    <td data-bind="text:applycontent">内容描述</td>
                    <%--applycontent--%>
                    <td data-bind="text:applyreason">原因描述</td>
                    <%--applyreason--%>
                    <td><a href="#"
                           data-bind="text:statename,event:{click:ShowApplyDetail.bind($data)}"></a>
                    </td>
                    <td data-bind="text:createuser.fullname">申请人</td>
                    <%--createid->createname--%>
                    <td data-bind="text:timeConvert(createtime())">申请时间</td>
                    <%--createtime--%>
                    <td class=" text-center">
                        <a title="查看流程" data-toggle="tooltip" data-placement="top"
                           data-bind="event:{click:ShowProcessStep.bind($data)}">
                            <i class="fa fa-search"></i>
                        </a>
                        <a title="处理步骤" data-toggle="tooltip" data-placement="top"
                           data-bind="event:{click:ShowApplyDetail.bind($data)}">
                            <i class="fa fa-edit"></i>
                        </a>
                    </td>
                    <%--state->statename--%>
                </tr>
                <%--<!-- /ko -->--%>
                </tbody>
            </table>
        </div>

</div>
</body>
</html>
<jsp:include page="../shared/footer.jsp"></jsp:include>
<script type="text/javascript">

    var viewModel = new PagerModel("${ctx}/flow/getList.html?myApply=true", $("form").serialize());
    var noticesModel = new PagerModel("${ctx}/notices/getList.html?type=user");
    var surveysModel = new PagerModel("${ctx}/surveys/getList.html?type=user");

    var Ipdnscount = {
        IP数据: ko.observable(""),
        域名数据: ko.observable(""),
        主机数据: ko.observable(""),
        ISP数据: ko.observable(""),
    }
    var apply = new Array();
    apply.push({name: "待审查工具", value: 0, pram: "1-0"});
    apply.push({name: "已审查工具", value: 0, pram: "1-4"});
    apply.push({name: "待审查需求", value: 0, pram: "2-0"});
    apply.push({name: "已审查需求", value: 0, pram: "2-4"});
    var applymodel = {data: ko.observableArray(apply)}
    var Ipdnscountdata;
    $(function () {
        ko.applyBindings(applymodel, $("#applymap")[0]);
        ko.applyBindings(viewModel, $("#flowlist")[0]);
        ko.applyBindings(noticesModel, $("#NoticesList")[0]);
        ko.applyBindings(surveysModel, $("#SurveysList")[0]);
        ko.applyBindings(Ipdnscount, $("#Ipdnscount")[0]);
        changTime();//立即执行一遍
        setInterval("changTime()", 1000);//1000为1秒钟
        //获取IP指纹检索频次
        /* ipretrievalfrequency(); */
        //获取军火类型统计数据饼图显示
        /* loadweapontypechart(); */
        //加载统计
        /* applymap(); */
        //IP指纹和ISP数据增长
        /* loadipdnscount() */
    })

    function changTime() {
        var data = new Date();
        var hours = data.getHours();
        var Minutes = data.getMinutes();
        $(".mydate1 img:eq(0)").attr("src", "${ctx}/images/number/" + Math.floor(hours / 10) + ".png");
        $(".mydate1 img:eq(1)").attr("src", "${ctx}/images/number/" + (hours % 10) + ".png");
        $(".mydate1 img:eq(2)").attr("src", "${ctx}/images/number/" + Math.floor(Minutes / 10) + ".png");
        $(".mydate1 img:eq(3)").attr("src", "${ctx}/images/number/" + (Minutes % 10) + ".png");
        $(".mydate1_flo span:eq(0)").html(moment().format('YYYY年MM月DD日'));
        $(".mydate1_flo span:eq(1)").html(NumberToZH(moment(data).format("E")));
    }

    function NumberToZH(num) {
        var ZHS = ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"];
        return ZHS[num - 1];
    }

    function ShowProcessStep(obj) {
        /*pram id is process id*/
        var currentUrl = "${ctx}/flow/flowstepdetaile.html?id=" + obj.processid() + "&processname=" + encodeURI(obj.processinfo.procname());
        /*  console.log(currentUrl);*/
        var title = "查看流程步骤";
        dialog.iframe(currentUrl, {title: title, size: [800, 450]});

    }

    function ShowApplyDetail(currentapply) {
        /* $.each(currentapply,function (i,v) {
         console.log(i);
         console.log(this());
         })*/
        var currentUrl = "${ctx}/flow/flowappliedetail.html?id=" + currentapply.id();
        var title = "查看申请详情";
        /*dialog.iframe(currentUrl, {title: title, size: [1000, 800]});*/
        window.parent.ContentTabs.addPage('查看申请详情', currentUrl, true);
    }
    /*IP指纹检索频次*/
    function ipretrievalfrequency() {
        var myChart = echarts.init(document.getElementById('ipretrievalfrequency'));
        myChart.showLoading({text: "加载中..."});
        //shuyizhi Test
        var now = moment().format("YYYY-MM-DD HH:mm:ss");
        var preweek = moment().subtract(1, "weeks").format("YYYY-MM-DD HH:mm:ss");
        $.ajax({
            type: "get",
            url: "${ctx}/searchlog/selectCount.html?createtimestart=" + preweek + "&createtimeend=" + now,
            dataType: "json",
            success: function (result) {
                if (result && result.length > 0) {
                    var option = {
                        tooltip: {
                            trigger: 'axis'
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                mark: {show: false},
                                dataView: {show: false, readOnly: false},
                                magicType: {show: false, type: ['line', 'bar']},
                                restore: {show: false},
                                saveAsImage: {show: false}
                            }
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: (function () {
                                    var times = [];
                                    $.each(result, function (index) {
                                        times.push(result[index].dateTime);
                                    });
                                    return times
                                })()
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                axisLabel: {
                                    formatter: '{value}'
                                }
                            }
                        ],
                        series: [
                            {
                                name: '检索次数',
                                type: 'line',
                                //data:[0, 4, 20, 13, 40, 100, 7],
                                data: (function () {
                                    var data = [];
                                    $.each(result, function (index) {
                                        data.push(result[index].cnt);
                                    });
                                    return data;
                                })(),
                                itemStyle: {
                                    normal: {
                                        color: '#2ECBC9'
                                    }
                                },
                                markPoint: {
                                    data: [
                                        {type: 'max', name: '最大值'},
                                        {type: 'min', name: '最小值'}
                                    ]
                                },
                                markLine: {
                                    data: [
                                        {type: 'average', name: '平均值'}
                                    ]
                                }
                            }
                        ]
                    };
                    myChart.hideLoading();
                    myChart.setOption(option);
                }
            },
            error: function () {
                myChart.hideLoading();
            }
        })

    }

    /*加载军火分类图表信息*/
    function loadweapontypechart() {
        var myChart = echarts.init(document.getElementById('weapontypechart'));
        myChart.showLoading({text: "加载中..."});
        option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: []
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {show: false},
                    dataView: {show: false, readOnly: false},
                    magicType: {
                        show: false,
                        type: ['pie', 'funnel']
                    },
                    restore: {show: false},
                    saveAsImage: {show: false}
                }
            },
            calculable: false,
            series: [
                {
                    //name:'',
                    type: 'pie',
                    selectedMode: 'single',
                    radius: [0, 70],
                    // for funnel
                    x: '20%',
                    width: '40%',
                    funnelAlign: 'right',
                    max: 1548,

                    itemStyle: {
                        normal: {
                            label: {
                                position: 'inner'
                            },
                            labelLine: {
                                show: false
                            }
                        }
                    },
                    data: []
                },
                {
                    //name:'',
                    type: 'pie',
                    radius: [100, 130],
                    x: '60%',
                    width: '35%',
                    funnelAlign: 'left',
                    max: 1048,
                    data: []
                }
            ]
        };

        $.ajax({
            type: "GET",
            url: "${ctx}/weapon/getWeaponTypeEchartsData.html",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            error: function (e) {
                myChart.hideLoading();
            },
            success: function (resdata) {
                if (resdata != null && resdata.length != 0) {
                    //option.legend.data = resdata[0];
                    option.series[0].data = resdata[1];
                    option.series[1].data = resdata[2];
                    myChart.hideLoading();
                    myChart.setOption(option);
                }
                else {
                    myChart.hideLoading();
                }
            }
        });
        myChart.hideLoading();
    }

    /*加载IP指纹和ISP数据增长*/
    function loadipdnscount() {
        $.ajax({
            type: "post",
            url: "${ctx}/home/getIpdnscount.html",
            dataType: "json",
            error: function (e) {
                console.log(e)
            },
            success: function (data) {
                Ipdnscountdata = data;
                changeTime();
            }
        });

    }
    function changeTime() {
        var time = $("#selectime").val();
        if (time == "day") {
            Ipdnscount.IP数据(Ipdnscountdata.ip.day[0].num);
            Ipdnscount.域名数据(Ipdnscountdata.dns.day[0].num);
            Ipdnscount.主机数据(Ipdnscountdata.host.day[0].num);
            Ipdnscount.ISP数据(Ipdnscountdata.isp.day[0].num);
        } else if (time == "week") {
            Ipdnscount.IP数据(Ipdnscountdata.ip.week[0].num);
            Ipdnscount.域名数据(Ipdnscountdata.dns.week[0].num);
            Ipdnscount.主机数据(Ipdnscountdata.host.week[0].num);
            Ipdnscount.ISP数据(Ipdnscountdata.isp.week[0].num);
        } else if (time == "month") {
            Ipdnscount.IP数据(Ipdnscountdata.ip.month[0].num);
            Ipdnscount.域名数据(Ipdnscountdata.dns.month[0].num);
            Ipdnscount.主机数据(Ipdnscountdata.host.month[0].num);
            Ipdnscount.ISP数据(Ipdnscountdata.isp.month[0].num);
        }

    }

    ///查看公告
    function replyNotices(notice) {
        window.parent.ContentTabs.addPage('查看公告', '${ctx}/notices/' + notice.id() + '/reply.html?type=user', true);

    }
    ///
    function replySurveys(surveys) {
        window.parent.ContentTabs.addPage('反馈调研','${ctx}/surveys/' + surveys.id() + '/reply.html?type=user', true);

    }


    //查看流程状态统计
    function applymap() {
        $.ajax({
            type: "GET",
            url: "${ctx}/flow/getapplymap.html",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            error: function (e) {

            },
            success: function (resdata) {
                $.each(resdata, function (i, v) {
                    switch (this.assigntype) {
                        case "1-0":
                            apply[0].value = this.num;
                            break;
                      /*  case "1-1":
                        case "1-2":*/
                        case "1-4":
                            apply[1].value += this.num;
                            break;
                        case "2-0":
                            apply[2].value = this.num;
                            break;
                      /*  case "2-1":
                        case "2-2":*/
                        case "2-4":
                            apply[3].value += this.num;
                            break;
                        default:
                            break;
                    }
                })
                applymodel.data(apply)

            }
        });

    }

    function jumpapply(parms) {
        window.parent.ContentTabs.addPage('流程记录', "${ctx}/flow/flowapplieslist.html?parms=" + parms, true);
    }
</script>
