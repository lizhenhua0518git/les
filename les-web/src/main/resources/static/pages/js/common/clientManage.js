if(!layui.formSelects){
	layui.config({
        base: '/iles/pages/layui/lay/modules/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });
	layui.use(['form','table','layer','laydate','formSelects'],function(){
		var formSelects = layui.formSelects;
		//点击车间下拉框 查询产线信息
		formSelects.on('workshopCode_search', function(id, vals, val, isAdd, isDisabled){
			//id:           点击select的id
			//vals:         当前select已选中的值
			//val:          当前select点击的值
			//isAdd:        当前操作选中or取消
			//isDisabled:   当前选项是否是disabled
			if (vals==null||vals.length===0){
				return;
			}
			console.log("vals>>>",vals,vals.length);
			//模型编码数组
			let arr = [];
			vals.forEach(function(item){
				//对应T_PLM_PLANT_STRUCTURE.MODEL_CODE is '模型编码'
				arr.push(item.value);
			});
			//初始化产线信息
			inintPlantMultiByPidSelect("lineCode_search", [], 3,arr,2);//产线
		}, true);
		//点击产线下拉框,,初始化工位信息
		formSelects.on('lineCode_search', function(id, vals, val, isAdd, isDisabled){
			if (vals==null||vals.length===0){
				return;
			}
			//模型编码数组
			let arr = [];
			vals.forEach(function(item){
				//对应T_PLM_PLANT_STRUCTURE.MODEL_CODE is '模型编码'
				arr.push(item.value);
			});
			inintPlantMultiByPidSelect("stationCode_search", [], 4,arr,1);//工位
		}, true);
	});
}

/**
 * 加载单据类型 入库和调拨类型的单据类型
 */
function initClientManageList(xmselectId,defualtValue){
	var contextPath = config.basicInfoConfig.contextPath;
	var option = {
		url:contextPath + 'clientManage/initClientManageList',
		type:"GET",
		success:function(result){
			if(result.code==0){
				var documentType=result.data;
				if (documentType.length>0){
					var optoinArray = new Array();
					for(var i=0;i<documentType.length;i++){
						itemOption = {"name":""+documentType[i].clientName, "value":""+documentType[i].clientManageId};
						optoinArray.push(itemOption);
					}
					layui.formSelects.data(xmselectId,'local',{
						arr:optoinArray
					});
					layui.formSelects.value(xmselectId, defualtValue,true);
					layui.form.render("select");
				}
			}
		}
	}
	$.ajax(option);
}

function initClientManage(permission,selectValue){
	var contextPath = config.basicInfoConfig.contextPath;
	var option = {
		url:contextPath + 'clientManage/initClientManageList',
		type:"GET",
		success:function(result){
			if(result.code==0){
				var documentType=result.data;
				if (documentType.length>0){
					if (documentType.length>0){
						for(var i=0;i<documentType.length;i++){
							$("#"+permission).append("<option value="+documentType[i].clientManageId+">"+documentType[i].clientName+"</option>");
						}
						if(selectValue !==''){
							$("#"+permission).val(selectValue);
						}
						layui.form.render("select");
					}
				}
			}
		}
	}
	$.ajax(option);
}
