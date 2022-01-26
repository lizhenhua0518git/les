
        var layuiElement = layui.element;    
        var layer = layui.layer;
        var  h =0;
        var contextPath = config.uasConfig.contextPath;
        function loadding(){
        	defaultPage();
            listMenus();
            //loadMsgCount();
        }
        //默认页
        function defaultPage(){
        	var isData = false;
        	//首页
        	$.each($(".layui-tab-title li[lay-id]"), function () {
        		if ($(this).attr("lay-id") == "indexNew") {
        			isData = false;
        		}
        	})
        	if (isData == false) {
        		active.tabAdd("/iles/pages/welcome.html","indexNew","首页");
        	}
        	active.tabChange("indexNew");
    		//工作看板-运营组
    		$.each($(".layui-tab-title li[lay-id]"), function () {
                if ($(this).attr("lay-id") == "200005") {
                    isData = true;
                }
            })
    		
    		//入库作业监控
    		$.each($(".layui-tab-title li[lay-id]"), function () {
                if ($(this).attr("lay-id") == "200036") {
                    isData = true;
                }
            })
    		
    		//出库作业监控
    		$.each($(".layui-tab-title li[lay-id]"), function () {
                if ($(this).attr("lay-id") == "200043") {
                    isData = true;
                }
            })
    		//仓位库存流水账
    		$.each($(".layui-tab-title li[lay-id]"), function () {
                if ($(this).attr("lay-id") == "77EB71EF425913E9E053D901000A810E") {
                    isData = true;
                }
            })
    		
        }
      //获取左侧导航菜单 开始
        function listMenus(){
            $.ajax({
                 url:contextPath+'resource/getResoureList'
                ,dataType: "json"
                ,type:"GET"
                ,async:false
                ,success:addMenu
            });
        }
      //加载菜单图片 开始
        function loadIcon(fileName){
            var result = "";
            var option = {
                     url:'/iles/imgToBase64Servlet?fileName='+fileName,
                     type:'GET',
                     async:false,
                     success:function(res){
                        if(res.code==0){
                            result= "data:image/png;base64,"+res.data;
                        }else{
                            return "";
                        }
                     }
                };
                $.ajax(option); 
            return result;
        }
      //加载菜单图片 结束
        //附加左侧导航菜单 开始
        function addMenu(result){
        	
            var html = ' <li> <a class="nav-header" data-toggle="collapse"  href="javascript:void(0);" onclick="leftMenun()" ><i class="fa fa-dashboard"></i>系统导航<i class="layui-icon" id="left_bar_hidden" style="position: absolute; right: 0px;" >&#xe668;</i></a> </li>';
            var data = $.parseJSON(result.data);
            $.each(data,function(i,item){
//            	if(i==0){
//            		html += ' <li class="active" >';	
//            	}else{
            		html += ' <li>';
//            	}
            	
            	html += ' <a class="nav-header" data-toggle="collapse" href=""javascript:void(0);">';
            	html += '<i class="fa fa-'+i+'"></i>'+item.title+'<span class="fa arrow"></span></a>';
            	 if(item.children !== undefined && item.children.length > 0){ 
//            		 if( i==0){
//            			 html +='<ul   class="nav nav-second-level collapse in" >'; 
//            		 }else{
            			 html +='<ul   class="nav nav-second-level collapse">';
//            		 }
            		 
            		  $.each(item.children,function(j,item2){ 
            			   
            			  if(item2.children !== undefined && item2.children.length > 0){ 
            				 
            				  html += ' <li>';
            					html += ' <a class="nav-header" data-toggle="collapse" href=""javascript:void(0);">';
            	            	html += '<i class="fa fa-'+i+'"></i>'+item2.title+'<span class="fa arrow"></span></a>';
            				   html +='<ul   class="nav nav-second-level collapse">';
            				  $.each(item2.children,function(k,item3){ 
            					  html +=' <li> <a href="javascript:;" data-id="'+item3.id+'" data-url="'+item3.url+'" data-title="'+item3.title+'">'+item3.title+'</a> </li> ';  
            				  })
            				  html +=' </ul> </li>';
            			  }else{  
            				  html +=' <li> <a href="javascript:;" data-id="'+item2.id+'" data-url="'+item2.url+'" data-title="'+item2.title+'">'+item2.title+'</a> </li> ';  
            			   }
            			 
            		  });
            		  html +='  </li>';
                 }
            	
            	  html +=' </ul>';
                 
            });
             $('#main-menu').append(html);   
              (function ($, window, document, undefined) {
                 
            	    var pluginName = "metisMenu",
            	        defaults = {
            	            toggle: true
            	        };
            	        
            	    function Plugin(element, options) {
            	        this.element = element;
            	        this.settings = $.extend({}, defaults, options);
            	        this._defaults = defaults;
            	        this._name = pluginName;
            	        this.init();
            	    }

            	    Plugin.prototype = {
            	        init: function () {
            	        	
            	            var $this = $(this.element),
            	                $toggle = this.settings.toggle;
            	          
            	            $this.find('li.active').has('ul').children('ul').addClass('collapse in');
            	            $this.find('li').not('.active').has('ul').children('ul').addClass('collapse');

            	            $this.find('li').has('ul').children('a').on('click', function (e) {
            	             
            	                e.preventDefault();
            	            
            	                $(this).parent('li').toggleClass('active').children('ul').collapse('toggle');

            	                if ($toggle) {
            	                	
            	                    $(this).parent('li').siblings().removeClass('active').children('ul.in').collapse('hide');
            	                }
            	                
            	                  var obj = $(this).parent('li').hasClass("active");
            	                  
            	                	if(obj){
            	                		var t=$(this);
            	                		 setTimeout(function () { 
            	                			 
            	                			var subUlH=t.parent('li').children('ul').children('li').length;
            	                			 var ulH= parseInt(subUlH)*60;
            	                			// $('#main-menu').height(h+ulH);
            	                			 
            	                			 }, 
            	                			 200);
                    	                 	
                    	                
            	                	} 
            	          
                	               
            	                 
            	            });
            	        }
            	    };

            	    $.fn[ pluginName ] = function (options) {
            	        return this.each(function () {
            	            if (!$.data(this, "plugin_" + pluginName)) {
            	                $.data(this, "plugin_" + pluginName, new Plugin(this, options));
            	            }
            	        });
            	    };
            	    $('#main-menu').metisMenu();
           
            	})(jQuery, window, document);
           
                //h = $('#main-menu').height();
                
             // $('#main-menu').height(h);
        }
        var active;
        layui.use(['element','jquery','layer'],function(){
            
            //左侧导航菜单点击事件开始
            $(document).on('click','.sidebar-collapse  ul  a',function(){
                var dataid = $(this);
                if(!dataid.attr("data-url")){
                    return ;
                }
                if ($(".layui-tab-title li[lay-id]").length <= 0) {
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
                } else {
                    var isData = false;
                    $.each($(".layui-tab-title li[lay-id]"), function () {
                        if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                            isData = true;
                        }
                    })
                    if (isData == false) {
                        active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
                    }
                }
                active.tabChange(dataid.attr("data-id"));
            });
            
            //左侧导航菜单点击事件结束
            
            //tab右键开始
             function customRightClick(id) {
                 $('.layui-tab-title li').on('contextmenu', function () { return false; });
                 $('.layui-tab-title,.layui-tab-title li').click(function () {
                    $('.rightmenu').hide();
                });
                //桌面点击右击 
                $('.layui-tab-title li').on('contextmenu', function (e) {
                    var popupmenu = $(".rightmenu");
                    popupmenu.find("li").attr("data-id",id);
                    l = ($(document).width() - e.clientX-200) < popupmenu.width() ? (e.clientX-200 - popupmenu.width()) : e.clientX-200;
                    t = ($(document).height() - e.clientY-50) < popupmenu.height() ? (e.clientY-50 - popupmenu.height()) : e.clientY-50;
                    popupmenu.css({ left: l, top: t }).show();
                    //alert("右键菜单")
                    return false;
                });
            } 
             //tab右键结束
             
             //右键功能开始
             $(".rightmenu li").click(function () {
                if ($(this).attr("data-type") == "closethis") {
                    active.tabDelete($(this).attr("data-id"))
                } else if ($(this).attr("data-type") == "closeall") {
                    var tabtitle = $(".layui-tab-title li");
                    var ids = new Array();
                    $.each(tabtitle, function (i) {
                        ids[i] = $(this).attr("lay-id");
                    })   
                    active.tabDeleteAll(ids);
                }
                $('.rightmenu').hide();
             })
             //右键功能结束
              
            //tab选项卡增删切换事件开始
             active = {
                tabAdd: function (url, id,title) {
                //新增一个Tab项
                layuiElement.tabAdd('layout-tabs', {
                    title: title
                  , content: '<iframe data-frameid="'+id+'" frameborder="0" name="tabContent" scrolling="yes" width="100%"  src="' + url + '"  onload="this.height=this.contentDocument.body.scrollHeight" ></iframe>'
                  , id: id 
                })
                customRightClick(id);//绑定右键菜单
                frameWH();//计算框架高度
                }
                , tabChange: function (id) {
                  //切换到指定Tab项
                  layuiElement.tabChange('layout-tabs', id); //切换到：用户管理
                  $("iframe[data-frameid='"+id+"']").attr("src",$("iframe[data-frameid='"+id+"']").attr("src"))//切换后刷新框架
                }
                , tabDelete: function (id) {
                    layuiElement.tabDelete("layout-tabs", id);//删除
                }
                , tabDeleteAll: function (ids) {//删除所有
                    $.each(ids, function (i,item) {
                        layuiElement.tabDelete("layout-tabs", item);
                    })
                }
            }
            //tab选项卡增删切换事件结束
            function frameWH() {
                //header 50; tab-title 31
                var h = $(window).height();
                h = h-99
                $("iframe").css("height",h+"px");
            }
     
            $(window).resize(function () {
                frameWH();
            })
            
        });
      
        //附加左侧导航菜单 结束
      //登出 开始
        function logout(){
            $.ajax({
                 url:contextPath+'simpleLogout'
                ,async:false
                ,type:"GET"
                ,dataType:'json'
                ,success:function(result){
                    if(result.code==0){
                        var url = "/iles/pages/login.html";
                        window.location.href=url;
                        window.localStorage.removeItem("token");
                        window.event.returnValue=false; 
                    }
                }
            });
        }
      //登出 结束
        //修改用户密码
        function changePassWord(){
            layer.open({
                type: 2,    //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                title:"修改密码",
                offset:'auto',//具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                id: 'changePass', //防止重复弹出
                area: ['650px', '350px'],
                content: 'changePassWord.html',
                shade: 0,//不显示遮罩
                yes: function(){
                    layer.closeAll();
                }
            });
        }
        
        //获取消息数量开始
        function loadMsgCount(){
        	 
            var option={
                    url:"msgLog/queryMsgConut?userId="+userId+"&isreaded=0",
                    type:"GET",
                    dataType: "json",
                    success:function(res){
                        if(res.code==0)
                            $("#msgCount").text(res.data);
                    }
            };
            $.ajax(option);
        }
        //获取消息数量结束
      //全屏、正常、简易屏幕切换 开始
		$(".layui-nav-child a").click(function(){
			var node = $(this);
			if(!node.attr("data-item"))
				return;
			switch (node.attr("data-item")){
				case 'screen-full':
					toFullScreen();
					break;
				case 'screen-high-header':
					highHeaderScreen();
					break;
				case 'screen-low-header':
					lowHeaderScreen();
					break;
				default:
					return;
			}
		});
		//全屏、正常、简易屏幕切换 结束  
		
		//左侧导航栏展开收缩开始
		var i = 0 ;
		function leftMenun(){
			if(i==0){
	  			$(".sidebar-collapse").animate({width:'toggle'});
	  			$(".layui-side-narrow").animate({width:'toggle'});
	  			$(".layui-body").animate({left:'28px'});
	  			$(".layui-card-body").css({"position":"absolute","top":"43px","left":"0","bottom":"0","right":"0"});
	  			i++;
	  		}else{
				$(".sidebar-collapse").animate({width:'toggle'});
				$(".layui-side-narrow").animate({width:'toggle'});
				$(".layui-body").animate({left:'200px'});
				i--;
			}
		}
		//左侧导航栏展开收缩结束
		//加载websocket
		function websocketInit(){
			var websocket = null;
	        var host = document.location.host;
	        var tNum=1;
	        var layArray = new Array();
	        var userId = "";
	        if("WebSocket" in window){
	            console.log("host:"+host);
	            websocket = new WebSocket("ws://"+host+"/iles/websocket/"+userId);
	            websocket.onopen = function(){
	                console.log("websocket 连接成功");
	            }
	            websocket.onerror = function(){
	                console.log("websocket 连接错误");
	            }
	            websocket.onmessage = function(event){
	                var temp = eval('('+event.data+')');
	                var msgCount = $("#msgCount").text();
	                $("#msgCount").text(parseInt(msgCount)+1);
	                showMsgContent(temp.title,temp.content);
	            }
	            websocket.colse = function(){
	                console.log("websocket 关闭");
	            }
	            
	        }else{
	            layer.msg("当前浏览器不支持websocket！无法接收消息")
	        }
	        window.onbeforeunload = function(event){
	            if(websocket!=null)
	                websocket.close();
	        }
		}
		
	 function showMsgContent(titleStr,messageContent){
            layer.open({
                type: 1
                ,title:titleStr
                ,offset:'rb'//具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                ,id: 'rabitMq'+tNum //防止重复弹出
                ,content: '<div style="padding: 20px 50px;width:250px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden">'+messageContent+'</div>'
                ,shade: 0 //不显示遮罩
                ,btn: ['查看消息']
                ,btnAlign: 'r' //按钮居中
                ,tipsMore: true
                ,yes: function(){
                    showMsgTab();
                    layer.closeAll();
                }
              });
            tNum++;
        }
        function showMsgTab(){
        	
            var isData = false;
            var msgManageObj = $("#main-menu").find("[data-title='消息日志管理']");
            var dataId = msgManageObj.attr("data-id");
            if(dataId && dataId.length>0){
             $.each($(".layui-tab-title li[lay-id]"), function () {
                    if ($(this).attr("lay-id") == dataId) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    active.tabAdd("pages/message/msgLog.html",dataId,"消息日志管理");
                }
                active.tabChange(dataId);
            }
        }
        //获取用户组织信息
        function oraginInint(){
        	var option = {
        			url : "./orgination/queryWarehouse",
        			type : "GET",
        			success : function(res) {
        				if (res.code == 0) {
        					var data = res.data;
        					var currWareHose = data.currWareHose;
        					var wareHoseList = data.wareHoseList;
        					for (var i = 0; i < wareHoseList.length; i++) {
        						if(currWareHose==wareHoseList[i].storageLocation){
        							$("#orgCode").append("<option value='"+wareHoseList[i].orgCode+"' storageLocation='"+wareHoseList[i].storageLocation+"' orgCode='"+wareHoseList[i].orgCode+"' selected>"+ wareHoseList[i].orgName + "</option>")
        						}else{
        							$("#orgCode").append("<option value='"+wareHoseList[i].orgCode+"' storageLocation='"+wareHoseList[i].storageLocation+"' orgCode='"+wareHoseList[i].orgCode+"' >"+ wareHoseList[i].orgName + "</option>")
        						}
        					}
        				}
        			}
        		};
        		$.ajax(option);
        }
        
        function changeWarehose(obj){ 
    		var selecteObj = $(obj).find(':selected');
    		
      		var orgCode=selecteObj.attr("orgCode");		
      		var storageLocation=selecteObj.attr("storageLocation");
      		var orgName=selecteObj.html();
      		chooseWarehose(orgCode,orgName,storageLocation);
    	}
    	
    	function chooseWarehose(orgCode,orgName,storageLocation){   			
    		var option ={
    				url:'./orgination/chooseWarehouse',
    				type:"post",
    				data:JSON.stringify({storageLocation:storageLocation,orgName:orgName,orgCode:orgCode}),
    				contentType:'application/json',
    				dateType:"json",
    				success:function(res){
    					if(res.code==0){
     					}
    				}
    		};
    		$.ajax(option);		
    	}

    	function openPanelIndex(){
    		 
    		var warehoseName = $('#orgCode').find("option:selected").val();  
    		var url ="/iles/pages/panelIndex/panelIndex.html";
    		if(warehoseName=='00500100'){
    			url ="/iles/pages/panelIndex.html";
    		}else if(warehoseName=='00500400'){
    			url ="/iles/pages/panelIndex/yzz_panelIndex.html";
    		}else if(warehoseName=='00500300'){
    			url ="/iles/pages/panelIndex/zzz_panelIndex.html";
    		}else if(warehoseName=='00500500'){
    			url ="/iles/pages/panelIndex/djzk_panelIndex.html";
    		}else if(warehoseName=='00500600'){
    			url ="/iles/pages/panelIndex/djxbk_panelIndex.html";
    		}else if(warehoseName=='00501100'){
    			url ="/iles/pages/panelIndex/xbj_panelIndex.html";
    		}else if(warehoseName=='00501200'){
    			url ="/iles/pages/panelIndex/tgzk_panelIndex.html";
    		}
    		window.open(url);
    	}