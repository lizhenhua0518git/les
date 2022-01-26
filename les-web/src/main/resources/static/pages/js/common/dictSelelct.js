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
 *
 * @param dictType 自动类型
 * @param permission 绑定元素的id
 * @param selectValue 是否默认选中值
 * @returns
 */
function queryDictItem(dictType,permission,selectValue){
			  var contextPath = config.basicInfoConfig.contextPath;
			  var dictTypeArray = new Array();
			  dictTypeArray.push(dictType);
				//查询字典
				var option={
						url:contextPath+'dictItems/listDictItemByTypes',
						type:'POST',
						dataType:'json',
						contentType: "application/json",
						data:JSON.stringify(dictTypeArray),
						success:function(res){
							if(res.code==0){
								dictItems = res.data
								defulatSelect(res.data,permission,selectValue);
							}
						}
	  			};
				$.ajax(option);
		  }
function defulatSelect(dictItems,permission,selectValue){
	  if(dictItems && dictItems.length>0){
		  for(var i=0;i<dictItems.length;i++){
			  //if(dictItems[i].dictTypeId=="enable"){
				  $("#"+permission).append("<option value="+dictItems[i].itemValue+">"+dictItems[i].itemName+"</option>");
			 // }
		  }
		 if(selectValue !==''){
			 $("#"+permission).val(selectValue);
		 }
		 layui.form.render("select");
	  }
}
/**
 *
 * @param dictItems :多个字典值集合
 * @param dictTypeId :字典类型id
 * @param permission :元素id
 * @param selectValue :默认选中值
 */
function defulatSelects(dictItems,dictTypeId,permission,selectValue){
	  if(dictItems && dictItems.length>0){
		  for(var i=0;i<dictItems.length;i++){
			  if(dictItems[i].dictTypeId==dictTypeId){
				  $("#"+permission).append("<option value="+dictItems[i].itemValue+">"+dictItems[i].itemName+"</option>");
			  }
		  }
		 if(selectValue !==''){
			 $("#"+permission).val(selectValue);
		 }
		 layui.form.render("select");
	  }
}
/**.
 *
 * @param dictTypes 查询一批字典信息
 */
function queryDictItems(dictTypes,dictLabelId,defualtValues){
	var contextPath = config.basicInfoConfig.contextPath;
	var option={
			url:contextPath+'dictItems/listDictItemByTypes',
			type:'POST',
			dataType:'json',
			contentType: "application/json",
			data:JSON.stringify(dictTypes),
			success:function(res){
				if(res.code==0){
					dictItems = res.data
					if(dictItems!=null && dictItems.length>0){
						for(var i=0;i<dictLabelId.length;i++){
							defulatSelects(dictItems,dictTypes[i],dictLabelId[i],defualtValues[i]);
						}
					}

					return dictItems;
				}else{
					return null;
				}
			},erro:function(res){
				return null;
			}
		};
	$.ajax(option);
}
/**.
 *
 * @param dictTypes
 * @param dictLabelId
 * @param defualtValues
 * 多选下拉框使用
 */
function initDictItemMultiSelect(dictTypes,dictLabelIds,defualtValues){
	var contextPath = config.basicInfoConfig.contextPath;
	var option={
			url:contextPath+'dictItems/listDictItemByTypes',
			type:'POST',
			dataType:'json',
			contentType: "application/json",
			data:JSON.stringify(dictTypes),
			success:function(res){
				if(res.code==0){
					dictItems = res.data
					if(dictItems!=null && dictItems.length>0){
						for(var i=0;i<dictLabelIds.length;i++){
							defulatMultiSelect(dictItems,dictTypes[i],dictLabelIds[i],defualtValues[i]);
						}
					}

					return dictItems;
				}else{
					return null;
				}
			},erro:function(res){
				return null;
			}
		};
	$.ajax(option);
}
/**.
 *
 * @param dictItems
 * @param dictType
 * @param dictLabelId
 * @param defualtValue
 * 给select赋option
 */
