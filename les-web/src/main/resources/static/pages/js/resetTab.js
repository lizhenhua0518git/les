/**
* @author	GaoYuan
* @date		2019.06.24
* 重置tab标签
*/


;(function(imgArr){
	"use strict";
	
/*	layui.use(['element'],function(){
		  var element = layui.element;
		  element.render('tab','layout-tabs');
		  element.on("tab(layout-tabs)",function(elem){
		   if(elem.index === 0){
		    //跳转
		    window.history.back(-1);
		    //或者自定义跳转页面
		    //window.location.href='http://www.baidu.com';
		   }
		  });
		 });*/
	
	//添加修改的样式表
	var _link = document.createElement("link");
	_link.rel = "stylesheet";
	_link.type = "text/css";
	_link.href = "pages/js/resetTab.css";
	document.getElementsByTagName("head").item(0).appendChild(_link);
	
	//去掉关闭按钮
//	$(".layui-body .layui-tab").attr('lay-allowclose',false);
	$(".layui-body .layui-tab").removeAttr('lay-allowclose');
	layui.use(['element'],function(){
		  var element = layui.element;
		  element.render('tab','layout-tabs');
	});
	
	//动态设置icon
	var _style = document.getElementById("tabStyle");
	if(!_style){
		_style = document.createElement("style");
		_style.id = "tabStyle";
		_style.type = "text/css";
	}
	var _styleHtml = "";
	var tabNum = $(".layui-body .layui-tab .layui-tab-title li");
	for(var i = 1; i<tabNum.length+1; i++){
		//content: url(images/admin.jpg)此处的图片地址需要改成对应的
		
		if(imgArr.length>0){
			_styleHtml = _styleHtml + ".layui-body .layui-tab .layui-tab-title li:nth-child("+ i +")::before{content: url(pages/images/"+ imgArr[(i-1)] +");}";
		}else{
			_styleHtml = _styleHtml + ".layui-body .layui-tab .layui-tab-title li:nth-child("+ i +")";
		}
		
	}
	try{
		_style.innerHTML = _styleHtml;
	}
	catch(e){//针对IE
		_style.styleSheet.cssText = _styleHtml;
	} 
	console.log(tabNum,_style.innerHTML)
	document.getElementsByTagName("head").item(0).appendChild(_style);
})(imgArr);








