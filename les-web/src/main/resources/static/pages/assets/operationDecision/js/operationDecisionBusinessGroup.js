$(function(){
	queryBusinessGroupSupplyPool();
	queryBusinessGroupQualityInspectionPool();
	queryBusinessGroupDistributionRate(1);
	queryBusinessGroupMaterialControl();
	queryBusinessGroupDistributionTask();
	queryReceivingTaskList();
	queryBusinessGroupInspectionList();
});

 
//供应齐套率
function queryBusinessGroupSupplyPool(){
	 var productLine =$("#productLine").val();
	 var option ={
            url:ctx+'/operationDecisionNew/queryBusinessGroupSupplyPool',
            type:'POST',
            data:{"productLine":productLine},
            dataType:'json',
            success:function(data){
                if(data.code==0){
                	var data =data.data;
                	var list = data.list;
               var supplyHtml ='';
               for(var i =0;i<list.length;i++){
            	   var obj = list[i];
            	   supplyHtml =supplyHtml+'<p>'+obj.warehouseName+'：'+obj.rate+'%</p>'; 
               }

               
                $("#supply").html(supplyHtml);	
			    $("#supplyNum").html('昨日未齐套：'+data.isShortNum+'单/'+data.totalNum+'单');		
			    $("#supplyRateValue").html(data.rate+"%");
				 $('#supplyRate').attr("data-percent",data.rate);
                 $('#supplyRate').easyPieChart({
         			trackColor: '#dfdfdf',
         			scaleColor: false,
         			size:100,		
         			barColor: '#fff'
         		});
              }
               
            },error:function(data){
                layer.alert(data.msg);
            }
        }
        $.ajax(option);
}

//质检齐套率
function queryBusinessGroupQualityInspectionPool(){
	 var productLine =$("#productLine").val();
	 var option ={
            url:ctx+'/operationDecisionNew/queryBusinessGroupQualityInspectionPool',
            type:'POST',
            data:{"productLine":productLine},
            dataType:'json',
            success:function(data){
                if(data.code==0){
                	var data =data.data;
                	var list = data.list;
                	 var html ='';
               for(var i =0;i<list.length;i++){
            	   var obj = list[i];
            	   html =html+'<p>'+obj.warehouseName+'：'+obj.rate+'%</p>'; 
               }
              
             
                $("#inspection").html(html);	
			    $("#inspectionNum").html('昨日未齐套：'+data.isShortNum+'单/'+data.totalNum+'单');		
			    $("#inspectionRateValue").html(data.rate+"%");
			    $('#qualityRate').attr("data-percent",data.rate);
                $('#qualityRate').easyPieChart({
        			trackColor: '#dfdfdf',
        			scaleColor: false,
        			size:100,		
        			barColor: '#fff'
        		});
              }
               
            },error:function(data){
                layer.alert(data.msg);
            }
        }
        $.ajax(option);
}

//配送及时率率
function queryBusinessGroupDistributionRate(requireDate){
	var productLine =$("#productLine").val();
	 var option ={
            url:ctx+'/operationDecisionNew/queryBusinessGroupDistributionRate',
            type:'POST',
          
            data:{"requireDate":requireDate,"productLine":productLine},
            dataType:'json',
            success:function(data){
                if(data.code==0){
                	var data =data.data;
                	var list = data.list;
                	 var html ='';
               for(var i =0;i<list.length;i++){
            	   var obj = list[i];
            	   html =html+'<p>'+obj.warehouseName+'：'+obj.rate+'%</p>'; 
               }
              
               if(requireDate==1){
            	   $("#timelinessRateToDay").html(html);	   
            	   $("#distributionNum").html('昨日未及时：'+data.overtimeNum+'单/'+data.totalNum+'单');		
               }else{
            	   
            	   $("#timelinessRateYesterday").html(html);	
            	   $("#distributionNum").html('前日未及时：'+data.overtimeNum+'单/'+data.totalNum+'单');		
               }

			    $("#distributionRateValue").html(data.rate+"%");
			    $('#timelinessRate').attr("data-percent",data.rate);
                $('#timelinessRate').easyPieChart({
        			trackColor: '#dfdfdf',
        			scaleColor: false,
        			size:100,		
        			barColor: '#fff'
        		});
              }
               
            },error:function(data){
                layer.alert(data.msg);
            }
        }
        $.ajax(option);
}

