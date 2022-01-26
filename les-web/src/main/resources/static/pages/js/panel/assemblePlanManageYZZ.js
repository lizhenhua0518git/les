
var layer = layui.layer;
var laytpl = layui.laytpl;
var element = layui.element;
layui.use('element', function(){
	
	
});

$(function(){
	queryInfo(); 
 setInterval(queryInfo,80000);
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
function queryInfo(){
  var url ="/iles/panel/queryYzZZAssemblePlane?storageLocation=1601&stationCode=ZB,GW01,GW02,GW03,GW04,GW05,GW06,GW07,GW08,11120202,11120103,11120203,11120405,11120303,11110108,11120102,11110115,11110103,11120101,11120404,11110101,01100000";
 //var stationCodeArray=["ZB","GW01","GW02","GW03","GW04","GW05","GW06","GW07","GW08"];
	  

	
	var gwArray=["11120202","11120103","11120203","11120405","11120303","11110108","11120102","11110115","11110103","11120101","11120404","11110101","01100000"];
 $.post(url,function(map){
	if(map.code==0){
	var data = map.data;
	var gwTotalRequireNumList=data.gwTotalRequireNumList;
	var gwTotalRequireNum = data.gwTotalRequireNum;
	var gwSendRequireNumList = data.gwSendRequireNumList;
	var gwFinshNum = data.gwFinshNum;
	var gwHandoverNum = data.gwHandoverNum;
	var gwSendNumList = data.gwSendNumList;
	var gwFinshMap = data.gwFinshMap;
	var notGwTotalRequireNum =data.notGwTotalRequireNum;
	var notGwFinshNum = data.notGwFinshNum;
	var notGwHandoverNum = data.notGwHandoverNum;
	
	$("#requiredDateI").text(map.data.requiredDate);
	
	if(Number(gwTotalRequireNum)-Number(gwFinshNum)<0){
    gwFinshNum =gwTotalRequireNum;
	}
	var gw_finish ="0.0";
 
	if(gwTotalRequireNum!=0){
	if(!isNaN((gwFinshNum/gwTotalRequireNum*100))){
		gw_finish = (gwFinshNum/gwTotalRequireNum*100).toFixed(2);
		
	}
	}
	$('#gw_finish').html("<div class='layui-progress-bar' lay-percent='"+gw_finish+"%'></div>");
	 
	$("#gw_finish_total").text(gwTotalRequireNum);
	$("#gw_finish_finish").text(gwFinshNum);
	$("#gw_finish_unfinish").text(Number(gwTotalRequireNum)-Number(gwFinshNum));
	
	
	if(Number(gwTotalRequireNum)-Number(gwHandoverNum)<0){
		gwHandoverNum =gwTotalRequireNum;
	}
	var  gw_handover ="0.0";
	if(gwTotalRequireNum!=0){
	if(!isNaN((gwHandoverNum/gwTotalRequireNum*100))){
		gw_handover = (gwHandoverNum/gwTotalRequireNum*100).toFixed(2);
		
	}
	}
	$('#gw_handover').html("<div class='layui-progress-bar' lay-percent='"+gw_handover+"%'></div>");
	 
   $("#gw_handover_total").text(gwTotalRequireNum);
   $("#gw_handover_handover").text(gwHandoverNum);
   $("#gw_handover_unhandover").text(Number(gwTotalRequireNum)-Number(gwHandoverNum));
   
   
   if(Number(notGwTotalRequireNum)-Number(notGwFinshNum)<0){
	   notGwFinshNum =notGwTotalRequireNum;
	}
   var  notgw_finish ="0.0";
	if(notGwTotalRequireNum!=0){
	if(!isNaN((notGwFinshNum/notGwTotalRequireNum*100))){
		notgw_finish = (notGwFinshNum/notGwTotalRequireNum*100).toFixed(2);
	
	}
	}
	$('#notgw_finish').html("<div class='layui-progress-bar' lay-percent='"+notgw_finish+"%'></div>");
	 
	  $("#notgw_finish_total").text(notGwTotalRequireNum);
	  $("#notgw_finish_finish").text(notGwFinshNum);
	  $("#notgw_finish_unfinish").text(Number(notGwTotalRequireNum)-Number(notGwFinshNum));
  
	  if(Number(notGwTotalRequireNum)-Number(notGwHandoverNum)<0){
		  notGwHandoverNum =notGwTotalRequireNum;
		}
	   var  notgw_handover ="0.0";
		if(notGwTotalRequireNum!=0){
		 if(!isNaN((notGwHandoverNum/notGwTotalRequireNum*100))){
			notgw_handover = (notGwHandoverNum/notGwTotalRequireNum*100).toFixed(2);
			
		 }
		}
	 
	 $('#notgw_handover').html("<div class='layui-progress-bar' lay-percent='"+notgw_handover+"%'></div>");
	
	 
	 $("#notgw_handover_total").text(notGwTotalRequireNum);
	 $("#notgw_handover_handover").text(notGwHandoverNum);
	 $("#notgw_handover_unhandover").text(Number(notGwTotalRequireNum)-Number(notGwHandoverNum));
	 
	 var left_gwHtml ='';
	 var right_gwHtml ='';
	 for(var i=0;i<gwTotalRequireNumList.length;i++){
		 var map = gwTotalRequireNumList[i];
		 var stationCode =map.stationCode;
		 if(isNaN(stationCode)){
			 right_gwHtml = right_gwHtml+'<tr class="left_tr">  '
			   	+'<td class="left_td right-tb" style="border-top: 1px solid;" >'+stationCode+':</td> '
			   	+'<td class="right-tb">'
				+'<div  style="background-color:lightblue" class="layui-progress  layui-progress-big" lay-showpercent="true" id="'+stationCode+'_sap" lay-filter="gwbjp_iFilter"> '
				+'<div  class="layui-progress-bar" lay-percent="0%"></div>  '
				+'</div>'
				+'<div><span style="float:left" id="'+stationCode+'_sap_total">0000</span><span id="'+stationCode+'_sap_finish">0000</span><span id="'+stationCode+'_sap_unfinish" style="float:right">0000</span></div>'
			  	+'<div  style="background-color:lightblue" class="layui-progress  layui-progress-big" lay-showpercent="true" id="'+stationCode+'" lay-filter="gwbjp_iFilter"> '
				+'<div  class="layui-progress-bar" lay-percent="0%"></div>'
				+'</div> '
				+'<div><span style="float:left" id="'+stationCode+'_total">0000</span><span id="'+stationCode+'_finish">0000</span><span id="'+stationCode+'_unfinish" style="float:right">0000</span></div>'
			  	+'</td>'
			    +'</tr>';
		   }else{
			   left_gwHtml = left_gwHtml+'<tr class="left_tr left_rd">  '
			   	+'<td class="left_td right-tb1 " style="border-top: 1px solid; border-bottom:1px solid ;" >'+stationCode+':</td> '
			   	+'<td class="right-tb2">'
				+'<div  style="background-color:lightblue"  class="layui-progress  layui-progress-big" lay-showpercent="true" id="'+stationCode+'_sap" lay-filter="gwbjp_iFilter"> '
				+'<div  class="layui-progress-bar" lay-percent="0%"></div>  '
				+'</div>'
				+'<div><span style="float:left" id="'+stationCode+'_sap_total">0000</span><span id="'+stationCode+'_sap_finish">0000</span><span id="'+stationCode+'_sap_unfinish" style="float:right">0000</span></div>'
			  	+'<div  style="background-color:lightblue" class="layui-progress  layui-progress-big" lay-showpercent="true" id="'+stationCode+'" lay-filter="gwbjp_iFilter"> '
				+'<div  class="layui-progress-bar" lay-percent="0%"></div>'
				+'</div> '
				+'<div><span style="float:left" id="'+stationCode+'_total">0000</span><span id="'+stationCode+'_finish">0000</span><span id="'+stationCode+'_unfinish" style="float:right">0000</span></div>'
			  	+'</td>'
			    +'</tr>';
			   
		   }
		 
		 
		 
	 }
  
	 $(".left_rd").remove();
     $("#gw1").after(left_gwHtml);
     $("#gw2").html(right_gwHtml);
     element.render('progress');
	 for(var i =0;i<gwTotalRequireNumList.length;i++){
		 var map = gwTotalRequireNumList[i];
		 var stationCode =map.stationCode;
		 var num =map.num;
		 $("#"+stationCode+"_sap_total").text(num);
		 
		 var finishNum= 0;
		 if(gwFinshMap[stationCode] !=null){
			   finishNum= gwFinshMap[stationCode];
		 }
		 
		
		 /**
		 if(stationCode=="ZB"){
			 finishNum= gwFinshMap.ZB;
		 }else if(stationCode=='GW01'){
			 finishNum= gwFinshMap.GW01;
		 }else if(stationCode=='GW02'){
			 finishNum= gwFinshMap.GW02;
		 }else if(stationCode=='GW03'){
			 finishNum= gwFinshMap.GW03;
		 }else if(stationCode=='GW04'){
			 finishNum= gwFinshMap.GW04;
		 }else if(stationCode=='GW05'){
			 finishNum= gwFinshMap.GW05;
		 }else if(stationCode=='GW06'){
			 finishNum= gwFinshMap.GW06;
		 }else if(stationCode=='GW07'){
			 finishNum= gwFinshMap.GW07;
		 }else if(stationCode=='GW08'){
			 finishNum= gwFinshMap.GW08;
		 } **/
		 
		 if(Number(num)-Number(finishNum)<0){
			 finishNum =num;
		 }
 $("#"+stationCode+"_sap_finish").text(finishNum);
 $("#"+stationCode+"_finish").text(finishNum);
 $("#"+stationCode+"_sap_unfinish").text(Number(num)-Number(finishNum));
 
   var  value ="0.0";
	if(finishNum!=0){
	if(!isNaN((finishNum/num*100))){
		value = (finishNum/num*100).toFixed(2);
	}
	}
	$("#"+stationCode+"_sap").html("<div class='layui-progress-bar' lay-percent='"+value+"%'></div>");
	 }
	 
	 for(var i =0;i<gwSendRequireNumList.length;i++){
		 var map = gwSendRequireNumList[i];
		 var stationCode =map.stationCode;
		 var num =map.num;
		 $("#"+stationCode+"_total").text(num);
		 var finishNum= gwFinshMap[stationCode];
 /**
 if(stationCode=="ZB"){
	 finishNum= gwFinshMap.ZB;
 }else if(stationCode=='GW01'){
	 finishNum= gwFinshMap.GW01;
 }else if(stationCode=='GW02'){
	 finishNum= gwFinshMap.GW02;
 }else if(stationCode=='GW03'){
	 finishNum= gwFinshMap.GW03;
 }else if(stationCode=='GW04'){
	 finishNum= gwFinshMap.GW04;
 }else if(stationCode=='GW05'){
	 finishNum= gwFinshMap.GW05;
 }else if(stationCode=='GW06'){
	 finishNum= gwFinshMap.GW06;
 }else if(stationCode=='GW07'){
	 finishNum= gwFinshMap.GW07;
 }else if(stationCode=='GW08'){
	 finishNum= gwFinshMap.GW08;
 } **/
 
 
 var finishNum = $("#"+stationCode+"_finish").text();
 $("#"+stationCode+"_unfinish").text(Number(num)-Number(finishNum));
 
 if(Number(num)-Number(finishNum)<0){
	 finishNum =num;
 }
 var  value ="0.0";
	if(finishNum!=0){
if(!isNaN((finishNum/num*100))){
	value = (finishNum/num*100).toFixed(2);
}
	}
	 $("#"+stationCode).html("<div class='layui-progress-bar' lay-percent='"+value+"%'></div>");
	 }
	 
	 
	 element.render('progress');
	 
	}	 
    },'json');
 	var option = {
 	url:'/iles/panel/queryCarrierUseNum?warehouseCode=00500400',
 	type:'GET',
 	success:function(res){
 if(res.code==0){
	var data = res.data;
	if(data.length>0){
var getTpl = carrierScirpt.innerHTML
,view = document.getElementById('carrierUseTd');
laytpl(getTpl).render(data, function(html){
	  view.innerHTML = html;
});
	}
 }
 	}
 	};
 	$.ajax(option);
 
}