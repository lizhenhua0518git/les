$(function(){
		querySupplyPool();
		queryQualityInspectionPool();
		queryDistributionRate(1);
		queryMaterialControl();
		queryDistributionTask();
		queryInspectionList();
});

function getPercent(num, total) {
    /// <summary>
    /// 求百分比
    /// </summary>
    /// <param name="num">当前数</param>
    /// <param name="total">总数</param>
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00)+"%";
}
//供应齐套率
function querySupplyPool(){
	 
	 var option ={
            url:ctx+'/operationDecisionNew/querySupplyPool',
            type:'POST',
            contentType: "application/json",
            dataType:'json',
            success:function(data){
                if(data.code==0){
                	var data =data.data;
               var supplyHtml ='';
               supplyHtml =supplyHtml+'<p>动车组装：'+data.dczz+'%</p>';
               supplyHtml =supplyHtml+'<p>动车车体：'+data.dcct+'%</p>';
               supplyHtml =supplyHtml+'<p>碳钢组装：'+data.tgzz+'%</p>';
               supplyHtml =supplyHtml+'<p>碳钢车体：'+data.tgct+'%</p>';
               supplyHtml =supplyHtml+'<p>动车检修：'+data.dcjx+'%</p>';
               supplyHtml =supplyHtml+'<p>转向架线：'+data.zxj+'%</p>';
                $("#supply").html(supplyHtml);	
			    $("#supplyNum").html('昨日未齐套：'+data.unIsShortSum+'单/'+data.sum+'单');		
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
function queryQualityInspectionPool(){
	 
	 var option ={
            url:ctx+'/operationDecisionNew/queryQualityInspectionPool',
            type:'POST',
            contentType: "application/json",
            dataType:'json',
            success:function(data){
                if(data.code==0){
                	var data =data.data;
               var html ='';
               html =html+'<p>动车组装：'+data.dczz+'%</p>';
               html =html+'<p>动车车体：'+data.dcct+'%</p>';
               html =html+'<p>碳钢组装：'+data.tgzz+'%</p>';
               html =html+'<p>碳钢车体：'+data.tgct+'%</p>';
               html =html+'<p>动车检修：'+data.dcjx+'%</p>';
               html =html+'<p>转向架线：'+data.zxj+'%</p>';
                $("#inspection").html(html);	
			    $("#inspectionNum").html('昨日未齐套：'+data.unInspectionSum+'单/'+data.sum+'单');		
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
function queryDistributionRate(requireDate){
	 
	 var option ={
            url:ctx+'/operationDecisionNew/queryDistributionRate',
            type:'POST',
          
            data:{"requireDate":requireDate},
            dataType:'json',
            success:function(data){
                if(data.code==0){
                	var data =data.data;
               var html ='';
               html =html+'<p>动车组装：'+data.dczz+'%</p>';
               html =html+'<p>动车车体：'+data.dcct+'%</p>';
               html =html+'<p>碳钢组装：'+data.tgzz+'%</p>';
               html =html+'<p>碳钢车体：'+data.tgct+'%</p>';
               html =html+'<p>动车检修：'+data.dcjx+'%</p>';
               html =html+'<p>转向架线：'+data.zxj+'%</p>';
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
function queryMaterialControl(){
	 
	 var option ={
            url:ctx+'/operationDecisionNew/queryMaterialControl',
            type:'POST',
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
                	   if(i==0){
                		   html=html+'动车组装   '; 
                	   }else if(i==1){
                		   html=html+'动车车体   '; 
                	   }else if(i==2){
                		   html=html+'碳钢组装   '; 
                	   }else if(i==3){
                		   html=html+'碳钢车体   '; 
                	   }else if(i==4){
                		   html=html+'动车检修   '; 
                	   }else if(i==5){
                		   html=html+'转向架   '; 
                	   } 
                	   
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


//配送任务
function queryDistributionTask(){
	 
	 var option ={
            url:ctx+'/operationDecisionNew/queryDistributionTask',
            type:'POST',
            dataType:'json',
            success:function(data){
                if(data.code==0){
                	var data =data.data;
                	var numMap =data.numMap;
                	var leaveOverNumMap =data.leaveOverNumMap;
                	var queryTime =data.queryTime;
                	$("#distributionTaskTime").html(queryTime);
               var distributionTaskSumHtml ='剩余：'+numMap.leaveOverNum+'笔 &nbsp; 总计：'+numMap.sum+'笔 &nbsp; 遗留单：计'+leaveOverNumMap.materialTypeNum+'种共'+leaveOverNumMap.materialNum+'笔';
                $("#distributionTaskSum").html(distributionTaskSumHtml);	
               var html ='';
               html ='<div>剩余：'+numMap.dcctleaveOverNum+'笔</div>'
				+'<div>总计：'+numMap.dcctSum+'笔</div>'
				+'<div>遗留单：计'+leaveOverNumMap.dcctMaterialTypeNum+'种物料，共'+leaveOverNumMap.dcctMaterialNum+'笔</div>';
               $("#dcct").html(html);
              
              html ='<div>剩余：'+numMap.dczzleaveOverNum+'笔</div>'
				+'<div>总计：'+numMap.dczzSum+'笔</div>'
				+'<div>遗留单：计'+leaveOverNumMap.dczzMaterialTypeNum+'种物料，共'+leaveOverNumMap.dczzMaterialNum+'笔</div>';
              $("#dczz").html(html);
              
              html ='<div>剩余：'+numMap.tgctleaveOverNum+'笔</div>'
				+'<div>总计：'+numMap.tgctSum+'笔</div>'
				+'<div>遗留单：计'+leaveOverNumMap.tgctMaterialTypeNum+'种物料，共'+leaveOverNumMap.tgctMaterialNum+'笔</div>';
              $("#tgct").html(html);
            
            
	            html ='<div>剩余：'+numMap.tgzzleaveOverNum+'笔</div>'
				+'<div>总计：'+numMap.tgzzSum+'笔</div>'
				+'<div>遗留单：计'+leaveOverNumMap.tgzzMaterialTypeNum+'种物料，共'+leaveOverNumMap.tgzzMaterialNum+'笔</div>';
	            $("#tgzz").html(html);
	            
	            html ='<div>剩余：'+numMap.dcjxleaveOverNum+'笔</div>'
				+'<div>总计：'+numMap.dcjxSum+'笔</div>'
				+'<div>遗留单：计'+leaveOverNumMap.dcjxMaterialTypeNum+'种物料，共'+leaveOverNumMap.dcjxMaterialNum+'笔</div>';
	            $("#dcjx").html(html);
	            
	            
	            html ='<div>剩余：'+numMap.zxjleaveOverNum+'笔</div>'
				+'<div>总计：'+numMap.zxjSum+'笔</div>'
				+'<div>遗留单：计'+leaveOverNumMap.zxjMaterialTypeNum+'种物料，共'+leaveOverNumMap.zxjMaterialNum+'笔</div>';
	            $("#zxj").html(html);
	           //饼状图
	            
	             
	             
	            
	    		echarts.init(document.getElementById('echartPie01')).setOption({
	    			title:{
	    				text:"动车车体",
	    				x:"center",
	    				textStyle:{
	    					color:"#666",
	    					fontSize:"14",
	    					fontFamily:"Microsoft YaHei"
	    				}
	    			},
	    			color:["#ed7789","#d8d8d8"],
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
	    				data:[numMap.dcctleaveOverNum,numMap.dcctSum]
	    			}]
	    		});
	    		echarts.init(document.getElementById('echartPie02')).setOption({
	    			title:{
	    				text:"动车组装",
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
	    				data:[numMap.dczzleaveOverNum,numMap.dczzSum]
	    			}]
	    		});
	    		echarts.init(document.getElementById('echartPie03')).setOption({
	    			title:{
	    				text:"碳钢车体",
	    				x:"center",
	    				textStyle:{
	    					color:"#666",
	    					fontSize:"14",
	    					fontFamily:"Microsoft YaHei"
	    				}
	    			},
	    			color:["#e2ab4a","#d8d8d8"],
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
	    				data:[numMap.tgctleaveOverNum,numMap.tgctSum]
	    			}]
	    		});
	    		echarts.init(document.getElementById('echartPie04')).setOption({
	    			title:{
	    				text:"碳钢组装",
	    				x:"center",
	    				textStyle:{
	    					color:"#666",
	    					fontSize:"14",
	    					fontFamily:"Microsoft YaHei"
	    				}
	    			},
	    			color:["#bccc65","#d8d8d8"],
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
	    				data:[numMap.tgzzleaveOverNum,numMap.tgzzSum]
	    			}]
	    		});
	    		echarts.init(document.getElementById('echartPie05')).setOption({
	    			title:{
	    				text:"动车检修",
	    				x:"center",
	    				textStyle:{
	    					color:"#666",
	    					fontSize:"14",
	    					fontFamily:"Microsoft YaHei"
	    				}
	    			},
	    			color:["#69c1e0","#d8d8d8"],
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
	    				data:[numMap.dcjxleaveOverNum,numMap.dcjxSum]
	    			}]
	    		});
	    		echarts.init(document.getElementById('echartPie06')).setOption({
	    			title:{
	    				text:"转向架线",
	    				x:"center",
	    				textStyle:{
	    					color:"#666",
	    					fontSize:"14",
	    					fontFamily:"Microsoft YaHei"
	    				}
	    			},
	    			color:["#b78dd1","#d8d8d8"],
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
	    				data:[numMap.zxjleaveOverNum,numMap.zxjSum]
	    			}]
	    		});
                
              }
               
            },error:function(data){
                layer.alert(data.msg);
            }
        }
        $.ajax(option);
}


//查询质检任务列表
function queryInspectionList(){
	 
	 var option ={
            url:ctx+'/operationDecisionNew/queryInspectionList',
            type:'POST',
            dataType:'json',
            success:function(data){
                if(data.code==0){
                	var list =data.data.list;
                	var queryTime=data.data.queryTime;
                	$("#inspectionListTime").html(queryTime);
                	var html ='';
                	for(var i=0;i<list.length;){
                		var urgentList = list[i];
                		var unUrgentList = list[i+1];
                		html=html+'<div class="col-lg-6">';
                		if(i==0){
                			html=html+'<div class="ZJRW"><div class="hd"><span>动车车体</span></div>' ;
                		 }else if(i==2){
                			 html=html+'<div class="ZJRW bg-ed7789"><div class="hd"><span>动车组装</span></div>' ;
                		 }else if(i==4){
                			 html=html+'<div class="ZJRW bg-bccc65"><div class="hd"><span>碳钢车体</span></div>' ;
                		 }else if(i==6){
                			 html=html+'<div class="ZJRW bg-59add2"><div class="hd"><span>碳钢组装</span></div>' ;
                		 }else if(i==8){
                			 html=html+'<div class="ZJRW bg-abb679"><div class="hd"><span>动车检修</span></div>' ;
                		 }else if(i==10){
                			 html=html+'<div class="ZJRW bg-b78dd1"><div class="hd"><span>转向架</span></div>' ;
                		 }
                		
    						
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
                		i=i+2;
                		
                	}
                	$("#inspectionList").html(html);
       
               
              }
               
            },error:function(data){
                layer.alert(data.msg);
            }
        }
        $.ajax(option);
}



