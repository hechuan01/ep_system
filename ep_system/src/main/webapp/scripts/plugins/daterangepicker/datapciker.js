function option(id, datetime, _min, _max, timepicker, opens) {//文本框id   控件value;当前时间前_min年,后_max年,是否显示时分秒,打开位置
    var Date_Middler = "-->";
    _min = (_min == undefined ? "" : _min);
    _max = (_max == undefined ? "" : _max);
    var options = {};
    _opens = opens == undefined ? "center" : opens;
    options.opens = _opens;
    //options.autoApply = true;
    if (timepicker) {
        //options.showWeekNumbers = true;
        options.timePicker = true;
        options.timePicker24Hour = true;
        options.timePickerSeconds = true;

    }

    if (timepicker) {
        options.locale = {
            format: 'YYYY-MM-DD HH:mm:ss ',//HH:mm:ss
            separator: Date_Middler,
            applyLabel: '应用',
            cancelLabel: '取消',
            fromLabel: 'From',
            toLabel: 'To',
            customRangeLabel: 'Custom',
            //daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
            //monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
            firstDay: 1
        };
    }
    else {
        options.locale = {
            format: 'YYYY-MM-DD ',//HH:mm:ss
            separator: Date_Middler,
            applyLabel: '应用',
            cancelLabel: '取消',
            fromLabel: 'From',
            toLabel: 'To',
            customRangeLabel: 'Custom',
            //daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
            //monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
            firstDay: 1
        };
    }


    var d = new Date();
    if (datetime == "") {//空值默认时间为当前天和前一天
        //options.startDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + (d.getDate());//month+1 js bug
        //options.endDate = d;


    }
    else {
        if ($("#" + id).val() !== "") {
            options.startDate = $("#" + id).val().split(Date_Middler)[0];
            options.endDate = $("#" + id).val().split(Date_Middler)[1];
            $("#StartTime").val(options.startDate);
            $("#EndTime").val(options.endDate);
        }
    }
    //options.minDate = (d.getFullYear() - (_min == "" ? 1 : _min)) + "-" + (d.getMonth() + 1) + "-" + (d.getDate() + 1);//month+1 js bug
    //options.maxDate = (d.getFullYear() + (_max == "" ? 0 : _max)) + "-" + (d.getMonth() + 1) + "-" + (d.getDate());//month+1 js bug;
    if (_min != "") {
        options.minDate = _min;
    }
    if (_max != "") {
        options.maxDate = _max;
    }
    options.showDropdowns = true;

    $("#" + id).daterangepicker(options, function (start, end, label) {
        $("#StartTime").val(start.format("YYYY-MM-DD HH:mm:ss"));
        $("#EndTime").val(end.format("YYYY-MM-DD HH:mm:ss"));

    });
    //$('#datetime').daterangepicker(options, function (start, end, label) { alert("开始时间：" + start.format("YYYY-MM-DD") + ";结束时间：" + end.format("YYYY-MM-DD")) });
    //alert("开始时间：" + $("#datetime").val().split(Date_Middler)[0] + ";结束时间：" + $("#datetime").val().split(Date_Middler)[1])获取时间
}



//var DateTimeNow = function () {
//    var d = new Date();
//    var now = (d.getFullYear()) + "-" + (d.getMonth() + 1) + "-" + (d.getDate());//当前日期
//    var nowdayadd = function () { return d.setTime(d.getTime() + 24 * 60 * 60 * 1000); }//当前时间后一天
//    var nowdaydel = function () { return d.setTime(d.getTime() + 24 * 60 * 60 * 1000); }//当前时间后一天

//}()
//$(document).ready(function () {
//    alert(DateTimeNow.d);
//})