$(function () {
    $(document).tooltip({
        selector: '[data-toggle="tooltip"]'
    });

    $('.checkbox, .radio').find(':checkbox,:radio').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue',
        increaseArea: '20%'
    });
});


/*关闭弹出框口*/
function layerClose() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

//显示可以输入的剩余字数
$('[data-importability]').on('input propertychange', function () {
    var $this = $(this);
    var maxlength = $this.data('importability');
    var length = $this.val().length;
    var importabilityLength = maxlength - length;
    var errorDiv = $this.parent().find('.importability-error');
    var tip = '还可以输入 ' + importabilityLength + ' 个字符';
    if (importabilityLength < 0)
        tip = '<red>已超出上限 ' + Math.abs(importabilityLength) + ' 个字符</red>';
    if (errorDiv.length > 0) {
        errorDiv.html(tip);
    } else {
        $this.after('<label class="importability-error">' + tip + '</label>');
    }
});
/**
 格式化时间显示方式、用法:format="yyyy-MM-dd hh:mm:ss";
 */
formatDate = function (v, format) {
    if (!v) return "";
    var d = v;
    if (typeof v === "string") {
        if (v.indexOf("/Date(") > -1)
            d = new Date(parseInt(v.replace("/Date(", "").replace(")/", ""), 10));
        else
            d = new Date(Date.parse(v.replace(/-/g, "/").replace("T", " ").split(".")[0])); //.split(".")[0] 用来处理出现毫秒的情况，截取掉.xxx，否则会出错
    }
    var o = {
        "M+": d.getMonth() + 1, //month
        "d+": d.getDate(), //day
        "h+": d.getHours(), //hour
        "m+": d.getMinutes(), //minute
        "s+": d.getSeconds(), //second
        "q+": Math.floor((d.getMonth() + 3) / 3), //quarter
        "S": d.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (d.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

/**
 当前时间
 */
function CurrentTime() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
}

/*
 自动获取页面控件值
 */
function GetWebControls(element) {
    var reVal = {};
    $(element).find("input, select, textarea").each(function (r) {
        var id = $(this).attr("id");
        var value = $(this).val();
        reVal[id] = value;
    });
    return reVal;
}

/*
 自动给控件赋值
 */
function SetWebControls(data) {
    try {
        for (var key in data) {
            var id = $("#" + key);
            if (id.attr("id")) {
                var value = $.trim(data[key]).replace("&nbsp;", "").replace(/</g, "<").replace(/>/, ">");
                var type = id.attr("type");
                switch (type) {
                    case "checkbox":
                        if (value == 1) {
                            id.attr("checked", "checked");
                        } else {
                            id.removeAttr("checked");
                        }
                        break;
                    default:
                        id.val(value);
                        break;
                }
            }
        }
    } catch (e) {
        alert(e);
    }
}
/*
 自动给控件赋值、对Lable
 */
function SetWebLable(data) {
    for (var key in data) {
        var id = $("#" + key);
        if (id.attr("id")) {
            var value = $.trim(data[key]).replace("&nbsp;", "");
            id.text(value);
        }
    }
}
/**
 文本框只允许输入数字
 **/
function IsNumber(obj) {
    $("#" + obj).bind("contextmenu", function () {
        return false;
    });
    $("#" + obj).css("ime-mode", "disabled");
    $("#" + obj).keypress(function (e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
    });
}

/**
 只能输入数字和小数点
 **/
function IsMoney(obj) {
    $("#" + obj).bind("contextmenu", function () {
        return false;
    });
    $("#" + obj).css("ime-mode", "disabled");
    $("#" + obj).bind("keydown", function (e) {
        var key = window.event ? e.keyCode : e.which;
        if (isFullStop(key)) {
            return $(this).val().indexOf(".") < 0;
        }
        return (isSpecialKey(key)) || ((isNumber(key) && !e.shiftKey));
    });

    function isNumber(key) {
        return key >= 48 && key <= 57;
    }

    function isSpecialKey(key) {
        return key == 8 || key == 46 || (key >= 37 && key <= 40) || key == 35 || key == 36 || key == 9 || key == 13;
    }

    function isFullStop(key) {
        return key == 190 || key == 110;
    }
}

/**
 * 金额格式(保留2位小数)后格式化成金额形式
 */
function FormatCurrency(num) {
    num = num.toString().replace(/\$|\,/g, "");
    if (isNaN(num))
        num = "0";
    var sign = (num === (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    var cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + "" +
            num.substring(num.length - (4 * i + 3));
    return (((sign) ? "" : "-") + num + "." + cents);
}
/*
 验证是否为空
 */
function IsNullOrEmpty(str) {
    var isOk = false;
    if (str == undefined || str === "" || str === "null") {
        isOk = true;
    }
    return isOk;
}


///将long类型时间转换为YYYY-MM-DD格式
function stringToDate(longDate) {
    return moment(longDate).format('YYYY-MM-DD').toString();
}

///将long类型时间转换为YYYY-MM-DD hh:mm:ss格式
function stringToTime(longDate) {
    return moment(longDate).format('YYYY-MM-DD hh:mm:ss').toString();
}


//得到选中行ID（复选框name值相同的）
function getChkBoxIds(n, s) {
    var chk = "";
    $('input[name="' + n + '"]:checked').each(function () {
        chk += $(this).val() + ",";
    });
    if (chk.length <= 0) {
        chk = "";
    }
    if (chk.length > 0) {
        chk = chk.substr(0, chk.length - 1);
    }
    if (s === 1) {
        if (chk.split(",").length > 1) {
            chk = "";
        }
    }
    return chk;
}
//得到所有行复选框ID
function getAllChkBoxIds(n, s) {
    var chk = "";
    $('input[name="' + n + '"]').each(function () {
        chk += $(this).val() + ",";
    });
    if (chk.length <= 0) {
        chk = "";
    }
    if (chk.length > 0) {
        chk = chk.substr(0, chk.length - 1);
    }
    if (s === 1) {
        if (chk.split(",").length > 1) {
            chk = "";
        }
    }
    return chk;
}


// window.onload = function () {
//     $(window).scroll(function () {
//         scrollPage(window, '.MyPosition', CurrentTabName);
//     });
// }
//
// function scrollPage(obj, target, CurrentTabName) {
//     debugger
//     if ($(obj).scrollTop() > 40) {
//         parent.frames[CurrentTabName].$(target).css({
//             "box-shadow": "0 2px 6px #bfbfbf",
//             "border-bottom": "0",
//             "position": "fixed"
//         });
//     } else {
//         parent.frames[CurrentTabName].$(target).css({
//             "box-shadow": "",
//             "border-bottom": "1px solid #eee",
//             "position": ""
//         });
//     }
// }
//
// //获取选中的iframe的名词
// var CurrentTabName = function () {
//     var iframe = $("iframe.area-content:visible", parent.document.body).attr('name');
//     if (iframe == undefined && iframe == null) {
//         iframe = "";
//     }
//     return iframe;
// }();