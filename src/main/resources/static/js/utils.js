/**
 * 定义原生使用占位符的方法，格式化数据
 * */
String.prototype.format = function (arguments) {
    // 数据长度为空，则直接返回
    if (arguments.length == 0) {
        return this;
    }
    // 使用正则表达式，循环替换占位符数据
    var result = this;
    arguments.forEach(function (value, key, map) {
        result = result.replace(new RegExp("\\[" + key + "\\]", "g"), value);
    });
    return result;
};
function loadJs() {
    try {
        $.blockUI({
            css: {
                border: 'none',
                padding: '15px',
                backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                opacity: .5,
                color: '#fff'
            }
        });
    } catch (e) {

    }
}

function closeBlockUI() {
    $(".blockUI").remove();
}
/**
 * @param requsetUrl 访问地址
 * @param paramData 访问参数
 * @param callback 回调函数
 */
function sendRequest(requsetUrl, paramData, callback, blockUIFlag) {
    if (blockUIFlag==null || blockUIFlag == undefined || blockUIFlag==true) {
        loadJs();
    }
    $.ajax({
        cache: false,
        type: "post",
        url: requsetUrl,
        data: paramData,
        success: function (back) {
            try {

                callback(back);
            } catch (e) {

            }
            closeBlockUI();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            closeBlockUI();
        }
    });
}
//ajax 同步调用
function sendRequestTB(requsetUrl, paramData, callback) {
    loadJs();
    $.ajax({
        cache: false,
        type: "post",
        url: requsetUrl,
        data: paramData,
        async: false,				//同步
        success: function (back) {
            try {

                callback(back);
            } catch (e) {
                //showPagePrompts('error',"操作发生异常,远程调用失败！");
            }
            closeBlockUI();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            closeBlockUI();
            //alert("远程调用失败！");
            //showPagePrompts('error',"操作发生错误,远程调用失败！");
        }
    });
}//ajax 同步调用
function sendRequestJSONTB(requsetUrl, paramData, callback) {
    loadJs();
    $.ajax({
        cache: false,
        type: "post",
        url: requsetUrl,
        data: paramData,
        dataType: "json",
        async: false,				//同步
        success: function (back) {
            try {

                callback(back);
            } catch (e) {
                //showPagePrompts('error',"操作发生异常,远程调用失败！");
            }
            closeBlockUI();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            closeBlockUI();
            //alert("远程调用失败！");
            //showPagePrompts('error',"操作发生错误,远程调用失败！");
        }
    });
}
function sendRequestTBorYB(requsetUrl, paramData, callback, async) {
    loadJs();
    $.ajax({
        cache: false,
        type: "post",
        url: requsetUrl + "?timestamp=" + new Date().getTime(),
        data: paramData,
        async: async,				//false同步, true异步
        success: function (back) {
            try {
                callback(back);
            } catch (e) {
                //showPagePrompts('error',"操作发生异常,远程调用失败！");
            }
            closeBlockUI();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            closeBlockUI();
            //alert("远程调用失败！");
            //showPagePrompts('error',"操作发生错误,远程调用失败！");
        }
    });
}
/**获取当前时间 格式：yyyy-mm-dd HH:MM:ss*/
function getCurentTime() {
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();  		//秒
    var clock = year + "-";
    if (month < 10)  clock += "0";
    clock += month + "-";
    if (day < 10) clock += "0";
    clock += day + " ";

    if (hh < 10)  clock += "0";
    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm;
    if (ss < 10) clock += '0';
    clock += ss;
    return (clock);
}

function loadContent(container, url, callback) {
    $(container).append('<img src="' + BAOFOO.contextPath + '/resource/img/loaders/1d_2.gif">');
    $(container).load(url, function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success") {
            if (callback == undefined) {
                return;
            }
            callback();
        }
    });
}

/**
 * 获取参数
 *
 * @param param
 * @returns
 */
