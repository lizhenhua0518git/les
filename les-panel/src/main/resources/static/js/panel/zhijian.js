/// <reference path="typings/index.d.ts" />

//数据更新频率（毫秒）
var updateInterval= 10000;
//页面滚动频率（毫秒）
var scrollInterval= 2500;
//每次滚动高度
var scrollHeight = 40;
var url = "/panel/inspectPanel";
//是否是首次加载
var load = false;
//当前显示元素
var el;
//下次显示元素
var el2;
//当前显示元素高度
var height;
var total;
var updateHandle;
var scrollHandle;
//是否无需滚动
var less=true;
//无需滚动的高度
var lessHeight=256;

$(function () {
    el = $('.grid');
    total = $('.total');
    update();
    updateHandle=setInterval(update, updateInterval);
    scrollHandle=setInterval(updateScroll, scrollInterval);
    setInterval(updateTime, 500);
});

function updateTime() {
    $('.time').text(format(new Date()));
}

function format(date){
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = append(m, 2, '0');
    var d = date.getDate();
    d = append(d, 2, '0');
    var h = date.getHours();
    h = append(h, 2, '0');
    var mm = date.getMinutes();
    mm = append(mm, 2, '0');
    var s = date.getSeconds();
    s = append(s, 2, '0');
    return y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + s;
}

function append(s, n, ch) {
    ch = ch || ' ';
    s = s + '';
    while (s.length < n) {
        s = ch + s;
    }
    return s;
}

function update() {
    var head = {
        //总数量
        totalCount:0,
        //质检数量
        inspectedNum:0,
        //未质检数量
        unInspectedNum:0,
        //合格
        totalQualifiedNum:0,
        //不合格
        totalUnQualifiedNum:0
    }


    $.get(url, function (data) {
        console.log('更新数据');
        console.log(data);
        if (data.data !=null ) {
            head.totalCount = data.data.totalCount;
            head.totalQualifiedNum =data.data.totalQualifiedNum;
            head.inspectedNum =data.data.inspectedNum;
            head.totalUnQualifiedNum =data.data.totalUnQualifiedNum;
            head.unInspectedNum =data.data.unInspectedNum;
            updateItems(data.data);
            updateHead(head);
        }
    }, 'json');
}

function updateHead(data){
    $('#info1').html(data.totalCount+'/'+data.inspectedNum+'/'+data.unInspectedNum);
    $('#info2').html(data.totalQualifiedNum);
    $('#info3').html(data.totalUnQualifiedNum);
}
function updateItems(items) {
    if (!load) {
        console.log('首次读取');
        el.empty();
        if (items !=null && items.listInspect instanceof Array) {
            for (var i = 0; i < items.listInspect.length; i++) {
                el.append(getElement(items.listInspect[i], i));
            }
            height = el.height();
            load = true;

            el2 = el.clone();
            el.parent().append(el2);
            el2.css('top', height);
        }
    }
    
    var h = el.height();
    if(h<=lessHeight){
        less=true;
    }else{
        less=false;
    }
    
    el2.empty();
    if(less){
        el.empty();
        for (var i = 0; i < items.listInspect.length; i++) {
            el.append(getElement(items.listInspect[i], i));
        }
        height = el.height();
        el.css('top',0);
    }else{
        for (var i = 0; i < items.listInspect.length; i++) {
            el2.append(getElement(items.listInspect[i], i));
        }
    }
    total.text(items.listInspect.length);
    
}

var x = 0;

function updateScroll() {

    if (!load) return;
    if(less)return;

    el.css('top', -x);
    el2.css('top', -x + height);
    x += scrollHeight;
    if (x > height) {
        x = 0;

        height = el2.height();

        el.css({
            top: height
        });

        var t = el2;
        el2 = el;
        el = t;
    }
}


//TODO 取需要显示的元素
function getElement(item, i) {
    var tr = $('<tr>');
    let inspectSchedule = item.inspectSchedule+"%";
    var inspectionTime = subCHString(item.inspectionTime, 16,0);
    var time = subCHString(item.time, 8,0);
    	tr.append($("<td>").css('width','7%').text((i > 8?(i+1):"0"+(i+1))));
        tr.append($("<td>").css('width','14%').text(item.statusStr));
        tr.append($("<td>").css('width','17%').text(item.billCode));
        tr.append($("<td>").css('width','30%').text(inspectionTime));
        // tr.append($('<td><div class="progress">\n' +
        //     '    <div class="progress-bar layui-bg-green" role="progressbar" aria-valuenow="40" \n' +
        //     '        aria-valuemin="0" aria-valuemax="100" style="width: '+inspectSchedule+'; margin: 0 15px;">\n' +
        //         inspectSchedule     +
        //     '    </div>\n' +
        //     '</div></td>'));
        tr.append('<td width="17%"><div class="layui-progress layui-progress-big"  lay-showPercent="true">\n' +
            '  <div class="layui-progress-bar layui-bg-green" lay-percent="'+inspectSchedule+'" style="width: '+inspectSchedule+'">' +
            '<span class="layui-progress-text">'+inspectSchedule+'</span></div>\n' +
            '</div></td>');
        tr.append($("<td>").css('width','15%').text(time));

    return tr;
}
//渲染表头
function putHeader() {
    var totalCount = document.getElementsByClassName("totalCount");

}

function stop(){
    clearInterval(scrollHandle);
}

function start(){
    scrollHandle = setInterval(updateScroll,scrollInterval);
}
function subCHString(str, len,flag)
{
    var newLength = 0;
    var newStr = "";
    var chineseRegex = /[^\x00-\xff]/g;
    var singleChar = "";
    var strLength = str.replace(chineseRegex,"**").length;
    for(var i = 0;i < strLength;i++)
    {
        singleChar = str.charAt(i).toString();
        if(singleChar.match(chineseRegex) != null)
        {
            newLength += 2;
        }
        else
        {
            newLength++;
        }
        if(newLength > len)
        {
            break;
        }
        newStr += singleChar;
    }
    if(flag==1){


    if(strLength > len)
    {
        newStr += "...";
    }
    }
    return newStr;
}