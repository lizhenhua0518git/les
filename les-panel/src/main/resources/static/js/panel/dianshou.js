/// <reference path="typings/index.d.ts" />

//数据更新频率（毫秒）
var updateInterval= 10000;
//页面滚动频率（毫秒）
var scrollInterval= 6000;
//每次滚动高度
var scrollHeight = 40;
var url = "/panel/receivePanel";
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
var element = "";
$(function () {
    //注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作

    layui.use('element', function(){
          element = layui.element;
    });


    el = $('.grid');
    total = $('.total');
    update();
    updateHandle=setInterval(update, updateInterval);
    scrollHandle=setInterval(updateScroll, scrollInterval);
    setInterval(updateTime, 1000);


});

function updateTime() {
    $('.time').html(format(new Date()));
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
    $.post(url, function (data) {
        console.log('更新数据');
        console.log(data);
        updateItems(data.data);
    }, 'json');
}

function updateItems(items) {
    if (!load) {
        console.log('首次读取');
        el.empty();
        for (var i = 0; i < items.length; i++) {
            el.append(getElement(items[i], i));
        }
        height = el.height();
        load = true;

        el2 = el.clone();
        el.parent().append(el2);
        el2.css('top', height);
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
        for (var i = 0; i < items.length; i++) {
            el.append(getElement(items[i], i));
        }
        height = el.height();
        el.css('top',0);
    }else{
        for (var i = 0; i < items.length; i++) {
            el2.append(getElement(items[i], i));
        }
    }
    total.text(items.length);
    
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
    var supplierName = subCHString(item.supplierName, 20,3);
    var lengthTime = subCHString(item.lengthTime, 8,2);
    var pointerName = subCHString(item.pointerName, 13,2);
    	tr.append($("<td>").css('width','7%').text((i > 8?(i+1):"0"+(i+1))));
        tr.append($("<td>").css('width','35%').text(supplierName));
        tr.append($("<td>").css('width','12%').text(item.statusStr));
        tr.append($("<td>").css('width','16%').text(pointerName));
        tr.append('<td width="15%"><div class="layui-progress layui-progress-big"  lay-showPercent="true">\n' +
            '  <div class="layui-progress-bar layui-bg-green" lay-percent="'+item.rateValue+'" style="width: '+item.rateValue+'">' +
            '<span class="layui-progress-text">'+item.rateValue+'</span></div>\n' +
            '</div></td>');


        tr.append($("<td>").css('width','15%').text(lengthTime));


    return tr;
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

    if(strLength > len)
    {
        if(flag==1){
            newStr += ".";
        }else if(flag==2){
            newStr += "..";
        }else{
            newStr += "...";
        }

    }
    return newStr;
}