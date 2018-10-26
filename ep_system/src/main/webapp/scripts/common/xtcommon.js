/**
 * Created by shuyizhi on 2017/3/16.
 */
/*时间格式化*/
/*时间转换*/
function timeConvert(datetime) {
    //"2016-02-19T13:12:14.985+08:00"
    if (datetime == null || datetime == undefined)
        return '';
    var date = new Date(datetime);
    //var date = eval(datetime.replace(/\/Date\(([-|+]{0,1}\d+\+\d+)\)\//gi, "new Date($1)"));
    if (date != null && date != undefined) {
        var resdate = date.format("yyyy-MM-dd hh:mm:ss");
    }
    if (resdate.toUpperCase() == "NAN-AN-AN AN:AN:AN") {
        var isoExp = /(\d{4}-\d{1,2}-\d{1,2})(T|\s*)(\d{1,2}:\d{1,2}:[\d]{1,2})/;
        var parts = isoExp.exec(datetime);
        resdate = parts[1] + ' ' + parts[3];
    }
    if (resdate.indexOf("1970-01-01") > -1) {
        resdate = '';
    }
    return resdate;
}
/*时间格式化*/
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month 
        "d+": this.getDate(), //day 
        "h+": this.getHours(), //hour 
        "m+": this.getMinutes(), //minute 
        "s+": this.getSeconds(), //second 
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter 
        "S": this.getMilliseconds() //millisecond 
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}