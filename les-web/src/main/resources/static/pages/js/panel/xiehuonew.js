/// <reference path="typings/index.d.ts" />
//数据更新频率（毫秒）
var updateInterval = 10000;
//页面滚动频率（毫秒）
var scrollInterval = 1000;
//每次滚动高度
var scrollHeight = 40;
//var url = "/iles/panel/queryQueueUploadRecord";
var url;
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
var lessHeight=720;
$(function () {
    url = $("#xiehuoUrl").val();
    el = $('#grid');
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
    h= append(h, 2, '0');
    var mm = date.getMinutes();
    mm = append(mm, 2, '0');
    var s = date.getSeconds();
    s = append(s, 2, '0');
    return y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + s;
}

function append(s, n, ch) {
    ch = ch || ' ';
    s = s + '';
    while (s.length<n){
        s = ch + s;
    }
    return s;
}

function update() {
    $.post(url, function (data) {
        console.log('更新数据:'+ data);
        console.log(data);
        var list=data.data;
        var finish =0;
        for(var i = 0; i < list.length; i++){
        	var obj = list[i];
        	if(obj.status=='已完成'){
        		finish++;	
        	}
        }
        total.text(finish+"/"+list.length);
        updateItems(list);
    }, 'json');
}

function updateItems(items) {
    if (!load) {
        console.log('首次读取');
        el.empty();
        for (var i = 0; i < items.length; i++) {
            el.append(getElement(items[i]));
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
            el.append(getElement(items[i]));
        }
        height = el.height();
        el.css('top',0);
    }else{
        for (var i = 0; i < items.length; i++) {
            el2.append(getElement(items[i]));
        }
    }
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

function subCHString(str, len) {
    var newLength = 0;
    var newStr = ""; 
    var chineseRegex = /[^\x00-\xff]/g; 
    var singleChar = ""; 
    var strLength = str.replace(chineseRegex,"**").length; 
    for(var i = 0;i < strLength;i++) 
    { 
        singleChar = str.charAt(i).toString(); 
        if(singleChar.match(chineseRegex) != null) {
            newLength += 2; 
        }else{
            newLength++; 
        } 
        if(newLength > len){
            break; 
        } 
        newStr += singleChar; 
    } 
    if(strLength > len) {
        newStr += "..."; 
    } 
    return newStr;
}
//TODO 取需要显示的元素
function getElement(item) {
    var tr = $('<tr>');
    var supplierName =  item.supplierName;
    if(supplierName !=null &&supplierName.length >6){
    	supplierName = subCHString(supplierName, 12);
    }
    if(item.isUrgent==1){
        tr.append($("<td>").css('width','130px').css('color','#e60012').text(item.manualNo));
	}else{
		tr.append($("<td>").css('width','130px').text(item.manualNo));
	}
    tr.append($("<td>").css('width','280px').text(supplierName));
    tr.append($("<td>").css('width','350px').text(item.arrivalCode));
    tr.append($("<td>").css('width','180px').text(item.status));
    tr.append($("<td>").css('width','180px').text(item.queueTime));
    if(item.waitTime >0){
        tr.append($("<td>").css('width','200px').text(item.waitTime));
    }else{
    	tr.append($("<td>").css('width','200px').text(0));
    }
    return tr;
}
function stop(){
    clearInterval(scrollHandle);
}
function start(){
    scrollHandle = setInterval(updateScroll,scrollInterval);
}
function toIndex(url){
	parent.location.href=url;
}