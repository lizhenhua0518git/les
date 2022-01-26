﻿
var layer = layui.layer;
var element = layui.element;
layui.use('element', function(){});

$(function(){
 queryArea(); 
 setInterval(queryArea,30000);
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
    return y + "年" + m + "月" + d + "日  " + h + ":" + mm + ":" + s;
}

function append(s, n, ch) {
    ch = ch || ' ';
    s = s + '';
    while (s.length < n) {
        s = ch + s;
    }
    return s;
}

var carList= null;
var timeNum = 0;
function queryArea(){
 var areaUrl ="/iles/panel/queryAssembleArea?storageLocation=1520";
 $.post(areaUrl,function(map){
	if(map.code==0){
	var jpqCount = 0;
	var JH_wlNum =0,JH_wlPer = '0.00%',WC_wlCount=0,wlNum =0,wlPer = '0.00%',wlRest = 0;
	var carNum = 0,carPer = '0.00%',carRest = 0;
	var JH_gwNum = 0,JH_gwPer = '0.00%',WC_gwCount=0,WC_gwCount=0,gwNum = 0,gwPer = '0.00%',gwRest = 0;
//	var orderNum=0,orderPer='0%',orderRest=0;
	if(map.data.jpqNum!=null){jpqCount = map.data.jpqNum;}
	if(map.data.wlNum!=null){wlNum = map.data.wlNum;}
	if(map.data.JH_wlNum!=null){JH_wlNum = map.data.JH_wlNum;}
	if(map.data.wlSum!=null){wlSum = map.data.wlSum;}
	if(map.data.wlRest!=null){wlRest = map.data.wlRest;}
	if(map.data.carNum!=null){carNum = map.data.carNum;}
	if(map.data.carRest!=null){carRest = map.data.carRest;}
	if(map.data.gwNum!=null){gwNum = map.data.gwNum;}
	if(map.data.JH_gwNum!=null){JH_gwNum = map.data.JH_gwNum;}
	if(map.data.gwRest!=null){gwRest = map.data.gwRest;}
//	if(map.data.orderNum!=null){orderNum = map.data.orderNum;}
//	if(map.data.orderRest!=null){orderRest = map.data.orderRest;}
	var WC_wlCount = null;
	var WC_gwCount = null;
	if(map.data.WC_wlCount!=null){WC_wlCount = map.data.WC_wlCount;}
	if(map.data.WC_gwCount!=null){WC_gwCount = map.data.WC_gwCount;}
		$('#jpqCount').html(jpqCount);
		$('#JH_wlNum').html(JH_wlNum);
		var z_wl_num = "0.00";
		if(JH_wlNum!=0){
			if(!isNaN((WC_wlCount/JH_wlNum*100))){
				z_wl_num = (WC_wlCount/JH_wlNum*100).toFixed(2);
			}
		}
		$('#JH_wlPer').html("<div class='layui-progress-bar' lay-percent='"+z_wl_num+"%'></div>");
		$('#JH_gwNum').html(JH_gwNum);
		var z_gw_num = "0.00";
		if(JH_gwNum!=0){
			if(!isNaN((WC_gwCount/JH_gwNum*100))){
				z_gw_num = (WC_gwCount/JH_gwNum*100).toFixed(2);
			}
		}
		$('#JH_gwPer').html("<div class='layui-progress-bar'  lay-percent='"+z_gw_num+"%'></div>");
		
		$('#wlNum').html(wlNum);
		var wl_num = "0.00";
		if(wlNum!=0){
			if(!isNaN(((wlNum-wlRest)/wlNum*100))){
				wl_num = ((wlNum-wlRest)/wlNum*100).toFixed(2);
			}
		}
		$('#wlPer').html("<div class='layui-progress-bar' lay-percent='"+wl_num+"%'></div>");
		$('#gwNum').html(gwNum);
		var gw_num = "0.00";
		if(gwNum!=0){
			if(!isNaN(((gwNum-gwRest)/gwNum*100))){
				gw_num = ((gwNum-gwRest)/gwNum*100).toFixed(2);
			}
		}
		$('#gwPer').html("<div class='layui-progress-bar'  lay-percent='"+gw_num+"%'></div>");
		
		$('#carNum').html(carNum);
		var car_num = "0.00";
		if(carNum!=0){
			if(!isNaN(((carNum-carRest)/carNum*100))){
				car_num = ((carNum-carRest)/carNum*100).toFixed(2);
			}
		}
		$('#carPer').html("<div class='layui-progress-bar'  lay-percent='"+car_num+"%'></div>");
		
//		$('#orderNum').html(orderNum);
//		var order_num = 0;
//		if(!isNaN(((orderNum-orderRest)/orderNum*100))){
//			order_num = ((orderNum-orderRest)/orderNum*100).toFixed(2);
//		}
//		$('#orderPer').html("<div class='layui-progress-bar' lay-percent='"+order_num+"%'></div>");
		element.render('progress');
	carList=map.data.carMap;
	locHtml();	//清空页面
	for(var i=0;i<map.data.areaCarTab.length;i++){
		var area = map.data.areaCarTab[i];
		if(area!=null){
			areaTab = area.split("/");
			var areaCode = areaTab[0];
			var areaName = areaCode.split("-")[2];
			var tab=areaTab[2].split("|");
			if(tab[1]=="ZZ1"){
				$('#'+areaCode).css("background","url('/iles/pages/img/gzxc_y.png')no-repeat");
			}else if(tab[1]=="ZZ2"){
				$('#'+areaCode).css("background","url('/iles/pages/img/gzxc_z.png')no-repeat");
			}else{
				$('#'+areaCode).css("background","url('/iles/pages/img/gzxc.png')no-repeat");
			}
			$('#'+areaCode).css("background-size","cover");
			var tab3 ="";
			if(tab[3]!==undefined){tab3 = tab[3]}
			$('#'+areaCode).html("<div id='"+areaTab[1]+"' onclick=getCode('"+areaTab[1]+"')><span>"+areaName+"</span><br/>" +
					"<span>"+areaTab[1]+"</span><br/>" +
							"<span>"+tab3+"</span></div>");
		}
		
	}
	}
    },'json');
}

function getCode(carId){
	var xqNum = carList[carId].xqNum;
	var syNum = carList[carId].syNum;
	var jd = ((xqNum-syNum)/xqNum*100).toFixed(2);
	var myDate = new Date(carList[carId].time).getTime();
	var date = new Date().getTime()-myDate; 
    var days    = date / 1000 / 60 / 60 / 24;
    var daysRound= Math.floor(days);
    var hours    = date/ 1000 / 60 / 60 - (24 * daysRound);
    var hoursRound   = Math.floor(hours);
    var minutes   = date / 1000 /60 - (24 * 60 * daysRound) - (60 * hoursRound);
    var minutesRound  = Math.floor(minutes);
    var seconds   = date/ 1000 - (24 * 60 * 60 * daysRound) - (60 * 60 * hoursRound) - (60 * minutesRound);
    var secondsRound  = Math.floor(seconds);
    var time ="";
   
    if(!isNaN(daysRound)){
    	 time+= daysRound+"天";
    }
    if(!isNaN(hoursRound)){
    	 time+= hoursRound +"时";
    }
    if(!isNaN(minutesRound)){
    	time+= minutesRound+"分";
    }
    if(!isNaN(secondsRound)){
    	time+= secondsRound+"秒";
    }
    if(time==""){
    	time="0分";
    }
	layer.open({
		  type: 4,
		  content: ["<div style='width:180px;height: 100px; font-size:14px'>需集配笔数："+xqNum+" 笔<br/>当前进度："+jd+" %<br/>剩余："+syNum+" 笔<br/>已用时："+time+"</div>", "#"+carId] //数组第二项即吸附元素选择器或者DOM
	}); 
}


function locHtml(){
	$('#XBJK-JPQ-A001').html("A001");
	$('#XBJK-JPQ-A002').html("A002");
	$('#XBJK-JPQ-A003').html("A003");
    $('#XBJK-JPQ-A004').html("A004");
    $('#XBJK-JPQ-A005').html("A005");
    $('#XBJK-JPQ-A006').html("A006");
    $('#XBJK-JPQ-A007').html("A007");
 	$('#XBJK-JPQ-A008').html("A008");
    $('#XBJK-JPQ-A009').html("A009");
    $('#XBJK-JPQ-A010').html("A010");
 
    
    
 	
 	
 	$('XBJK-JPQ-A001').css("background","");
 	$('XBJK-JPQ-A002').css("background","");
	$('XBJK-JPQ-A003').css("background","");
    $('XBJK-JPQ-A004').css("background","");
    $('XBJK-JPQ-A005').css("background","");
    $('XBJK-JPQ-A006').css("background","");
    $('XBJK-JPQ-A007').css("background","");
 	$('XBJK-JPQ-A008').css("background","");
    $('XBJK-JPQ-A009').css("background","");
    $('XBJK-JPQ-A010').css("background","");
   
}



