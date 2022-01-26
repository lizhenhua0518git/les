/// <reference path="typings/index.d.ts" />

//数据更新频率（毫秒）
var updateInterval = 30000;
//页面滚动频率（毫秒）
var scrollInterval = 2000;
//每次滚动高度
var scrollHeight = 1;
var url = "/iles/panel/queryAssembleRecord";
//是否是首次加载
var load = false;
//当前显示元素
var el;
//下次显示元素
var el2;
//当前显示元素高度
var height;
var updateHandle;
var scrollHandle;
//是否无需滚动
var less = true;
//无需滚动的高度
var lessHeight = 200;

//TODO 截图中有的单元格文字是红色，请自行按情况处理

$(function () {
    el = $('.grid');
    update();
    updateHandle = setInterval(update, updateInterval);
    scrollHandle = setInterval(updateScroll, scrollInterval);
    setInterval(updateTime, 2000);
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
    $.post(url, function (data) {
        console.log('更新数据');
        console.log(data);
        var list = data.data;
        if(null !=list){
        	for(i=0;i<list.length;i++){
        		var map = list[i];
        		if(null !=map.plan){
        			 $('.total').html(map.plan);
        		}
        		if(null !=map.pick){
       			 $('.jianliao').html(map.pick);
       		    }
        		if(null !=map.assembling){
       			 $('.jipei').html(map.assembling);
       		    }
        		if(null !=map.check){
          			 $('.fuhe').html(map.check);
          		}
        		if(null !=map.outTotal){
         			 $('.chuku').html(map.outTotal);
         		}
        		if(null !=map.list){
        			  updateItems(map.list);
        		}
        		
        	}
        	
        }
        
      
    }, 'json');
}

function updateItems(items) {
  /*  if (!load) {
        console.log('首次读取');
        console.log(items);
        el.empty();*/
        for (var i = 0; i < items.length; i++) {
            el.append(getElement(items[i]));
        }
/* height = el.height();
        load = true;

        el2 = el.clone();
        el.parent().append(el2);
        el2.css('top', height);
    }
    var h = el.height();
    if (h <= lessHeight) {
        less = true;
    } else {
        less = false;
    }

    el2.empty();

    if (less) {
        el.empty();
        for (var i = 0; i < items.length; i++) {
            el.append(getElement(items[i]));
        }
        height = el.height();
        el.css('top', 0);
    } else {
        for (var i = 0; i < items.length; i++) {
            el2.append(getElement(items[i]));
        }
    }*/
    // $('tr', el2).css('background-color', 'green');
    // $('tr', el).css('background-color', '');
}

var x = 0;

function updateScroll() {
	var tbody = document.getElementById("scrollData");
	var row = tbody.insertRow(tbody.rows.length);
	for(j=0;j<tbody.rows[0].cells.length;j++){//循环第一行的所有单元格的数据，让其加到最后新加的一行数据中（注意下标是从0开始的）
		var cell = row.insertCell(j);//给新插入的行中添加单元格
		cell.style.color = tbody.rows[0].cells[j].style.color;
		//cell.height = "80px";//一个单元格的高度，跟css样式中的line-height高度一样
		cell.innerHTML = tbody.rows[0].cells[j].innerHTML;//设置新单元格的内容，这个根据需要，自己设置
		}
		tbody.deleteRow(0);//删除table的第一行
  /*  if (!load) return;
    if (less) return;

    el.css('top', -x);
    el2.css('top', parseInt(-x + height));
    x = parseInt(x + scrollHeight);
        if (x > height) {
            x = 0;
            height = el2.height();
            el.css({ top: height});
            var t = el2;
            el2 = el;
            el = t;
        }*/
    }

    //TODO 取需要显示的元素
    function getElement(item) {
        var tr = $('<tr>');
        //修改宽度同时修改html中的宽度
        tr.append($("<td>").css("width", "170px").text(item.subFactory));
        tr.append($("<td>").css("width", "170px").text(item.stationCode));
        tr.append($("<td>").css("width", "170px").text(item.carrierCode));
        var lowerFrameCount =( item.lowerFrameCount==undefined?"":item.lowerFrameCount)+(item.totalCount==undefined?"":("/"+item.totalCount));
        var assembledCount =  (item.assembledCount==undefined?"":item.assembledCount)+(item.totalCount==undefined?"":("/"+item.totalCount));
        var checkCount =  (item.checkCount==undefined?"":item.checkCount)+(item.totalCount==undefined?"":("/"+item.totalCount));
        var moveCount =  (item.moveCount==undefined?"":item.moveCount)+(item.totalCount==undefined?"":("/"+item.totalCount));
       	
        if(item.lowerTimeOut !=null && item.lowerTimeOut !="-" && item.lowerTimeOut > 0){
        	tr.append($("<td>").css("width", "170px").css("color","red").text(lowerFrameCount));
        }else{
        	tr.append($("<td>").css("width", "170px").text(lowerFrameCount));
        }
        
        if(item.assembleTimeOut !=null && item.assembleTimeOut !="-" && item.assembleTimeOut > 0){
        	tr.append($("<td>").css("width", "170px").css("color","red").text(assembledCount));
        }else{
        	tr.append($("<td>").css("width", "170px").text(assembledCount));
        }
        
        if(item.checkTimeOut !=null && item.checkTimeOut !="-" && item.checkTimeOut > 0){
        	tr.append($("<td>").css("width", "170px").css("color","red").text(checkCount));
        }else{
        	tr.append($("<td>").css("width", "170px").text(checkCount));
        }
        
        if(item.moveTimeOut !=null && item.moveTimeOut !="-" && item.moveTimeOut > 0){
        	tr.append($("<td>").css("width", "170px").css("color","red").text(moveCount));
        }else{
        	tr.append($("<td>").css("width", "170px").text(moveCount));
        }
        
        return tr;
    }


    function stop() {
        clearInterval(scrollHandle);
    }

    function start() {
        scrollHandle = setInterval(updateScroll, scrollInterval);
    }