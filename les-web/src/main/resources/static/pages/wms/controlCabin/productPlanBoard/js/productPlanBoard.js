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

function searchByDate(){
	var params=initParams();
	$("#planCountAll").text("");
	$("#noPlanCountAll").text("");
	initFirstModuleData(params);
	initDoubleBarData(params);
	initSixPieData(params);
	initBarSingleData(params,'echartBar1','动车组装');
	initBarSingleData(params,'echartBar2','动车车体');
	initBarSingleData(params,'echartBar3','动车检修');
	initBarSingleData(params,'echartBar4','碳钢组装');
	initBarSingleData(params,'echartBar5','碳钢车体');
	initBarSingleData(params,'echartBar6','转向架');
};
function resetDate(){
	$("#requiredDate").val("");
};
function initParams(){
	var param = {};
	var requiredDate = $("#requiredDate").val();
	var beginDate;
	var endDate;
	if(requiredDate==""||requiredDate==null){
		var curDate = new Date();
		var curDate2=new Date(curDate.getTime()+24*60*60*1000);
		var curDateStr = dateFtt("yyyy-MM-dd",curDate2)
		requiredDate=curDateStr+" - "+curDateStr;
	}
	beginDate = requiredDate.substring(0,10)+' 00:00:00';
	endDate = requiredDate.substring(13)+' 23:59:59';
	param.beginDate=beginDate;
	param.endDate = endDate;
	return param;
};
//获取头部总数
function initFirstModuleData(params){
	var resData={};
		$.ajax({
			url:contextPath+"productionPlan/board/firstModuleNew",
			data:{
				beginDate:params.beginDate,
				endDate:params.endDate	
			},
			success:function(res){
				if(res.code==0){
					resData.totalCount=res.data.totalCount;
					resData.planCount=res.data.planCount;
					resData.noPlanCount=res.data.noPlanCount;
					resData.overRate=res.data.overRate;
				}else{
					resData.totalCount=0;
					resData.planCount=0;
					resData.noPlanCount=0;
					resData.overRate=0;
				}
				initFirstModule(resData);
			}
		});
};
function initFirstModule(resData){
	var clock = new FlipClock($('.clock'), resData.totalCount, {clockFace: 'Counter'});
	//配送完成进度
	$('#circleProgress').attr("data-percent",resData.overRate);
	$('#circleProgress .percent').text(resData.overRate+"%");
	$('#circleProgress').easyPieChart({
		trackColor: '#457db5',
		scaleColor: false,
		lineWidth: 12,
		lineCap:'square',
		rotate:0,
		size:100,		
		barColor: '#40edfe'
	});
	$('#circleProgress').data('easyPieChart').update(resData.overRate);
	$("#planCountAll").append(resData.planCount);
	$("#noPlanCountAll").append(resData.noPlanCount);
	var $this = $('._panel .title');
	if($this.width() > (document.documentElement.clientWidth/19.2 || 100)*3.7){
		$this.css({"margin-left": "0.2rem"});
		$this.siblings('.circleProgress').css({"margin-right": "0.1rem"});
	}
};
//双阴影柱状图	
function initDoubleBarData(params){
	//params.productLineNames=["动车组装", "动车车体", "动车检修", "碳钢组装", "碳钢车体", "转向架"];
	var arr=["动车组装", "动车车体", "动车检修", "碳钢组装", "碳钢车体", "转向架"];
	var str=arr.join(',');
		$.ajax({
			url:contextPath+"productionPlan/board/totalCountByProductLinesNew",
			data:{
				beginDate:params.beginDate,
				endDate:params.endDate,
				productLineNames:str	
			},
			success:function(res){
				initDoubleBar("DoubleBar",res.data);
			}
		});
	
};
//初始六个饼状图数据
function initSixPieData(params){
	var arr=["动车组装", "动车车体", "动车检修", "碳钢组装", "碳钢车体", "转向架"];
	var str=arr.join(',');
	$.ajax({
			url:contextPath+"productionPlan/board/totalOverCountByProductLinesNew",
			data:{
				beginDate:params.beginDate,
				endDate:params.endDate,
				productLineNames:str	
			},
			success:function(res){
				initSixPie('echartPie',res.data);
			}
	});
};
//产线工位分布柱状图（param，柱状图id,产线名称）
function initBarSingleData(params,echarId,productLineName){
	$.ajax({
			url:contextPath+"productionPlan/board/findStationPlanCount",
			data:{
				beginDate:params.beginDate,
				endDate:params.endDate,
				productLineName:productLineName	
			},
			success:function(res){
				initBarSingle(echarId,res.data);
			}
	});	
};

	//renderSixPie('echartPie');
	function initSixPie(id,resData){
		var pie = document.getElementById(id);
		var option = {
			color: ['#2cc0f7', '#ffbf44'],
			legend: {
				top:"2%",
				itemHeight:25,
				itemGap:30,
				textStyle:{
					color:"#89bef3",
					fontSize:16
				},
				data: ['已完成', '未完成']
			},
			tooltip:{
				show:true,
				formatter:'{a} <br/>{b} : {c} ({d}%)',
			},
			series:[
				{
					name:resData[0].productLineName,
					type:"pie",
					center: ['20%', '35%'],
					radius:[0,"25%"],
					selectedMode: 'single',
					selectedOffset:5,
					hoverAnimation:true,
					hoverOffset:5,
					label:{
						show:false
					},
					data:[
						{value:resData[0].totalOverCount, name:'已完成', selected:false},
						{value:resData[0].noOverCount, name:'未完成', selected:false}
					]
				},
				{
					name:resData[1].productLineName,
					type:"pie",
					center: ['50%', '35%'],
					radius:[0,"25%"],
					selectedMode: 'single',
					selectedOffset:5,
					hoverAnimation:true,
					hoverOffset:5,
					label:{
						show:false
					},
					data:[
						{value:resData[1].totalOverCount, name:'已完成', selected:false},
						{value:resData[1].noOverCount, name:'未完成', selected:false}
					]
				},
				{
					name:resData[2].productLineName,
					type:"pie",
					center: ['80%', '35%'],
					radius:[0,"25%"],
					selectedMode: 'single',
					selectedOffset:5,
					hoverAnimation:true,
					hoverOffset:5,
					label:{
						show:false
					},
					data:[
						{value:resData[2].totalOverCount, name:'已完成', selected:false},
						{value:resData[2].noOverCount, name:'未完成', selected:false}
					]
				},
				{
					name:resData[3].productLineName,
					type:"pie",
					center: ['20%', '80%'],
					radius:[0,"25%"],
					selectedMode: 'single',
					selectedOffset:5,
					hoverAnimation:true,
					hoverOffset:5,
					label:{
						show:false
					},
					data:[
						{value:resData[3].totalOverCount, name:'已完成', selected:false},
						{value:resData[3].noOverCount, name:'未完成', selected:false}
					]
				},
				{
					name:resData[4].productLineName,
					type:"pie",
					center: ['50%', '80%'],
					radius:[0,"25%"],
					selectedMode: 'single',
					selectedOffset:5,
					hoverAnimation:true,
					hoverOffset:5,
					label:{
						show:false
					},
					data:[
						{value:resData[4].totalOverCount, name:'已完成', selected:false},
						{value:resData[4].noOverCount, name:'未完成', selected:false}
					]
				},
				{
					name:resData[5].productLineName,
					type:"pie",
					center: ['80%', '80%'],
					radius:[0,"25%"],
					selectedMode: 'single',
					selectedOffset:5,
					hoverAnimation:true,
					hoverOffset:5,
					label:{
						show:false
					},
					data:[
						{value:resData[5].totalOverCount, name:'已完成', selected:false},
						{value:resData[5].noOverCount, name:'未完成', selected:false}
					]
				}
			]
		};
		echarts.init(pie).setOption(option);
	}
	
	//renderDoubleBar('DoubleBar');
	function initDoubleBar(id,resData){
		var bar = document.getElementById(id);
		var option = {
			color: [ '#ee8a62','#2cc0f7'],
			legend: {
				top:"6%",
				itemHeight:25,
				itemGap:30,
				textStyle:{
					color:"#89bef3",
					fontSize:16
				},
				data: ['工位制', '非工位制']
			},
			grid: {
				top:'25%',
				bottom:'15%'
			},
			xAxis: {
				type: 'category',
				axisLine:{show:false},
				axisTick: {show: false},
				axisLabel: {color:"#89bef3",fontSize:16},
				data: [resData[0].productLineName,resData[1].productLineName ,resData[2].productLineName ,resData[3].productLineName ,resData[4].productLineName ,resData[5].productLineName ]
			},
			yAxis: {
				type: 'value',
				axisLine:{show:false},
				axisLabel: {color:"#89bef3",fontSize:16},
				splitLine:{
					lineStyle:{
						color:"#295288",
					}
				}
			},
			dataZoom:[
				{
					type:'inside',
				}
			],
			tooltip:{
				show:true,
			},
			series: [
				{
					type: 'bar',
					barWidth:30,
					barGap: "-100%",
					stack: 'one',
					itemStyle:{
						barBorderRadius:[5,5,0,0],
						opacity:1,
					},
					label: {
						normal: {
							show: true,
							position: 'top',
							color:'#fff',
							fontSize:16,
						}
					},
					data: [resData[0].totalCount,resData[1].totalCount ,resData[2].totalCount ,resData[3].totalCount ,resData[4].totalCount ,resData[5].totalCount ]//工位制与非工位制数据对应总和
				},
				{
					name: '工位制',
					type: 'bar',
					barWidth:30,
					barGap: "-100%",
					stack: 'two',
					data: [resData[0].planCount,resData[1].planCount ,resData[2].planCount ,resData[3].planCount ,resData[4].planCount ,resData[5].planCount ]
				},
				{
					name: '非工位制',
					type: 'bar',
					barWidth:30,
					barGap: "-100%",
					stack: 'two',
					itemStyle:{
						barBorderRadius:[5,5,0,0]
					},
					data: [resData[0].noPlanCount,resData[1].noPlanCount ,resData[2].noPlanCount ,resData[3].noPlanCount ,resData[4].noPlanCount ,resData[5].noPlanCount ]
				}
			]
		};
		echarts.init(bar).setOption(option);
	}
	
	
	//renderBar("echartBar1");
	//renderBar("echartBar2");
	//renderBar("echartBar3");
	//renderBar("echartBar4");
	//renderBar("echartBar5");
	//renderBar("echartBar6");
	
	function initBarSingle(id,resData){
		var stationArr=[];
		var overArr=[];
		if(resData==""||resData==null){
			return;
		}
		var list=resData.list;
		if(list==null){
			return;
		}
		for(var i=0;i<list.length;i++){
			stationArr.push(list[i].stationCode);
			overArr.push(list[i].planCount);
		}
		var bar = document.getElementById(id);
		var option = {
			title:{
				text: "出库计划",
				textStyle:{
					color: "#89bef3",
					fontSize:24,
					fontWeight:'bold'
				},
				left:"center",
				top:"8%"
			},
			color: ['#2cc0f7', '#ee8a62'],
			grid: {
				top:'30%',
				bottom:'12%'
			},
			xAxis: {
				type: 'category',
				axisLine:{show:false},
				axisTick: {show: false},
				axisLabel: {color:"#89bef3",fontSize:16},
				//data: ['gw001', 'gw002', 'gw003', 'gw004', 'gw005', 'gw006', 'gw007', 'gw008', 'gw009', 'gw010']
				data:stationArr
			},
			dataZoom:[
						{
							type:'inside',
							startValue:stationArr[0],
							endValue:stationArr[8],
						}
					],
			yAxis: {
				type: 'value',
				name: '{a|'+resData.planCount+'} \n {b|总计}',
				nameLocation: 'end',
				nameGap: 20,
				nameTextStyle:{
					color:'#89bef3',
					fontSize:16,
					align:'left',
					rich:{
						a:{
							color: '#4fd4ff',
							fontSize:36,
							lineHeight: 40
						},
						b:{
							color: '#89bef3',
							fontSize:16,
							lineHeight: 30
						}
					}
				},
				axisLine:{show:false},
				axisLabel: {color:"#89bef3",fontSize:16},
				splitLine:{
					lineStyle:{
						color:"#295288",

					}
				}
			},
			series: [
				{
					name: '完成',
					type: 'bar',
					barWidth:30,
					barGap: "20%",
					itemStyle:{
						barBorderRadius:[5,5,0,0]
					},
					label: {
						normal: {
							show: true,
							position: 'top',
							//color:'#fff',
							fontSize:16,
						}
					},
					//data: [160, 182, 100, 120, 182, 100, 120, 182, 100, 120]
					data:overArr
				}
			]
		};
		echarts.init(bar).setOption(option);
	};