function getPrama(param) {
    if (typeof (param) == "undefined") {
        return null;
    }
    return param;
}
/**
 * 格式化字符串，暂支持0-99个参数替换
 * @param template 需要格式化的参数
 * @param params 替换参数
 * @returns
 */
function formatString(template, params) {
    if (params === undefined) {
        return template;
    }
    var tmpArr = [];
    for (var i = 0; i < template.length;) {
        var ch = template.charAt(i);
        if (ch === '{') {
            i++;
            var index = parseInt(template.charAt(i));
            i++;
            ch = template.charAt(i);
            if (ch != '}') {
                tmpArr.push(params[index * 10 + parseInt(template.charAt(i))]);
                i++;
            } else {
                tmpArr.push(params[index]);
            }
        } else {
            tmpArr.push(ch);
        }
        i++;
    }
    return tmpArr.join('');
}

/**
 * 获取输入控件
 *
 * @param editId
 * @returns
 */
function getBaofooEdit(editId) {
    return document.getElementById(editId);
}

/**
 * 获取输入控件
 *
 * @param editId
 * @returns
 */
function getBaofooEditVersion(editId) {
    try {
        return getBaofooEdit(editId).get_version();
    } catch (e) {
        //
        return "";
    }
}

/**
 * 获取输入控件
 *
 * @param editId
 * @returns
 */
function setBaofooEditFontSize(editId, fontSize) {
    //Times Roman
    //Arial
    try {
        return getBaofooEdit(editId).set_font("Times Roman", fontSize);
    } catch (e) {
        //
    }
}

/**
 * 获取输入控件的内容
 *
 * @param editId
 * @param dpass
 * @returns
 */
function getBaofooEditContent(editId, dpass) {
    try {
        return getBaofooEdit(editId).get_text(dpass);
    } catch (e) {
        return "";
    }
}

var specSequence = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
    'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
    'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'];

function getRandomMD5key(codeCount) {
    var randomCode = "";
    for (var i = 0; i < codeCount; i++) {
        var strRand = specSequence[Math.floor(Math.random() * 32)];
        randomCode += strRand;
    }
    return randomCode.toLocaleLowerCase();
}

/**
 * 去除空格、换行等
 * @param Str
 * @returns {String}
 * onkeyup="value=value.replace(/(^\s*)|(\s*$)/g,'')" 去除左右空格
 */
function IgnoreSpaces(Str) { //能去左、中、右的空格等
    var ResultStr = "";
    //
    var Number = " ";
    for (var i = 0; i < Str.length; i++) {
        var c = Str.charAt(i);
        if (Number.indexOf(c) == -1) {
            ResultStr += Str.charAt(i);
        }
    }
    return ResultStr;
}
//自动补零  pad(12,4)=0012
function pad(num, n) {
    var len = num.toString().length;
    while (len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}
//替换全部 replaceAll
String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
}

function checkIdcard(idcard) {
    var Errors = new Array(
        "true",
        "身份证号码位数不对",
        "身份证号码出生日期超出范围或含有非法字符",
        "身份证号码校验错误",
        "身份证地区非法"
    );
    var area = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外"
    }

    var idcard, Y, JYM;
    var S, M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");

    //地区检验
    if (area[parseInt(idcard.substr(0, 2))] == null) return Errors[4];

    //身份号码位数及格式检验
    switch (idcard.length) {
        case 15:
            if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 )) {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
            } else {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
            }

            if (ereg.test(idcard)) return Errors[0];
            else return Errors[2];
            break;
        case 18:
            //18位身份号码检测
            //出生日期的合法性检查
            //闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
            //平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
            if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0 )) {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
            } else {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
            }
            if (ereg.test(idcard)) {//测试出生日期的合法性
                return Errors[0]; //检测ID的校验位
            } else return Errors[2];
            break;
        default:
            return Errors[1];
            break;
    }
}

/**
 * 获取选中的checkBox值
 * @param checkboxName
 * @returns {string}
 */
