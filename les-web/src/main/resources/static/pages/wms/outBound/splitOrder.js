/**
 * 分批订单
 */
var i;
/**
 * 根据凭证分解仓库库存
 * @param type
 */
function splitStroage(type){
	//弹出层
	i = ityzl_SHOW_LOAD_LAYER();
	$("#matchButton").attr("class","layui-btn layui-btn-normal layui-btn-sm layui-btn-disabled");
	$("#matchButton").attr("disabled","disabled");
	var option = {
    	url:config.omsConfig.contextPath+'order/noBeatDecompose',
    	data:{type:type},
    	type:'POST',
    	success:function(data){
    		if(data.code==0){
    			splitWareCode(type);
    		}else if(data.code==9){
    			layer.confirm('没找到凭证信息,是否继续匹配TO', {icon: 3}, function(index){
    				$("#matchButton").attr("class","layui-btn layui-btn-normal layui-btn-sm layui-btn-disabled");
    				$("#matchButton").attr("disabled","disabled");
    				splitWareCode(type);
    				layer.close(index);
    				i = ityzl_SHOW_LOAD_LAYER();
    			});
    			$("#matchButton").attr("class","layui-btn layui-btn-normal layui-btn-sm");
           	 	$("#matchButton").removeAttr("disabled");
    		}else if(data.msg.indexof("处理中")>0){
    			layer.alert("数据正在处理中,请稍后", {icon: 5});
    		}else {
    			layer.alert("请联系管理员!", {icon: 5});
    		}
    	},error:function(data){
    		splitWareCode(type);
    		layer.alert(data.msg, {icon: 5});
    	}
    }
    $.ajax(option);
}
/**
 * 根据工序订单分解仓位库存
 * @param type
 */
function splitWareCode(type){
	var option = {
    	url:config.omsConfig.contextPath+'order/orderProcessing',
    	data:{type:type},
    	type:'POST',
    	success:function(data){
    		if(data.code==0){
    			ityzl_CLOSE_LOAD_LAYER(i);
           	 	ityzl_SHOW_TIP_LAYER();
           	 	$("#matchButton").attr("class","layui-btn layui-btn-normal layui-btn-sm");
           	 	$("#matchButton").removeAttr("disabled");
    			layer.alert('分批成功！', {icon: 6});
    		}else{
    			ityzl_CLOSE_LOAD_LAYER(i);
    			layer.alert(data.msg, {icon: 5});
    			$("#matchButton").attr("class","layui-btn layui-btn-normal layui-btn-sm");
        		$("#matchButton").removeAttr("disabled");
    		}
    	},error:function(data){
    		ityzl_CLOSE_LOAD_LAYER(i);
    		layer.alert(data.msg, {icon: 5});
    		$("#matchButton").attr("class","layui-btn layui-btn-normal layui-btn-sm");
    		$("#matchButton").removeAttr("disabled");
    	}
    }
    $.ajax(option); 
}
function pullOrder(){
	//弹出层
	i = ityzl_SHOW_LOAD_LAYER();
	$("#pullOrder").attr("class","layui-btn layui-btn-normal layui-btn-sm layui-btn-disabled");
	$("#pullOrder").attr("disabled","disabled");
	var option = {
    	url:config.apiConfig.contextPath+'/push/quart/pullProcessOrder',
    	type:'GET',
    	success:function(data){
    		if(data.code==0){
			layer.alert(data.msg, {icon: 5});
    		}else if(data.msg.indexof("处理中")>0){
    			layer.alert("数据正在处理中,请稍后", {icon: 5});
    		}else {
    			layer.alert("请联系管理员!", {icon: 5});
    		}
		ityzl_CLOSE_LOAD_LAYER(i);
		$("#pullOrder").attr("class","layui-btn layui-btn-normal layui-btn-sm");
    		$("#pullOrder").removeAttr("disabled");
    	},error:function(data){
    		layer.alert(data.msg, {icon: 5});
			ityzl_CLOSE_LOAD_LAYER(i);
			$("#pullOrder").attr("class","layui-btn layui-btn-normal layui-btn-sm");
	    		$("#pullOrder").removeAttr("disabled");
	    	}
    }
    $.ajax(option);
}
/**
 * 弹出层
 */
;!function(){
	 
	layer.ready(function(){ 
	});
}();
function ityzl_SHOW_LOAD_LAYER(){
	return layer.msg('正在导入,请稍等...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: '0px', time:10000000}) ;
}
function ityzl_CLOSE_LOAD_LAYER(index){
	layer.close(index);
}
function ityzl_SHOW_TIP_LAYER(){
	layer.msg('恭喜您，加载完成！',{time: 1000,offset: '10px'});
}