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
var url="/iles/panel/queryLackMaterialRecord";
//是否首次加载
var load=false;

$(function(){
  update();
 setInterval(update,updateInterval);
 //setInterval(update,500);

 setInterval(updateScroll,updatePageInterval);
  setInterval(updateTime, 500);
});

function updateTime() {
    $('.time').text(format(new Date()));
}

function updateDays(days) {
	$('#date_range').val(days);
	 update();
}


function format(date){
	 
	var date_range=$('#date_range').val();	
	
	var endDate=date
	
 	
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
        
	
	endDate.setDate(date.getDate() + parseInt(date_range));

	
	$('#endDate').val(format_10(endDate));
    
    var y_e = endDate.getFullYear();
    var m_e = endDate.getMonth() + 1;
    m_e = append(m_e, 2, '0');
    var d_e = endDate.getDate();
    d_e = append(d_e, 2, '0');
        
    //return y + "年" + m + "月" + d + "日  " + h + ":" + mm + ":" + s;
    return m + "/" + d + ""+"--"+m_e + "/" + d_e + "";
}



function format_10(date){
	 
  	
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = append(m, 2, '0');
    var d = date.getDate();
    d = append(d, 2, '0');
  
        
     return  y + "-" +m + "-" + d;
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
function create_page1(data){
	var title =  '';
		title += '<span class="list list-danger">缺料笔数 '+'</span>';
		title += '<span class="list list-primary">需求笔数 '+ '</span>';
		$("#inpsecTitle").html(title);
	
	var mapData = gen_data(data.areaMap);
	var mapData = gen_data(data.main_install_list);
	console.log(mapData);
	var mapData = gen_data(data.pre_install_list);
	console.log(mapData);
	
	
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
	
 
 

	var dateRange=$('#date_range').val();

	 var  areaUrl ="/iles/panel/queryLackMaterialRecord?dateRange="+dateRange;
	 $.post(areaUrl,function(data){
  		        
			var pre_install_list = data.data[0].pre_install_list;
			
			var main_install_list = data.data[0].main_install_list;
						
			var total_info_list = data.data[0].total_info_list;

			for(var i=0; i<total_info_list.length; i++){
				 
				$('#total_kind').text(total_info_list[i].TOTAL_KIND );
				$('#no_lack_kind').html(total_info_list[i].NO_LACK_KIND );
				

			/*	$('#total_kind_1').text(total_info_list[i].TOTAL_KIND );
				$('#no_lack_station').html(total_info_list[i].TOTAL_STATION-total_info_list[i].LACK_STATION );
				$('#lack_kind').html(total_info_list[i].LACK_KIND );*/

				$('#total_station').html(total_info_list[i].TOTAL_STATION );
			 	$('#no_lack_station').html(total_info_list[i].TOTAL_STATION-total_info_list[i].LACK_STATION );

				$('#material_kind').html(total_info_list[i].MATERIAL_KIND);
				$('#material_qty').html(total_info_list[i].MATERIAL_QTY );
				
	 			
				/**  $('#workbay_material_kind').html('物料种类:   '+total_info_list[i].MATERIAL_KIND);
	 			$('#workbay_material_qty').html('物料数量:   '+total_info_list[i].MATERIAL_QTY); **/

			}
			
			
			 var list=$('span[id$=lack_qty]');			
			 for(var i=0; i<list.length; i++){ 
 				 list[i].innerText=0; 
			 }
			 
			 var list=$('span[id$=need_qty]');				
			 for(var i=0; i<list.length; i++){ 
 				 list[i].innerText=0; 
			 }
			 
			 
			 var list=$('td[id$=_name]');				
			 for(var i=0; i<list.length; i++){  
  				$('#'+list[i].id).css("color",'#d9d9d9');	
 				
			 }
			 
			 
			  
			 
			 
 		
			for(var i=0; i<pre_install_list.length; i++){

			 var id='#y_'+pre_install_list[i].WORKBAY_TYPE+'_'+pre_install_list[i].WORKBAY_CODE+'_';
 			$(id+'lack_qty').html(pre_install_list[i].LACK_QTY);
			$(id+'need_qty').html(pre_install_list[i].NEED_QTY);
			
			if(pre_install_list[i].LACK_QTY>0){
				
				$(id+'name').css("color",'#d2880c');
				/*$(id+'lack').css("color",'#d2880c');*/
				/*$(id+'need').css("color",'#d2880c');*/
				$(id+'red').css("color",'#d2880c');
				$(id+'green').css("color",'#d2880c');
		 
			}else{
	
				
				$(id+'name').css("color",'');
			/*	$(id+'lack').css("color",'');
				$(id+'need').css("color",'');*/
				$(id+'red').css("color",'');
				$(id+'green').css("background",'');
				
				
			}
		

			}
		 
			
			
			for(var i=0; i<main_install_list.length; i++){

				 var id='#z_'+main_install_list[i].WORKBAY_TYPE+'_'+main_install_list[i].WORKBAY_CODE+'_';
				 
				$(id+'lack_qty').html(main_install_list[i].LACK_QTY);
				
				$(id+'need_qty').html(main_install_list[i].NEED_QTY);
				
				if(main_install_list[i].LACK_QTY>0){
					
					$(id+'name').css("color",'#d2880c');
					/*$(id+'lack').css("color",'#d2880c');*/
					/*$(id+'need').css("color",'#d2880c');*/
					$(id+'red').css("color",'#d2880c');
					$(id+'green').css("color",'#d2880c');
			 
				}else{
		
					
					$(id+'name').css("color",'');
				/*	$(id+'lack').css("color",'');
					$(id+'need').css("color",'');*/
					$(id+'red').css("color",'');
					$(id+'green').css("background",'');
					
					
				}
			
				

				}
			 
				
			
			
	
			
	 
			
					        
	    },'json');
}


function showTab(branch,station){
	 var title="";
  	 var dataId="";
  	var dateRange=$('#date_range').val()
  	
     var requestTime =format_10(new Date());

     var url ="../warningLackMaterial/subWarningLackMaterialDemo.html?subfactory="+branch+"&dateRange="+dateRange+"&station="+station+"&requestTime="+requestTime;

     
	 title ='工位齐套预警';
	 
	 dataId ="subWarningLackMaterialDemoTabId"; 
	 
/*	 window.open(url,title);
*/	 toIndex(url);
}

function showTab_inner(branch,station){
  	 var title="";
  	 var dataId="";
  	var dateRange=$('#date_range').val()
  	
     var requestTime =format_10(new Date());

     var url ="pages/mfc/warningLackMaterial/subWarningLackMaterialDemo.html?subfactory="+branch+"&dateRange="+dateRange+"&station="+station+"&requestTime="+requestTime;

     
	 title ='工位齐套预警';
	 
	 dataId ="subWarningLackMaterialDemoTabId"; 
	
	
  	var isData = false;
    var liObj =$("body", parent.document).find(".layui-tab-title li[lay-id]");
    if(dataId && dataId.length>0){
     $.each(liObj, function () {
            if ($(this).attr("lay-id") == dataId) {
                isData = true;
                window.parent.active.tabDelete($(this).attr("lay-id"));
             }
	        
           
          
        })
        
     
    
       window.parent.active.tabAdd(url,dataId,title);
 
     window.parent.active.tabChange(dataId);
    }
   }




 	function toIndex(url) {
		parent.location.href = url;
	}
 

 	
 	function showDetail(){
 		 var title="";
 	  	 var dataId="";
 	  	var dateRange=$('#date_range').val()
  	  	
 	     var requestTime =format_10(new Date());
  	  
 	   var endDate=$('#endDate').val()
  	   
 	    var url ="/iles/pages/mfc/warningLackMaterial/warningLackMaterialDetail.html?type=1&subfactory=&station=&carCode=&process=&endTime="+endDate+"&requestTime="+requestTime;
  		 title ='工位齐套管理';
 		 
  		 dataId ="subWarningLackMaterialDemoTabId"; 
 		 
 	/*	 window.open(url,title);
 	*/	 toIndex(url);
 	}
 	