//查询三日物控信息
function queryBusinessGroupMaterialControl(){
	var productLine =$("#productLine").val();
	 var option ={
            url:ctx+'/operationDecisionNew/queryBusinessGroupMaterialControl',
            type:'POST',
            data:{"productLine":productLine},
            dataType:'json',
            success:function(data){
                if(data.code==0){
                	var data =data.data;
                	var dateTime="";
                	var firstDate =data.firstDate.substring(5,11);;
                	var secondDate =data.secondDate.substring(5,11);
                	var thirdDate =data.thirdDate.substring(5,11);;
                	var threeDayMaterialDateHtml ='<div><span>'+firstDate+'</span></div>'
             		+'<div><span>'+secondDate+'</span></div>'
             		+'<div><span>'+thirdDate+'</span></div>';
                	$("#threeDayMaterialDate").html(threeDayMaterialDateHtml);
                	var list = data.list;
                	 var html ='';
                   for(var i =0;i<list.length;i++){
                	   var threeDayMaterialObj = list[i];
                	   html =html+ '<dl><dt>';
                	   html=html+threeDayMaterialObj.warehouseName;
                	   
                	   html=html+'</dt><dd><table>';
                	   for(var j=0;j<3;j++){
                		   var info = threeDayMaterialObj[j];
                		  if(info !=undefined){
                			  dateTime =info.dataTime; 
                   		   
                   		   
                     		 html=html +'<tr>'
     						+'<td>'+info.project+'</td>'
     						+'<td>缺'+info.firstMaterialTypeNum+'种 &nbsp; '+info.firstmaterialNum+'笔</td>'
     						+'<td>缺'+info.secondMaterialTypeNum+'种 &nbsp; '+info.secondmaterialNum+'笔</td>'
     						+'<td>缺'+info.thirdMaterialTypeNum+'种 &nbsp; '+info.thirdmaterialNum+'笔</td>'
     						+'</tr>';
                     	   }else{
                     		  html=html +'<tr>'
       						+'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>'
       						+'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>'
       						+'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>'
       						+'<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>'
       						+'</tr>';
                     	   } 
                		  }
                		  
                	      

                	   html=html +'</table>'
						+'</dd>'
					    +'</dl>';
                	   
                   }
                 
                 	$("#threeDayMaterialData").html(html);
                 	$("#threeDayMaterialDateTime").html(dateTime);
                	
               
              }
               
            },error:function(data){
                layer.alert(data.msg);
            }
        }
        $.ajax(option);
}

