function dateFtt(fmt,date)   
{ 
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
};

function searchNeatRate(){
	var DCXParams=initParams();
	DCXParams.type='DCX';
	var TGXParams=initParams();
	TGXParams.type='TGX';
	document.getElementById("dcLine").innerHTML="";
	document.getElementById("tgLine").innerHTML="";
	initStationNeatRate(DCXParams,'dcLine');
	initStationNeatRate(TGXParams,'tgLine');
}

function initParams(){
	var param = {};
	var requiredDate = $("#requiredDate").val();
	var beginDate;
	var endDate;
	if(requiredDate==""||requiredDate==null){
		var curDate = new Date();
		var curDate2=new Date(curDate.getTime()-24*60*60*1000);
		var curDateStr = dateFtt("yyyy-MM-dd",curDate2)
		requiredDate=curDateStr+" - "+curDateStr;
	}
	beginDate = requiredDate.substring(0,10);
	endDate = requiredDate.substring(13);
	param.beginDate=beginDate;
	param.endDate = endDate;
	return param;
};

function initStationNeatRate(params,productLineId){
	$.ajax({
		url:contextPath+"productionPlan/board/stationNeatRate",
		data:{
			beginDate:params.beginDate,
			endDate:params.endDate,
			type:params.type
		},
		success:function(res){
			initProductLine(res.data,productLineId);
		}
	});
};
function initProductLine(data,productLineId){
	for(var i=0;i<data.length;i++){
		var str='<li><div class="item" id='+productLineId+i+'></div></li>';
		$("#"+productLineId).append(str);
		var progressBarNames=new Array();
		var progressBarValues=new Array();
		var list=data[i].list;
		for(var j=0;j<list.length;j++){
			progressBarNames.push(list[j].warehouse);
			progressBarNames.push(list[j].warehouse);
			progressBarValues.push({value:list[j].neatRate,itemStyle:{color:'#2ec9cf'}});
			progressBarValues.push({value:list[j].neatRateXD,itemStyle:{color:'#4b9bec'}});
		}
		renderProgressBar({id:productLineId+i, itemTitle:data[i].projectName, progressBarNames:progressBarNames,progressBarValues:progressBarValues});
	}
};
function renderProgressBar(obj){
	var bar = document.getElementById(obj.id);
	var option = {
		title:{
			text:obj.itemTitle,
			left:'center',
			top:'10%',
			textStyle:{
				color:'#c0dcfe',
				fontSize:24
			}
		},
		color: ['#2cc0f7'],
		legend: {
			top:'middle',
			right:"2%",
			itemWidth:15,
			itemHeight:15,
			itemGap:20,
			textStyle:{
				color:"#89bef3",
				fontSize:14
			},
			orient:'vertical',
			data: ['G1:75%', 'G2:85%','G3:100%']
		},
		grid: {
			top:'22%',
			bottom:'16%',
			left:'18%',
			right:'25%'
		},
		yAxis: {
			type: 'category',
			axisLine:{show:false},
			axisTick: {show: false},
			axisLabel: {color:"#89bef3",fontSize:16},
			data: obj.progressBarNames
		},
		xAxis: {
			type: 'value',
			axisLine:{show:false},
			axisTick:{length:5},
			axisLabel: {color:"#89bef3",fontSize:16,formatter: '{value} %'},
			splitLine:{
				show:true,
				lineStyle:{
					color:"#295288",
					type:"dashed"
				}
			},
			splitNumber:1,
			interval:20,
			max:100
		},
		tooltip:{
			show:true,
			trigger:'axis',
			axisPointer:{
				type:'none'
			},
			formatter:'{b} <br> {a}:{c}%'
		},
		series: [
			{
				name:'已完成',
				type: 'bar',
				barWidth:30,
				itemStyle:{
					barBorderRadius:[0,5,5,0],
				},
				label: {
					formatter: '{c} %',
					show: true,
					position: 'right',
					color:'#fff',
					fontSize:16,
				},
				data: obj.progressBarValues
			},
			{
				name:'G1:75%',
				type:'bar',
				barWidth:30,
				barGap: "-100%"
			},
			{
				name:'G2:85%',
				type:'bar',
				barWidth:30,
				barGap: "-100%"
			},
			{
				name:'G3:100%',
				type:'bar',
				barWidth:30,
				barGap: "-100%"
			}
		]
	};
	
	var  n = echarts.init(bar);
	n.setOption(option);
	checkHeight(bar,n);
};