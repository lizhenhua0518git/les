/// <reference path="typings/index.d.ts" />
var wareHouse = "00500300";//仓库编码
//数据更新频率（毫秒）
var updateInterval=20000;
//页面切换频率（毫秒）
var updatePageInterval=6000;
//当前使用
var items;
//当前显示元素索引
var current=0;
//每页显示数量
var size=2;
//下次要使用
var items2;
//没有数据提示
var noItemText="暂无紧急物料";
var wmsContextPath = config.wmsConfig.contextPath;
var url=wmsContextPath+"panel/queryInspectRecordZK";
//是否首次加载
var load=false;

$(function(){
	var index =0;
	//3秒轮播一次
	var timer = setInterval(function(){
	    index = (index == 1) ? 0 : index + 1;          
	    //某个div显示，其他的隐藏
	    $(".dev").hide().eq(index).show();    
	}, 10000);
	update();
	setInterval(update,updateInterval);
	setInterval(updateScroll,updatePageInterval);
	setInterval(updateTime, 500);
	$("#hide_div").click(function(obj){
		$("#title_div").hide();
		$("#show_div").show();
	});
	$("#show_div").click(function(obj){
		$("#title_div").show();
		$("#show_div").hide();
	});
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

function update(){
	queryArea();
	 
}

function updateScroll()
{ 
    var em = $('#em');
    if(items==null){
        items=items2;
        items2=null;
    }
    if(items==null||items.length==0){
        em.text(noItemText);
        return;
    }
    if(items.length <= size){
    	em.empty();
    	for(var i=0; i<items.length;i++){
    		 var el=getElement(items[i]);
             em.append(el);
    	}
    	return false;
    }
    current=current+size;
    if(current>=items.length){
        if(items2!=null){
            items=items2;
        }
        current=0;
    }
    //TODO 调整切换效果
    em.animate({'opacity':0},200,function(){
        em.empty();
        for(var i=current;i<current+size;i++){
        	if(i<items.length){
        		var el=getElement(items[i]);
                em.append(el);
        	}
        }
        em.animate({'opacity':100},200);
    });
}
//TODO 取需要显示的元素
function getElement(item){
    var div=$('<div>').text(item.text);
    return div;
}

function queryArea(){
	$("#inspecContent2").hide();
	var  areaUrl =wmsContextPath+"panel/queryAreaDJ";
	$.post(areaUrl,{wareHouse:wareHouse},function(data){
		console.log('更新数据');
		console.log(data.data);
		var title =  '<span class="list list-primary" id="checkTotal"></span>';
		title += '<span class="list list-warning" id="isUrgentTotal"></span>';
		title += '<span class="list list-danger" id="overtimeTotal"></span>';
		title += '<span class="list list-info" id="dumpTotal"></span>';
		$("#inpsecTitle").html(title);
		var list = data.data;
		var content = "";
		for(var i=0; i<12; i++){
			content += '<div class="col-md-4 col-xs-4 col-sm-6"><div class="grid">';
			var area = list[i].areaCode.split("-")[2]+"";
			content += '<div class="team-main"> <span class="text" style="font-size: 23px;">';
			content += area;
			content += '</span><span class="team team-primary" style="width: 80px;" id="'+list[i].areaCode+'_check">';
			content += '</span><span class="team team-warning" style="width: 80px;" id="'+list[i].areaCode+'_isUrgent">';
			content += '</span><span class="team team-danger" style="width: 80px;" id="'+list[i].areaCode+'_overtime">';
			content += '</span><span class="team team-info" style="width: 80px;" id="'+list[i].areaCode+'_dump"></span></div>';
			content += '</div></div>';
		}
		$("#inspecContent1").html(content);
		content = "";
		for(var i=12; i<list.length; i++){
			content += '<div class="col-md-4 col-xs-4 col-sm-6"><div class="grid">';
			var area = list[i].areaCode.split("-")[2]+"";
			content += '<div class="team-main"> <span class="text" style="font-size: 23px;">';
			content += area;
			content += '</span><span class="team team-primary" style="width: 80px;" id="'+list[i].areaCode+'_check">';
			content += '</span><span class="team team-warning" style="width: 80px;" id="'+list[i].areaCode+'_isUrgent">';
			content += '</span><span class="team team-danger" style="width: 80px;" id="'+list[i].areaCode+'_overtime">';
			content += '</span><span class="team team-info" style="width: 80px;" id="'+list[i].areaCode+'_dump"></span></div>';
			content += '</div></div>';
		}
		$("#inspecContent2").html(content);
		$.post(url,{wareHouse:wareHouse},function(data){
			console.log('更新数据');
			console.log(data.data);
			var map = data.data;
			var checkList =map.check;
			var dumpList =map.dump;
			var overtimeList =map.overtime;
			var isUrgentList =map.isUrgen;
			if(null !=checkList){
				var checkSum =0;
				for(i=0;i<checkList.length;i++){
					var id= checkList[i].areaCode+"_check";
					$("#"+id).html(checkList[i].sum);
					checkSum= parseInt(checkSum)+ parseInt(checkList[i].sum);
				}
				$("#checkTotal").html("待检"+checkSum);
			}
			if(null !=dumpList){
				var dumpSum =0;
				for(i=0;i<dumpList.length;i++){
					var id= dumpList[i].areaCode+"_dump";
					$("#"+id).html(dumpList[i].sum);
					dumpSum= parseInt(dumpSum)+ parseInt(dumpList[i].sum);
				}
				$("#dumpTotal").html("待转储"+dumpSum);
			}
			if(null !=overtimeList){
				$("#overtimeTotal").html("超期"+overtimeList.length);
				for(i=0;i<overtimeList.length;i++){
					var id= overtimeList[i].areaCode+"_overtime";
					var value =$("#"+id).html();
					$("#"+id).html(parseInt(value)+1);
				}
			}
			if(null !=isUrgentList){
				$("#isUrgentTotal").html("紧急 "+isUrgentList.length);
				for(i=0;i<isUrgentList.length;i++){
					var id= isUrgentList[i].areaCode+"_isUrgent";
					var value =$("#"+id).html();
					$("#"+id).html( parseInt(value)+1);
				}
			}else{
				 $("#isUrgentTotal").html("紧急0 ");
			}
			items2=map.txtList;
		},'json');
		for(i=0;i<list.length;i++){
			var id= list[i].areaCode+"_check";
			if($("#"+id).html()==''){
				$("#"+id).html("0");
			}
			id= list[i].areaCode+"_dump";
			if($("#"+id).html()==''){
				$("#"+id).html("0");
			}
			id= list[i].areaCode+"_overtime";
			if($("#"+id).html()==''){
				$("#"+id).html("0");
			}
			id= list[i].areaCode+"_isUrgent";
			if($("#"+id).html()==''){
				$("#"+id).html("0");
			}
		}
	},'json');
}





