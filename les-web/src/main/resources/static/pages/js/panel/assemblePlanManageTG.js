
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
 var areaUrl ="/iles/panel/queryAssembleArea?storageLocation=1001,A005,1500,1800";
 var bgImageAray = [
	 "TGZK-JPQ-A001","TGZK-JPQ-A002","TGZK-JPQ-A003","TGZK-JPQ-A004","TGZK-JPQ-A005",
	 "TGZK-JPQ-A006","TGZK-JPQ-A007","TGZK-JPQ-A008","TGZK-JPQ-A009","TGZK-JPQ-A010",
	 "TGZK-JPQ-A011","TGZK-JPQ-A012","TGZK-JPQ-A013","TGZK-JPQ-A014","TGZK-JPQ-A015",
	 "TGZK-JPQ-A016","TGZK-JPQ-A017","TGZK-JPQ-A018","TGZK-JPQ-A019","TGZK-JPQ-A020",
	 "TGZK-JPQ-A021", "TGZK-JPQ-A022","TGZK-JPQ-A023","TGZK-JPQ-A024","TGZK-JPQ-B001",
	 "TGZK-JPQ-B002","TGZK-JPQ-B003","TGZK-JPQ-B004","TGZK-JPQ-B005","TGZK-JPQ-B006",
	 "TGZK-JPQ-B007","TGZK-JPQ-B008","TGZK-JPQ-B009","TGZK-JPQ-B010","TGZK-JPQ-B011",
	 "TGZK-JPQ-B012","TGZK-JPQ-B013","TGZK-JPQ-B014","TGZK-JPQ-B015","TGZK-JPQ-B016",
	 "TGZK-JPQ-C001","TGZK-JPQ-C002","TGZK-JPQ-C003","TGZK-JPQ-C004","TGZK-JPQ-C005",
	 "TGZK-JPQ-C006","TGZK-JPQ-C007","TGZK-JPQ-C008","TGZK-JPQ-C009","TGZK-JPQ-C010",
	 "TGZK-JPQ-C011","TGZK-JPQ-C012","TGZK-JPQ-C013","TGZK-JPQ-C014","TGZK-JPQ-C015",
	 "TGZK-JPQ-C016","TGZK-JPQ-D001","TGZK-JPQ-D002","TGZK-JPQ-D003","TGZK-JPQ-D004",
	 "TGZK-JPQ-D005","TGZK-JPQ-D006","TGZK-JPQ-D007","TGZK-JPQ-D008","TGZK-JPQ-D009",
	 "TGZK-JPQ-D010","TGZK-JPQ-D011","TGZK-JPQ-D012","TGZK-JPQ-D013","TGZK-JPQ-D014",
	 "TGZK-JPQ-D015","TGZK-JPQ-D016","TGZK-JPQ-E001","TGZK-JPQ-E002","TGZK-JPQ-E003",
	 "TGZK-JPQ-E004","TGZK-JPQ-E005","TGZK-JPQ-E006","TGZK-JPQ-E007","TGZK-JPQ-E008",
	 "TGZK-JPQ-E009","TGZK-JPQ-E010","TGZK-JPQ-E011","TGZK-JPQ-E012","TGZK-JPQ-E013",
	 "TGZK-JPQ-E014","TGZK-JPQ-E015","TGZK-JPQ-E016","TGZK-JPQ-E017","TGZK-JPQ-E018",
	 "TGZK-JPQ-E019","TGZK-JPQ-E020","TGZK-JPQ-E021","TGZK-JPQ-E022","TGZK-JPQ-E023",
	 "TGZK-JPQ-E024","TGZK-JPQ-E025","TGZK-JPQ-E026","TGZK-JPQ-E027","TGZK-JPQ-E028",
	 "TGZK-JPQ-E029","TGZK-JPQ-E030","TGZK-JPQ-E031","TGZK-JPQ-E032"];
 $.post(areaUrl,function(map){
	if(map.code==0){
	var gwzbs_zs = 0,wlNum=0,wlRest=0,jpqCount=0;
	var gwzbs_wwc =0,JH_wlPer = '0.00%',WC_wlCount=0,gwzbs_ywc =0,wlPer = '0.00%',fgwzbs_ywc = 0,fgwzbs_zs=0,fgwzbs_wwc=0,bs_zs=0,bs_ywc=0,bs_wwc=0,tjbs_zs=0,tjbs_ywc=0;
	var carNum = 0,carPer = '0.00%',carRest = 0;
	var JH_gwNum = 0,JH_gwPer = '0.00%',WC_gwCount=0,WC_gwCount=0,gwNum = 0,gwPer = '0.00%',gwRest = 0;
//	var orderNum=0,orderPer='0%',orderRest=0;
	if(map.data.jpqNum!=null){jpqCount = map.data.jpqNum;}
	if(map.data.gwzbs_zs!=null){gwzbs_zs = map.data.gwzbs_zs;}
	if(map.data.gwzbs_ywc!=null){gwzbs_ywc = map.data.gwzbs_ywc;}
	if(map.data.gwzbs_wwc!=null){gwzbs_wwc = map.data.gwzbs_wwc;}
	if(map.data.fgwzbs_zs!=null){fgwzbs_zs = map.data.fgwzbs_zs;}
	if(map.data.fgwzbs_ywc!=null){fgwzbs_ywc = map.data.fgwzbs_ywc;}
	if(map.data.fgwzbs_wwc!=null){fgwzbs_wwc = map.data.fgwzbs_wwc;}
	if(map.data.bs_zs!=null){bs_zs = map.data.bs_zs;}
	if(map.data.bs_ywc!=null){bs_ywc = map.data.bs_ywc;}
	if(map.data.bs_wwc!=null){bs_wwc = map.data.bs_wwc;}
	if(map.data.gwRest!=null){gwRest = map.data.gwRest;}
	if(map.data.tjbs_zs!=null){tjbs_zs = map.data.tjbs_zs;}
	if(map.data.tjbs_ywc!=null){tjbs_ywc = map.data.tjbs_ywc;}
	if(map.data.wlNum!=null){wlNum=map.data.wlNum;}
	if(map.data.carNum!=null){carNum=map.data.carNum;}
	if(map.data.carRest!=null){carRest=map.data.carRest;}
	if(map.data.wlRest!=null){wlRest=map.data.wlRest;}
	if(map.data.gwNum!=null){gwNum=map.data.gwNum;}
	var WC_wlCount = 0;
	var WC_gwCount = 0,excptionCarSet=0;
	if(map.data.WC_wlCount!=null){WC_wlCount = map.data.WC_wlCount;}
	if(map.data.WC_gwCount!=null){WC_gwCount = map.data.WC_gwCount;}
	if(map.data.excptionCarSet!=null){excptionCarSet = map.data.excptionCarSet;}
	$("#exceptionCount").text(excptionCarSet);
	$('#jpqCount').text(jpqCount);
	$("#requiredDateI").text(map.data.requiredDate);
	$("#requiredDateH").text(map.data.toDate);
    //工位笔数、百分比
	$("#gwzbs_zs").text(gwzbs_zs);
	$("#gwzbs_ywc").text(gwzbs_ywc);
	$("#gwzbs_wwc").text(gwzbs_wwc);
	var gwbjp_i="0.0";
	if(gwzbs_ywc!=0){
		if(!isNaN((gwzbs_ywc/gwzbs_zs*100))){
			gwbjp_i = (gwzbs_ywc/gwzbs_zs*100).toFixed(2);
			
		}
	}
	$('#gwbjp_i').html("<div class='layui-progress-bar' lay-percent='"+gwbjp_i+"%'></div>");
	
	//非工位笔数、百分比
	$("#fgwzbs_zs").text(fgwzbs_zs);
	$("#fgwzbs_ywc").text(fgwzbs_ywc);
	$("#fgwzbs_wwc").text(fgwzbs_wwc);
	var fgwbjp_i="0.0";
	if(fgwzbs_ywc!=0){
		if(!isNaN((fgwzbs_ywc/fgwzbs_zs*100))){
			fgwbjp_i = (fgwzbs_ywc/fgwzbs_zs*100).toFixed(2);
			
		}
	}
	$('#fgwbjp_i').html("<div class='layui-progress-bar' lay-percent='"+fgwbjp_i+"%'></div>");
	
	//需求日期是今天及以前笔数、百分比
	$("#bs_zs").text(bs_zs);
	$("#bs_ywc").text(bs_ywc);
	$("#bs_wwc").text(bs_wwc);
	var gwbjp_h = "0.0";
	if(bs_zs!=0){
		if(!isNaN((bs_ywc/bs_zs*100))){
			gwbjp_h = (bs_ywc/bs_zs*100).toFixed(2);
		}
	}
	$('#gwbjp_h').html("<div class='layui-progress-bar' lay-percent='"+gwbjp_h+"%'></div>");
	
	//提前集配
	$("#tjbs_zs").text(tjbs_zs);
	$("#tjbs_ywc").text(tjbs_ywc);
	var tjbs_wwc="0.0";
	if(tjbs_zs!=0){
		if(!isNaN((tjbs_ywc/tjbs_zs*100))){
			tjbs_wwc = (tjbs_ywc/tjbs_zs*100).toFixed(2);
			
		}
	}
	$("#tjbs_wwc").text(tjbs_wwc+"%");
	//当前作业笔数
	$("#dqbs_zs").text(wlNum);
	$("#dqbs_ywc").text(wlNum-wlRest);
	$("#dqbs_wwc").text(wlRest);
	var z_wl_num = "0.00";
	if(wlNum!=0){
		if(!isNaN(((wlNum-wlRest)/wlNum*100))){
			z_wl_num = ((wlNum-wlRest)/wlNum*100).toFixed(2);
		}
	}
	$("#wlbjp_n").html("<div class='layui-progress-bar' lay-percent='"+z_wl_num+"%'></div>");
	//载具数
	$("#zj_zs").text(carNum);
	$("#zj_ywc").text(carNum-carRest);
	$("#zj_wwc").text(carRest);
	var car_num = "0.00";
	if(carNum!=0){
		if(!isNaN(((carNum-carRest)/carNum*100))){
			car_num = ((carNum-carRest)/carNum*100).toFixed(2);
		}
	}
	$("#zjjp_n").html("<div class='layui-progress-bar' lay-percent='"+car_num+"%'></div>");
	//工位数
	$("#gw_zs").text(gwNum);
	$("#gw_ywc").text(gwNum-gwRest);
	$("#gw_wwc").text(gwRest);
	var gw_num = "0.00";
	if(gwNum!=0){
		if(!isNaN(((gwNum-gwRest)/gwNum*100))){
			gw_num = ((gwNum-gwRest)/gwNum*100).toFixed(2);
		}
	}
	$("#gwbjp_n").html("<div class='layui-progress-bar' lay-percent='"+gw_num+"%'></div>");
	
	element.render('progress');
	carList=map.data.carMap;
	locHtml();	//清空页面
	var margTop=0;
	for(var i=0;i<map.data.areaCarTab.length;i++){
		var area = map.data.areaCarTab[i];
		if(area!=null){
			areaTab = area.split("/");
			var areaCode = areaTab[0];
			var areaName = areaCode.split("-")[2];
			var tab=areaTab[2].split("|");
			var tab3 ="";
			if(tab[3]!==undefined){tab3 = tab[3]}
			if(tab[1]=="ZZ"){
				if($.inArray(areaCode,bgImageAray)<0){
					$('#'+areaCode).css("background","url('/iles/pages/img/gzxc_y1.png')no-repeat");
					$('#'+areaCode).html("<ul id='"+areaTab[1]+"' onclick=getCode('"+areaTab[1]+"')><li style='margin-top: -22px;'>"+areaName+"</li>" +
							"<li>"+areaTab[1]+"</li>" +
									"<li>"+tab3+"</li></ul>");
				}else{
					$('#'+areaCode).css("background","url('/iles/pages/img/gzxc_y.png')no-repeat");
					$('#'+areaCode).html("<ul id='"+areaTab[1]+"' onclick=getCode('"+areaTab[1]+"')><li style='margin-top: -3px;'>"+areaName+"</li>" +
							"<li>"+areaTab[1]+"</li>" +
									"<li>"+tab3+"</li></ul>");
				}
			}else if(tab[1]=="ZZ2"){
				if($.inArray(areaCode,bgImageAray)<0){
					$('#'+areaCode).css("background","url('/iles/pages/img/gzxc_z1.png')no-repeat");
					$('#'+areaCode).html("<ul id='"+areaTab[1]+"' onclick=getCode('"+areaTab[1]+"')><li style='margin-top: -22px;'>"+areaName+"</li>" +
							"<li>"+areaTab[1]+"</li>" +
									"<li>"+tab3+"</li></ul>");
				}else{
					$('#'+areaCode).css("background","url('/iles/pages/img/gzxc_z.png')no-repeat");
					$('#'+areaCode).html("<ul id='"+areaTab[1]+"' onclick=getCode('"+areaTab[1]+"')><li style='margin-top: -3px;'>"+areaName+"</li>" +
							"<li>"+areaTab[1]+"</li>" +
									"<li>"+tab3+"</li></ul>");
				}
				
			}else{
				if($.inArray(areaCode,bgImageAray)<0){
					$('#'+areaCode).css("background","url('/iles/pages/img/gzxc_f1.png')no-repeat");
					$('#'+areaCode).html("<ul id='"+areaTab[1]+"' onclick=getCode('"+areaTab[1]+"')><li style='margin-top: -38px;'>"+areaName+"</li>" +
							"<li>"+areaTab[1]+"</li>" +
									"<li>"+tab3+"</li></ul>");
				}else{
					$('#'+areaCode).css("background","url('/iles/pages/img/gzxc_f.png')no-repeat");
					$('#'+areaCode).html("<ul id='"+areaTab[1]+"' onclick=getCode('"+areaTab[1]+"')><li style='margin-top: -3px;'>"+areaName+"</li>" +
							"<li>"+areaTab[1]+"</li>" +
									"<li>"+tab3+"</li></ul>");
				}
				
			}
			$('#'+areaCode).css("background-size","cover");
			
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
	var areaArray = ["A","B","C","D","E"];
	var l=17;
	for(var i=0;i<areaArray.length;i++){
		
		var obj = areaArray[i];
		if(obj=="A"){
		 l=25;	
		}else if(obj=="B" || obj=="C" || obj=="D"){
			 
		}else {
			l=33;
		 
		}
		
		for(var j=1;j<l;j++){
			if(j<11){
				$("#TGZK-JPQ-"+obj+"00"+j).html(obj+"00"+j);
				$("#TGZK-JPQ-"+obj+"00"+j).css("background","");
			}else{
				$("#TGZK-JPQ-"+obj+"0"+j).html(obj+"0"+j);
				$("#TGZK-JPQ-"+obj+"0"+j).css("background","");
			}
		}
		
	}
  
}


function addJpqHtml(){
	
	var html ='';
	for(var i=13;i<25;i++){
		html ='<td class="td" id="TGZK-JPQ-A0'+i+'"  >A0'+i+'</td>';
	}
	$("#jpq2").html(html);
	
}