function defulatMultiSelect(dictItems,dictTypeId,dictLabelId,defualtValue){
	var optoinArray = new Array();
	if(dictItems && dictItems.length>0){
		  var itemOption;
		  for(var i=0;i<dictItems.length;i++){
			  if(dictItems[i].dictTypeId==dictTypeId){
				  itemOption = {"name":""+dictItems[i].itemName, "value":""+dictItems[i].itemValue};
				  optoinArray.push(itemOption);
			  }
		  }
		 layui.formSelects.data(dictLabelId,'local',{
				arr:optoinArray
		 });
      	 layui.formSelects.value(dictLabelId, defualtValue,true);
		 layui.form.render("select");
	  }
}
/**
 * 初始化工厂信息
 */
function inintFactory(factory_search){
	var contextPath = config.basicInfoConfig.contextPath
	var option = {
		url:contextPath + 'plmPlantStructure/listFactoryByType?modelType=1',
		type:"get",
		success:function(result){
			if(result.code==0){
				var factoryCodes=result.data;
				if (factoryCodes.length>0){
					for(var i=0;i<factoryCodes.length;i++){
						$("#"+factory_search).append("<option value="+factoryCodes[i].modelCode+"  >"+factoryCodes[i].modelName+"</option>");
					}
				}
				form.render();
			}
		}
	}
	$.ajax(option);
}

/**
 * 初始化工厂信息
 */
function inintFactorySelect(factory_search,selectValue){
	var contextPath = config.basicInfoConfig.contextPath
	var option = {
		url:contextPath + 'plmPlantStructure/listFactoryByType?modelType=1',
		type:"get",
		success:function(result){

			if(result.code==0){
				var factoryCodes=result.data;
				if (factoryCodes.length>0){
					for(var i=0;i<factoryCodes.length;i++){
						$("#"+factory_search).append("<option value="+factoryCodes[i].modelCode+"  >"+factoryCodes[i].modelName+"</option>");
					}
					$("#"+factory_search).val(selectValue);
					layui.form.render("select");
				}
				form.render();
			}
		}
	}
	$.ajax(option);
}
/**
 * 加载单据类型
 */