function getCheckboxValue(checkboxName) {
    var ids = "";
    $("input[type=checkbox][name=" + checkboxName + "]:checked").each(
        function () {
            ids += "," + $(this).val();
        }
    );
    return ids.substr(1).trim();
}

function wopen(title, url, width, height, initMaximizable) {
    if (url.indexOf("?") == -1) {
        url = url + "?t=" + new Date().getTime();
    } else {
        url = url + "&t=" + new Date().getTime();
    }
    width = width ? width : "90%";
    height = height ? height : "90%";
    $("#divPanel").dialog("destroy");
    $("#divPanel").remove();
    $(document.body).append("<div id='divPanel' data-options=\"iconCls:'icon-save',resizable:true,modal:true,maximizable:true,collapsible:true\" class='easyui-dialog'></div>");
    var $_DIV = $("#divPanel");
    $_DIV.html('<iframe src="" style="width: 100%;height: 100%;border:0px;" id="iframe_DIV_Panel"></iframe>');
    $_DIV.dialog({
        title: title,
        autoOpen: true,
        width: width,
        height: height
    });
    $("#divPanel").css("height", height);
    //禁止在Chrome中加载两次请求
    $("#iframe_DIV_Panel").attr("src", url);
    $(".window-title").dblclick(function () {
        $(".panel-tool-max").click();
    });
    if (initMaximizable == true) {
        $(".panel-tool-max").click();
    }
}

function check(valOnw, valTow) {
    var reg = /\S/;
    if (valTow == null || !reg.test(valTow) || valTow == undefined) {
        valOnw.css('borderColor', 'red');
        // valOnw.append("<p>Hello world!</p>");
        return true;
    } else {
        valOnw.css('borderColor', '');
        return false;
    }
}

function checkCity(valOnw, valTow) {
    var reg = /\d/;
    if (valTow == null || !reg.test(valTow) || valTow === 0) {
        valOnw.parent().css('borderColor','red')
        // valOnw.append("<p>Hello world!</p>");
        return true;
    } else {
        valOnw.parent().css('borderColor', '');
        return false;
    }
}
//判断字符串/数组/对象/不为空时返回true
function isNotNull(obj) {
    if (obj instanceof Object) {
        for (var a in obj) {
            return true
        }
        return false
    }
    return typeof (obj) != 'undefined' && obj !== null && ($.isArray(obj) ? obj.length !== 0 : obj !== "");
}

/**
 * 数字替换方法
 * @param value 原始值
 * @param decimalLength 保留小数位
 * @returns {*|void|string}
 */