var queryTime ="";
//配送任务
function queryBusinessGroupDistributionTask(){
	var productLine =$("#productLine").val();
	 var option ={
            url:ctx+'/operationDecisionNew/queryBusinessGroupDistributionTask',
            type:'POST',
            dataType:'json',
            data:{"productLine":productLine},
            success:function(data){
                if(data.code==0){
                	var data =data.data;
                	var numMap =data.numMap;
                	var distributionTaskList =numMap.distributionTaskList;
                	var handoverTaskList =numMap.handoverTaskList;
                	
                	  queryTime =data.queryTime;
                	
                	var  html ='<h2>配送任务</h2>'
            	   +'<div class="dateTime">数据时间：<span id="distributionTaskTime"></span></div>'
            	   +'<div class="summary w476">剩余：'+numMap.allleaveOverNum+'笔 &nbsp; 总计：'+numMap.allTotalNum+'笔 &nbsp; 遗留单：计'+numMap.allMaterialTypeNum+'种共'+numMap.allMaterialNum+'笔</div>';
               for(var i =0;i<distributionTaskList.length;){
            	   var num=Number(i)+1;
            	   var objOne = distributionTaskList[i];
            	   var objTwo = distributionTaskList[i+1];
            	   
            	   html =html+'<div class="row" ><div class="col-sm-6">'
            	   +'<div class="pieDetial">'
            	   +'<div>剩余：'+objOne.leaveOverNum+'笔</div>'
            	   +'<div>总计：'+objOne.totalNum+'笔</div>'
            	   +'<div>遗留单：计'+objOne.materialTypeNum+'种物料，共'+objOne.materialNum+'笔</div>'
            	   +'</div>'
            	   +'<div class="gEchartBox piesBox">'
            	   +'<div class="gEchart" id="echartPie0'+num+'"></div>'
            	   +'</div>'
            	   +'</div>';
            	   if(Number(i)+1<distributionTaskList.length){
            		   var num=Number(i)+2;
            		   html =html+ '<div class="col-sm-6">'
                	   +'<div class="pieDetial">'
                	   +'<div>剩余：'+objTwo.leaveOverNum+'笔</div>'
                	   +'<div>总计：'+objTwo.totalNum+'笔</div>'
                	   +'<div>遗留单：计'+objTwo.materialTypeNum+'种物料，共'+objTwo.materialNum+'笔</div>'
                	   +'</div>'
                	   +'<div class="gEchartBox piesBox">'
                	   +'<div class="gEchart" id="echartPie0'+num+'"></div>'
                	   +'</div>'
                	   +'</div>' ;
            		   
            	   }
            	   html =html+'</div>';
            	  
            	   i=i+2;
            	   
               }
                	
           $("#distributionTask").html(html);
           for(var i =0;i<distributionTaskList.length;i++){
        	   var num=Number(i)+1;
        	   var  id = "echartPie0"+num;
          	   var obj = distributionTaskList[i];
        	   echarts.init(document.getElementById(id)).setOption({
	    			title:{
	    				text:obj.warehouseName,
	    				x:"center",
	    				textStyle:{
	    					color:"#666",
	    					fontSize:"14",
	    					fontFamily:"Microsoft YaHei"
	    				}
	    			},
	    			color:["#50d1bf","#d8d8d8"],
	    			series:[{
	    				type:"pie",
	    				center:["50%","60%"],
	    				labelLine:{
	    					show:false
	    				},
	    				itemStyle:{
	    					normal:{

	    					},
	    					emphasis:{

	    					}
	    				},
	    				data:[obj.leaveOverNum,obj.totalNum]
	    			}]
	    		});  
           } 
         
     
           
              html ='<h2>交接任务</h2>'
        	   +'<div class="dateTime">数据时间：<span id="handoverTaskTime"></span></div>'
        	   +'<div class="summary w476">剩余：'+numMap.allHandoverLeaveOverNum+'笔 &nbsp; 总计：'+numMap.allHandoverTotalNum+'笔 &nbsp;</div>';
		     for(var i =0;i<handoverTaskList.length;){
		  	   var num=Number(i)+1;
		  	   var objOne = handoverTaskList[i];
		  	   var objTwo = handoverTaskList[i+1];
		  	   
		  	   html =html+'<div class="row" ><div class="col-sm-6">'
		  	   +'<div class="pieDetial">'
		  	   +'<div>剩余：'+objOne.leaveOverNum+'笔</div>'
		  	   +'<div>总计：'+objOne.totalNum+'笔</div>'
		  	  
		  	   +'</div>'
		  	   +'<div class="gEchartBox piesBox">'
		  	   +'<div class="gEchart" id="echartPie2'+num+'"></div>'
		  	   +'</div>'
		  	   +'</div>';
		  	   if(Number(i)+1<handoverTaskList.length){
		  		   var num=Number(i)+2;
		  		   html =html+ '<div class="col-sm-6">'
		      	   +'<div class="pieDetial">'
		      	   +'<div>剩余：'+objTwo.leaveOverNum+'笔</div>'
		      	   +'<div>总计：'+objTwo.totalNum+'笔</div>'
		      	  
		      	   +'</div>'
		      	   +'<div class="gEchartBox piesBox">'
		      	   +'<div class="gEchart" id="echartPie2'+num+'"></div>'
		      	   +'</div>'
		      	   +'</div>' ;
		  		   
		  	   }
		
		  	  html =html+'</div>';

		  	   i=i+2;
		  	   
		     }
     
		     $("#handoverTask").html(html);
		     for(var i =0;i<distributionTaskList.length;i++){
	        	   var num=Number(i)+1;
	        	   var  id = "echartPie2"+num;
	          	   var obj = distributionTaskList[i];
	        	   echarts.init(document.getElementById(id)).setOption({
		    			title:{
		    				text:obj.warehouseName,
		    				x:"center",
		    				textStyle:{
		    					color:"#666",
		    					fontSize:"14",
		    					fontFamily:"Microsoft YaHei"
		    				}
		    			},
		    			color:["#50d1bf","#d8d8d8"],
		    			series:[{
		    				type:"pie",
		    				center:["50%","60%"],
		    				labelLine:{
		    					show:false
		    				},
		    				itemStyle:{
		    					normal:{

		    					},
		    					emphasis:{

		    					}
		    				},
		    				data:[obj.leaveOverNum,obj.totalNum]
		    			}]
		    		});  
	           } 
	           }
                
                $("#distributionTaskTime").html(queryTime);
            	$("#handoverTaskTime").html(queryTime);
            	
          
            },error:function(data){
                layer.alert(data.msg);
            }
        }
        $.ajax(option);
}