function inintBillTypeMultiSelect(xmselectId,defualtValue){
	var contextPath = config.basicInfoConfig.contextPath
    var option = {
            url:contextPath + 'documentType/listByBusinessType?businessType=2&status=1',
            type:"GET",
            success:function(result){
                   if(result.code==0){
                       var documentType=result.data;
                       if (documentType.length>0){
      					 var optoinArray = new Array();
      					 for(var i=0;i<documentType.length;i++){
      						  itemOption = {"name":""+documentType[i].documentName, "value":""+documentType[i].documentCode};
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
/**
 * 加载单据类型 入库和调拨类型的单据类型
 */
function initBillTypeMultiSelect2(xmselectId,defualtValue,businessTypeStr){
	var contextPath = config.basicInfoConfig.contextPath;
    var option = {
            url:contextPath + 'documentType/listByBusinessType?businessTypeStr='+businessTypeStr+'&status=1',
            type:"GET",
            success:function(result){
                   if(result.code==0){
                       var documentType=result.data;
                       if (documentType.length>0){
      					 var optoinArray = new Array();
      					 for(var i=0;i<documentType.length;i++){
      						  itemOption = {"name":""+documentType[i].documentName, "value":""+documentType[i].documentCode};
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
/**
 * 初始化工厂信息
 */
function inintFactoryMultiSelect(xmselectId,defualtValue){
	var contextPath = config.basicInfoConfig.contextPath
	var option = {
		url:contextPath + 'plmPlantStructure/listFactoryByType?modelType=1',
		type:"get",
		success:function(result){
			if(result.code==0){
				var factoryCodes=result.data;
				if (factoryCodes.length>0){
					 var optoinArray = new Array();
					 for(var i=0;i<factoryCodes.length;i++){
						  itemOption = {"name":""+factoryCodes[i].modelName, "value":""+factoryCodes[i].modelCode};
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
/**.
 *
 * @param xmselectId
 * @param defualtValue
 * @param type
 * 加载工厂1、车间2、产线3、工位4
 */
function inintPlantMultiSelect(xmselectId,defualtValue,type){
	var contextPath = config.basicInfoConfig.contextPath;
	var option = {
		url:contextPath + 'plmPlantStructure/listFactoryByType?modelType='+type,
		type:"get",
		success:function(result){
			if(result.code==0){
				var factoryCodes=result.data;
				console.log("factoryCodes",factoryCodes)
				if (factoryCodes.length>0){
					 var optoinArray = new Array();
					 for(var i=0;i<factoryCodes.length;i++){
						  itemOption = {"name":""+factoryCodes[i].modelName, "value":""+factoryCodes[i].modelCode,"id":""+factoryCodes[i].oId};
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

/**.
 * 通过状态和pid获取信息
 * @param xmselectId
 * @param defualtValue
 * @param type ,pid
 * defualtValue加载  1工厂、2车间、3产线、4工位
 * flag 1查询工位 2查询产线
 * @param pid
 * @param flag
 */
/**
 * T_PLM_PLANT_STRUCTURE数据初始化
 * 对应产线,工位下拉框
 * @param xmselectId 节点id
 * @param defualtValue 默认值
 * @param type 类型
 * @param modelCode
 * @param flag
 */
function inintPlantMultiByPidSelect(xmselectId, defualtValue, type, modelCode) {
	var dictTypes = {type: type, modelCode: modelCode};
	var contextPath = config.basicInfoConfig.contextPath;
	console.log("dictTypes>>>>", dictTypes);
	var option = {
		url: contextPath + 'plmPlantStructure/listPlantModel',
		type: 'POST',
		dataType: 'json',
		contentType: "application/json",
		data: JSON.stringify(dictTypes),
		success: function (result) {
			if (result.code == 0) {
				var factoryCodes = result.data;
				var optoinArray = new Array();
				for (var i = 0; i < factoryCodes.length; i++) {
					optoinArray.push({
						"name": factoryCodes[i].modelName,
						"value": factoryCodes[i].modelCode,
						"id": factoryCodes[i].oId
					});
				}
				layui.formSelects.data(xmselectId, 'local', {
					arr: optoinArray
				});
				layui.formSelects.value(xmselectId, defualtValue, true);
				layui.form.render("select");
			}
		}
	};
	$.ajax(option);
}

/**
* @param factory_search 下拉框id
* @param type
* 加载工厂1、车间2、产线3、工位4
 */
function inintPlantFactory(factory_search,type){
	var contextPath = config.basicInfoConfig.contextPath
	var option = {
		url:contextPath + 'plmPlantStructure/listFactoryByType?modelType='+type,
		type:"get",
		success:function(result){
			if(result.code==0){
				var factoryCodes=result.data;
				if (factoryCodes.length>0){
					for(var i=0;i<factoryCodes.length;i++){
						$("#"+factory_search).append("<option value="+factoryCodes[i].modelCode+"  >"+factoryCodes[i].modelName+"</option>");
					}
				}
				form.render();
			}
		}
	}
	$.ajax(option);
}



/**
 *
 * @param dictType 自动类型
 * @param permission 绑定元素的id
 * @param selectValue 是否默认选中值
 * @returns
 */
function initDictItem(dictType,permission,selectValue){
	var contextPath = config.basicInfoConfig.contextPath;
	var option = {
		url:contextPath + 'dict/initDictItemList?dictType='+dictType,
		type:"GET",
		success:function(result){
			if(result.code==0){
				var documentType=result.data;
				if (documentType.length>0){
					for(var i=0;i<documentType.length;i++){
						$("#"+permission).append("<option value="+documentType[i].itemValue+">"+documentType[i].itemName+"</option>");
					}
					if(selectValue !==''){
						$("#"+permission).val(selectValue);
					}
					layui.form.render("select");
				}
			}
		}
	}
	$.ajax(option);
}

/**
 * 加载单据类型 入库和调拨类型的单据类型
 */
function initDictItemList(xmselectId,defualtValue,dictType){
	var contextPath = config.basicInfoConfig.contextPath;
	var option = {
		url:contextPath + 'dict/initDictItemList?dictType='+dictType,
		type:"GET",
		success:function(result){
			if(result.code==0){
				var documentType=result.data;
				if (documentType.length>0){
					var optoinArray = new Array();
					for(var i=0;i<documentType.length;i++){
						itemOption = {"name":""+documentType[i].itemName, "value":""+documentType[i].itemValue};
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
