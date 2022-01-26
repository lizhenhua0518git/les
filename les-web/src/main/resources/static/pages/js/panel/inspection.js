/// <reference path="typings/index.d.ts" />

//数据更新频率（毫秒）
var updateInterval=30000;
//页面切换频率（毫秒）
var updatePageInterval=2000;
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
var url="/iles/panel/queryInspectRecord";
//是否首次加载
var load=false;

$(function(){
  update();
 setInterval(update,updateInterval);
 setInterval(updateScroll,updatePageInterval);
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
	/**
    $.post(url,function(data){
        console.log('更新数据');
        console.log(data.data);
        create_page(data.data);
        items2=data.data;
    },'json');
    **/
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

// 创建页面数据
function create_page(data){
	var title =  '<span class="list list-primary">待检'+ data.total1+'</span>';
		title += '<span class="list list-warning">紧急 '+ data.total2 +'</span>';
		title += '<span class="list list-danger">超期 '+ data.total3 +'</span>';
		title += '<span class="list list-info">待转储 '+ data.total4 +'</span>';
		$("#inpsecTitle").html(title);
	
	var mapData = gen_data(data.areaMap);
	var areaList = ['A', 'B', 'C', 'D'];
	var subAreaList = [1, 2, 3, 4, 5, 6];
	var content = "";
	for(var i=0; i<areaList.length; i++){
		var prefix = areaList[i];
		content += '<div class="col-md-3 col-xs-3 col-sm-6" ><div class="grid">';
		for(var j=0; j<subAreaList.length; j++){
			var area = prefix+subAreaList[j];
			content += '<div class="team-main"> <span class="text">';
			content += area;
			var valMap = mapData[area];
			if(valMap == "undefined" || valMap == null){
				valMap = {"1": 0, "2": 0, "3": 0, "4": 0};
			}
			content += '</span><span class="team team-primary">'+ hdata(valMap["1"]);
			content += '</span><span class="team team-warning">'+ hdata(valMap["2"]);
			content += '</span><span class="team team-danger">'+ hdata(valMap["3"]);
			content += '</span><span class="team team-info">'+ hdata(valMap["4"])+'</span></div>'
		}
		content += '</div></div>';
	}
	$("#inspecContent").html(content);
	
	/**
	 * 处理结果集合
	 */
	function hdata(val){
		if(val == "undefined" || val == null){
			return 0;
		}
		return val;
	}
	
	function gen_data(data){
        new_data = {};
        for(var i in data){
            var arrList = i.split("-");
            var prefix = arrList[arrList.length -1];
            prefix = prefix.replace("0", "").replace("0", "").replace("0", "");
            new_data[prefix] = data[i];
        }
        return new_data;
    }
}
function queryArea(){
	 var  areaUrl ="/iles/panel/queryArea";
	 $.post(areaUrl,function(data){
	        console.log('更新数据');
	        console.log(data.data);
	        var title =  '<span class="list list-primary" id="checkTotal"></span>';
			title += '<span class="list list-warning" id="isUrgentTotal"></span>';
			title += '<span class="list list-danger" id="overtimeTotal"></span>';
			title += '<span class="list list-info" id="dumpTotal"></span>';
			$("#inpsecTitle").html(title);
			var list = data.data;
			var content ="";
			var areaList = ['A', 'B', 'C'];
			var subAreaList = [1, 2, 3, 4, 5, 6];
			var content = "";
			var k=-1;
			for(var i=0; i<areaList.length; i++){
				
				var prefix = areaList[i];
				content += '<div class="col-md-3 col-xs-3 col-sm-6" style="margin-left:25px"><div class="grid">';
				for(var j=0; j<subAreaList.length; j++){
					k++;
					var area = prefix+subAreaList[j];
					content += '<div class="team-main"> <span class="text">';
					content += area;
				 
					content += '</span><span class="team team-primary" id="'+list[k].areaCode+'_check">';
					content += '</span><span class="team team-warning" id="'+list[k].areaCode+'_isUrgent">';
					content += '</span><span class="team team-danger" id="'+list[k].areaCode+'_overtime">';
					content += '</span><span class="team team-info" id="'+list[k].areaCode+'_dump"></span></div>';
				}
				content += '</div></div>';
			}
			$("#inspecContent").html(content);
			
			  $.post(url,function(data){
			        console.log('更新数据');
			        console.log(data.data);
			        var map = data.data;
			        var checkList =map.check;
			        var dumpList =map.dump;
			        var overtimeList =map.overtime;
			        var isUrgentList =map.isUrgent;
			     
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
			        	
			        	 $("#dumpTotal").html("转储"+dumpSum);
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