function queryReceivingTaskList(){
	var productLine =$("#productLine").val();
	 var option ={
            url:ctx+'/operationDecisionNew/queryReceivingTaskList',
            type:'POST',
            dataType:'json',
            data:{"productLine":productLine},
            success:function(data){
                if(data.code==0){
                	var list =data.data;
                	 
                	var  html ='<h2>收货任务</h2>'
                 	   +'<div class="dateTime">数据时间：<span id="receivingTaskTime"></span></div>';
               for(var i =0;i<list.length;){
            	   var num=Number(i)+1;
            	   var objOne = list[i];
            	   var objTwo = list[i+1];
            	   var leaveOverOne = list[i];
            	   var leaveOverTwo = list[i+1];
            	   html =html+'<div class="row" ><div class="col-sm-6">'
            	   +'<div class="pieDetial">'
            	   +'<div>剩余：'+objOne.notReceiveNum+'笔</div>'
            	   +'<div>总计：'+objOne.totalNum+'笔</div>'
            	  
            	   +'</div>'
            	   +'<div class="gEchartBox piesBox">'
            	   +'<div class="gEchart" id="echartPie3'+num+'"></div>'
            	   +'</div>'
            	   +'</div>';
            	   if(Number(i)+1<list.length){
            		   var num=Number(i)+2;
            		   html =html+ '<div class="col-sm-6">'
                	   +'<div class="pieDetial">'
                	   +'<div>剩余：'+objTwo.notReceiveNum+'笔</div>'
                	   +'<div>总计：'+objTwo.totalNum+'笔</div>'
                	   
                	   +'</div>'
                	   +'<div class="gEchartBox piesBox">'
                	   +'<div class="gEchart" id="echartPie3'+num+'"></div>'
                	   +'</div>'
                	   +'</div>' ;
            		   
            	   }
            	   html =html+'</div>';
 
            	  
            	   i=i+2;
            	   
               }
                	
             $("#receivingTask").html(html);
             
             
             for(var i =0;i<list.length;i++){
      		   var num=Number(i)+1;
      		   var  id = "echartPie3"+num;
      		   var obj = list[i];
          	   echarts.init(document.getElementById(id)).setOption({
 	    			title:{
 	    				text:obj.warehouseName,
 	    				x:"center",
 	    				textStyle:{
 	    					color:"#666",
 	    					fontSize:"14",
 	    					fontFamily:"Microsoft YaHei"
 	    				}
 	    			},
 	    			color:["#50d1bf","#d8d8d8"],
 	    			series:[{
 	    				type:"pie",
 	    				center:["50%","60%"],
 	    				labelLine:{
 	    					show:false
 	    				},
 	    				itemStyle:{
 	    					normal:{

 	    					},
 	    					emphasis:{

 	    					}
 	    				},
 	    				data:[obj.notReceiveNum,obj.totalNum]
 	    			}]
 	    		});  
             }
             $("#receivingTaskTime").html(queryTime);
         	
              }
               
            },error:function(data){
                layer.alert(data.msg);
            }
        }
        $.ajax(option);
}