function numberReplace(value, decimalLength) {
    var val = value;
    if (!val) {
        return "";
    }
    val = val.replace(/[^\d+(\.\d+\-)?$]/gi, '');
    var index = val.indexOf('.');
    if (index === 0) {
        return "";
    }
    if (index > 0) {
        var priceValueArray = val.split(".");
        var decimal = priceValueArray[1];
        val = priceValueArray[0];
        if(val == 0) {
            val = val[0]
        }
        if (decimalLength > 0) {
            val += "." + (decimal.length > decimalLength ? decimal.substr(0, decimalLength) : decimal);
        }
    }
    if (val < 0) {
        return "";
    }

    return val;
}
// 获取参数Location的附加参数
function getLocationParameter() {
    let url = location.search; //获取url中"?"符后的字串
    let theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
/**
 * map转数组。
 * 
 * @param {Map}map
 *            map对象
 * @return 数组
 */
function mapToArr(map) {
    var list = [];
    for (var key in map) {
        list.push([key, map[key]]);
    }
    return list;
}
/**
 * 倒计时方法
 * @param value 原始值
 * @returns {*|void|string}
 */
function clock(value){
    var today=new Date(),//当前时间
        h=today.getHours(),
        m=today.getMinutes(),
        s=today.getSeconds();
      var stopTime = new Date(localStorage.getItem('stopTime')),//结束时间
        stopH=stopTime.getHours(),
        stopM=stopTime.getMinutes(),
        stopS=stopTime.getSeconds();
      var shenyu=stopTime.getTime()-today.getTime(),//倒计时毫秒数
        shengyuD=parseInt(shenyu/(60*60*24*1000)),//转换为天
        D=parseInt(shenyu)-parseInt(shengyuD*60*60*24*1000),//除去天的毫秒数
        shengyuH=parseInt(D/(60*60*1000)),//除去天的毫秒数转换成小时
        H=D-shengyuH*60*60*1000,//除去天、小时的毫秒数
        shengyuM=parseInt(H/(60*1000)),//除去天的毫秒数转换成分钟
        M=H-shengyuM*60*1000;//除去天、小时、分的毫秒数
        S=parseInt((shenyu-shengyuD*60*60*24*1000-shengyuH*60*60*1000-shengyuM*60*1000)/1000)//除去天、小时、分的毫秒数转化为秒
        document.getElementById("remainingTime").innerHTML=(shengyuD+'d '+shengyuH+'h '+ shengyuM+'min '+S+ 's');
    
        // setTimeout("clock()",500);
        setTimeout(clock,500);
}
// 普通时间转格林威治时间
function toGLWZ(time){
    // 获取某个时间格式的时间戳
    let timestamp2 = Date.parse(new Date(time));
    timestamp2 = timestamp2 / 1000;
    // 将当前时间换成时间格式字符串
    let newDate = new Date();
    newDate.setTime(timestamp2 * 1000);
    return newDate.toISOString();
}

// 普通时间转时间戳
function timeStamp(time) {
    let date = new Date(time);
    // 有三种方式获取
    let timeStamp = date.getTime();
    return timeStamp
}
// 格林威治时间转换
function UTC(time) {
    let dateee = new Date(time).toJSON();
    let date = new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    return date
}
// 计算时间差
function timeDifference(val1,val2) {
    var date1 = new Date(val1)
    var date2 = new Date(val2)
    var s1 = date1.getTime(),s2 = date2.getTime();
    var total = (s2 - s1)/1000;
    var day = parseInt(total / (24*60*60));//计算整数天数
    var afterDay = total - day*24*60*60;//取得算出天数后剩余的秒数
    var hour = parseInt(afterDay/(60*60));//计算整数小时数
    var afterHour = total - day*24*60*60 - hour*60*60;//取得算出小时数后剩余的秒数
    var min = parseInt(afterHour/60);//计算整数分
    var afterMin = total - day*24*60*60 - hour*60*60 - min*60;//取得算出分后剩余的秒数
    return [day,hour,min,afterMin].join(':')
}
// 获取当前时间
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}

//除法

function accDiv(num1,num2){

    var t1,t2,r1,r2;

    try{t1 = num1.toString().split('.')[1].length;}catch(e){

        t1 = 0;

    }

    try{t2=num2.toString().split(".")[1].length;}catch(e){t2=0;}

    r1=Number(num1.toString().replace(".",""));

    r2=Number(num2.toString().replace(".",""));

    return (r1/r2)*Math.pow(10,t2-t1);

}


//乘法

function accMul(arg1,arg2)

{

    var m=0,s1=arg1.toString(),s2=arg2.toString();

    try{m+=s1.split(".")[1].length}catch(e){}

    try{m+=s2.split(".")[1].length}catch(e){}

    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)

}

//加法

function accAdd(arg1,arg2){

    var r1,r2,m;

    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}

    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}

    m=Math.pow(10,Math.max(r1,r2))

    return (arg1*m+arg2*m)/m

}

//减法

function Subtr(arg1,arg2){

    var r1,r2,m,n;

    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}

    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}

    m=Math.pow(10,Math.max(r1,r2));

    n=(r1>=r2)?r1:r2;

    return ((arg1*m-arg2*m)/m).toFixed(n);

}
    