//查询质检任务列表
function queryBusinessGroupInspectionList(){
	var productLine =$("#productLine").val();
	 var option ={
            url:ctx+'/operationDecisionNew/queryBusinessGroupInspectionList',
            type:'POST',
            dataType:'json',
            data:{"productLine":productLine},
            success:function(data){
                if(data.code==0){
                	var list =data.data;
                	var html ='';
                	for(var i=0;i<list.length;i++){
                		var obj = list[i];
                		var urgentList = obj.urgentList;
                		var unUrgentList = obj.unUrgentList;
                		var warehouseName =obj.warehouseName;
                		if(Number(i)%2==0){
                			html=html+'<div class="row">';
                			
                		}
                		html=html+'<div class="col-lg-6">';
                		html=html+'<div class="ZJRW"><div class="hd"><span>'+warehouseName+'</span></div>' ;      						
                		html=html+'<div class="bd"> '
    						+'<div class="tit">    '
							+'<span>紧急物料</span>'
							+'<span>待检：'+urgentList[0]+'笔</span> '
							+'<span>超期：'+urgentList[1]+'笔</span> '
    					    +'</div>    '
    						+'<div class="cet">    '
    						+' <table>  '
							+' <tr>     '
							+'	<td><div>4h</div></td>   '
							+'	<td><div>'+urgentList[2]+'</div></td>'
							+'	<td><div>2h</div></td>   '
							+'	<td><div>'+urgentList[3]+'</div></td>'
							+'	<td><div>1h</div></td>   '
							+'	<td><div>'+urgentList[4]+'</div></td>'
							+'	<td><div>0h</div></td>   '
							+'	<td><div>'+urgentList[5]+'</div></td>'
							+'	<td><div>1h</div></td>   '
							+'	<td><div>'+urgentList[6]+'</div></td>'
							+'	<td><div>2h</div></td>   '
							+'	<td><div>'+urgentList[7]+'</div></td>'
							+'	<td><div>4h</div></td>   '
							+'	<td><div>'+urgentList[8]+'</div></td>'
    						+'	</tr>   '
    						+'	</table>'
    						+'	</div>  '
    						+'	<div class="line"><i></i></div> '
    						+'    </div>'
    						+'     <div class="bd">'
    						+'	<div class="tit">  '
    						+'		<span>常规物料</span>'
    						+'		<span>待检：'+unUrgentList[0]+'笔</span>'
    						+'		<span>超期：'+unUrgentList[1]+'笔</span>'
    						+'	</div>  '
    						+'	<div class="cet">  '
    						+'	  <table>   '
    						+'	    <tr>'
							+'		<td><div>3d</div></td>'
							+'		<td><div>'+unUrgentList[2]+'</div></td>'
							+'		<td><div>2d</div></td>'
							+'		<td><div>'+unUrgentList[3]+'</div></td>'
							+'		<td><div>1d</div></td>'
							+'		<td><div>'+unUrgentList[4]+'</div></td>'
							+'		<td><div>0d</div></td>'
							+'		<td><div>'+unUrgentList[5]+'</div></td>'
							+'		<td><div>1d</div></td>'
							+'		<td><div>'+unUrgentList[6]+'</div></td>'
							+'		<td><div>2d</div></td>'
							+'		<td><div>'+unUrgentList[7]+'</div></td>'
							+'		<td><div>2d</div></td>'
							+'		<td><div>'+unUrgentList[8]+'</div></td>'
    						+'		</tr>   '
    						+'		</table>'
    						+'	</div>  '
    						+'	<div class="line"><i></i></div> '
    						+'    </div>'
    					    +'    </div>'
    				        +'    </div>';
                	 
                		if(Number(i)%2==1){
                			html=html+'    </div>';
                			
                		}
                		 
                	}
                	$("#inspectionList").after(html);
                	$("#inspectionListTime").html(queryTime);

              }
               
            },error:function(data){
                layer.alert(data.msg);
            }
        }
        $.ajax(option);
}