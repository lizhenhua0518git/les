var formSelects = layui.formSelects;
//初始化LayUI modules 组件
if (!formSelects) {
    layui.config({
        base: '/iles/pages/layui/lay/modules/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });
    layui.use(['form', 'table', 'layer', 'laydate', 'formSelects'], function () {
        formSelects = layui.formSelects;
    });
}


// /**
//  * 功能描述：单选下拉仓库
//  * @param warehouseSelectId 下拉框ID
//  * @param permission 1-权限控制 0-不控制
//  * @param selectValue  选中的值
//  * @returns
//  */
// function initWarehouseSignSelect(warehouseSelectId, permission, selectValue) {
//     var params = {limit: 0, sortColums: "org_code asc", permission: permission};
//     var contextPath = config.basicInfoConfig.contextPath
//     var option = {
//         url: contextPath + 'orgnization/listWarehouse',
//         type: "post",
//         data: JSON.stringify(params),
//         contentType: 'application/json',
//         success: function (result) {
//             if (result.code == 0) {
//                 var WarehouseCodes = result.data;
//                 warehouseSignSelect(WarehouseCodes, warehouseSelectId, selectValue);
//             }
//         }
//     }
//     $.ajax(option);
// }

/**
 * 功能描述：渲染单选下拉框
 * @param WarehouseCodes 数据
 * @param warehouseSelectId 下拉框ID
 * @param selectValue  选中的值
 * @returns
 */
function warehouseSignSelect(WarehouseCodes, warehouseSelectId, selectValue) {
    if (WarehouseCodes.wareHoseList.length > 0) {
        $("#" + warehouseSelectId).attr("lay-filter", "warehouseFilter");
        //warehouseSignLisenter("warehouseFilter",warehouseSelectId);
        var currwareCode;
        var wareHoseList = WarehouseCodes.wareHoseList;
        if ($("#" + warehouseSelectId).find("option").length == 1) {
            for (var i = 0; i < wareHoseList.length; i++) {
                if (WarehouseCodes.currWareHose == wareHoseList[i].storageLocation) {
                    currwareCode = wareHoseList[i].orgCode;
                }
                $("#" + warehouseSelectId).append("<option value=" + wareHoseList[i].orgCode + " storagelocation=" + wareHoseList[i].storageLocation + " orgcode=" + wareHoseList[i].orgCode + " >" + wareHoseList[i].orgName + "</option>");
            }
            /*if(null == selectValue || selectValue==''){
                selectValue = currwareCode;
            }*/
            $("#" + warehouseSelectId).val(selectValue);
            layui.form.render("select");
        }

    }
}


// /**
//  *  功能描述：多选下拉仓库
//  *  permission  1-权限控制 0-不控制
//  * @param warehouseSelectId 下拉框ID
//  * @param xmselectId  多选的xmselect参数ID
//  * @param selectValues 多选中的值
//  * @param permission  1-权限控制 0-不控制
//  * @returns
//  */
// function initWarehouseMultiSelect(warehouseSelectId, xmselectId, permission, selectValues) {
//     var params = {limit: 0, sortColums: "org_code asc", permission: permission};
//     var contextPath = config.basicInfoConfig.contextPath;
//     var option = {
//         url: contextPath + 'orgnization/listWarehouse',
//         type: "post",
//         contentType: "application/json",
//         dataType: 'json',
//         data: JSON.stringify(params),
//         success: function (result) {
//             if (result.code == 0) {
//                 var WarehouseCodes = result.data;
//                 warehouseMultiSelect(WarehouseCodes, warehouseSelectId, xmselectId, selectValues);
//             }
//         }
//     }
//     $.ajax(option);
// }

/**
 * 功能描述：渲染多选下拉框
 * @param WarehouseCodes  数据
 * @param warehouseSelectId   下拉框ID
 * @param xmselectId  多选的xmselect参数ID
 * @param selectValues 多选中的值 数据结构如["00500100","00500200"]
 * @returns
 */
function warehouseMultiSelect(WarehouseCodes, warehouseSelectId, xmselectId, selectValues) {
    if (WarehouseCodes.wareHoseList.length > 0) {
        //warehouseMultiLisenter(xmselectId);
        var optoinArray = new Array();
        var curWarehouse = WarehouseCodes.currWareHose;
        var wareHoseList = WarehouseCodes.wareHoseList;
        if (!selectValues) {
            selectValues = new Array();
        }
        for (var i = 0; i < wareHoseList.length; i++) {
            var optionItem = {
                "name": "" + wareHoseList[i].orgName,
                "value": "" + wareHoseList[i].orgCode,
                "storagelocation": "" + wareHoseList[i].storageLocation,
                "sapStoragelocation": wareHoseList[i].sapStorageLocation,
                "factory": wareHoseList[i].factory
            };
            optoinArray.push(optionItem);
            /*if(wareHoseList[i].storageLocation==curWarehouse){
                selectValues.push(wareHoseList[i].orgCode);
            }*/
        }
        if ($("#" + warehouseSelectId).find("option").length == 1) {
            layui.formSelects.data(xmselectId, 'local', {
                arr: optoinArray
            });
        }
        layui.form.render("select");
    }
}

/**
 *  功能描述：库存地点多选下拉框
 *  permission  1-权限控制 0-不控制
 * @param storageLocationSelectId 下拉框ID
 * @param xmselectId  多选的xmselect参数ID
 * @param permission  1-权限控制 0-不控制
 * @param selectValues 多选中的值
 * @returns
 */
function initStorageLocationMultiSelect(storageLocationSelectId, xmselectId, permission, selectValues) {
    var params = {limit: 0, sortColums: "org_code asc", permission: permission};
    var contextPath = config.basicInfoConfig.contextPath;
    var option = {
        url: contextPath + 'orgnization/listWarehouse',
        type: "post",
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(params),
        success: function (result) {
            if (result.code == 0) {
                let storageLocationCodes = result.data;
                storageLocationMultiSelect(storageLocationCodes, storageLocationSelectId, xmselectId, selectValues);
            }
        }
    }
    $.ajax(option);
}




/**
 * 功能描述：渲染多选下拉框
 * @param storageLocationCodes  数据
 * @param storageLocationSelectId   下拉框ID
 * @param xmselectId  多选的xmselect参数ID
 * @param selectValues 多选中的值 数据结构如["00500100","00500200"]
 * @returns
 */
function storageLocationMultiSelect(storageLocationCodes, storageLocationSelectId, xmselectId, selectValues) {
    if (storageLocationCodes.wareHoseList.length > 0) {
        //warehouseMultiLisenter(xmselectId);
        var optoinArray = new Array();
        var curWarehouse = storageLocationCodes.currWareHose;
        var wareHoseList = storageLocationCodes.wareHoseList;
        if (!selectValues) {
            selectValues = new Array();
        }
        for (var i = 0; i < wareHoseList.length; i++) {
            var optionItem = {
                "name": "" + wareHoseList[i].storageLocation,
                "value": "" + wareHoseList[i].storageLocation,
                "factory": wareHoseList[i].factory
            };
            optoinArray.push(optionItem);
            /*if(wareHoseList[i].storageLocation==curWarehouse){
                selectValues.push(wareHoseList[i].orgCode);
            }*/
        }
        if ($("#" + storageLocationSelectId).find("option").length == 1) {
            layui.formSelects.data(xmselectId, 'local', {
                arr: optoinArray
            });
        }
        layui.form.render("select");
    }
}


/**.
 *
 * @param parentFilter:监听对象的filter
 * @param selectIds:需要改变的元素select对应id集合
 * @param cols:需要将select展示的字段名
 * @param defualtVales:select缺省值
 * 给多选增加监听属性
 */
function linkageSelect(parentFilter, selectIds, cols, defualtVales) {
    layui.formSelects.on(parentFilter, function (id, vals, val, isAdd, isDisabled) {
        //id:           点击select的id
        //vals:         当前select已选中的值
        //val:          当前select点击的值
        //isAdd:        当前操作选中or取消
        //isDisabled:   当前选项是否是disabled

        var defualWarehouse;
        if (isAdd) {//选中
            vals.push(val);
        } else {//取消选中
            if (vals.length > 1) {
                for (var i = 0; i < vals.length; i++) {
                    if (vals[i].name == val.name) {
                        vals.splice(i, 1);
                        break;
                    }
                }

            }
        }
        if (vals && vals.length > 0) {
            var optoinArray;
            var optionItem;
            var tempValue;
            var objs;
            for (var j = 0; j < selectIds.length; j++) {
                optoinArray = new Array();
                for (var i = 0; i < vals.length; i++) {
                    tempValue = vals[i][cols[j]];
                    objs = tempValue.split(";");
                    for (var k = 0; k < objs.length; k++) {
                        optionItem = {"name": "" + objs[k], "value": "" + objs[k]};
                        optoinArray.push(optionItem);
                    }
                }
                layui.formSelects.data(selectIds[j], 'local', {
                    arr: optoinArray
                });
                layui.formSelects.value(selectIds[j], defualtVales[j], true);
            }
        }

        //如果return false, 那么将取消本次操作
        return true;
    });
}

/**.
 * 单选监听
 * @param filter
 * @param warehouseSelectId
 */
function warehouseSignLisenter(filter, warehouseSelectId) {
    layui.form.on('select(' + filter + ')', function (data) {

        var obj = $("#" + warehouseSelectId).find("option:selected");
        chooseWarehose(obj.attr("orgCode"), obj.text(), obj.attr("storagelocation"));
    });
}

/**.
 *    多选监听
 * @param filter
 */
function warehouseMultiLisenter(filter) {

    layui.formSelects.on(filter, function (id, vals, val, isAdd, isDisabled) {
        //id:           点击select的id
        //vals:         当前select已选中的值
        //val:          当前select点击的值
        //isAdd:        当前操作选中or取消
        //isDisabled:   当前选项是否是disabled
        var defualWarehouse;
        if (isAdd) {//选中
            if (vals.length > 0) {
                defualWarehouse = vals[0];
            } else {
                defualWarehouse = val;
            }
        } else {//取消选中
            if (vals.length > 1) {

                if (vals[0].name == val.name) {
                    defualWarehouse = vals[1];
                } else {
                    defualWarehouse = vals[0]
                }
            }

        }
        if (defualWarehouse) {
            chooseWarehose(defualWarehouse.value, defualWarehouse.name, defualWarehouse.storagelocation);
        }
        //如果return false, 那么将取消本次操作
        return true;
    });
}

/**.
 * 选中仓库
 * @param orgCode
 * @param orgName
 * @param storageLocation
 */
function chooseWarehose(orgCode, orgName, storageLocation) {
    var contextPath = config.basicInfoConfig.contextPath
    var option = {
        url: contextPath + 'orgnization/chooseWarehouse',
        type: "post",
        data: JSON.stringify({storageLocation: storageLocation, orgName: orgName, orgCode: orgCode}),
        contentType: 'application/json',
        dateType: "json",
        success: function (res) {
            if (res.code == 0) {
                $("#orgName").html(orgName);
            }
        }
    };
    $.ajax(option);
}


/**
 *  初始化仓库下拉框（多选）
 *  permission  1-权限控制 0-不控制
 * @param warehouseSelectId 下拉框ID
 * @param xmselectId  多选的xmselect参数ID
 * @param selectValues 多选中的值
 * @param permission  1-权限控制 0-不控制
 * @returns
 */
function initWarehouseMultiSelect(warehouseSelectId, xmselectId, permission, selectValues) {
    var params = {};
    var contextPath = config.basicInfoConfig.contextPath;
    var option = {
        url: contextPath + 'warehouse/initWarehouseSelect',
        type: "post",
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(params),
        success: function (result) {
            if (result.code == 0) {
                var warehouseList = result.data;
                if (warehouseList.length) {
                    var optoinArray = new Array();
                    if (!selectValues) {
                        selectValues = new Array();
                    }
                    for (var i = 0; i < warehouseList.length; i++) {
                        var optionItem = {
                            "name": "" + warehouseList[i].warehouseName,
                            "value": "" + warehouseList[i].warehouseCode
                        };
                        optoinArray.push(optionItem);
                    }
                    if ($("#" + warehouseSelectId).find("option").length == 1) {
                        layui.formSelects.data(xmselectId, 'local', {
                            arr: optoinArray
                        });
                    }
                    layui.form.render("select");
                }
            }
        }
    }
    $.ajax(option);
}

/**
 * 初始化仓库下拉框（单选）
 * @param warehouseSelectId 下拉框ID
 * @param permission 1-权限控制 0-不控制
 * @param selectValue  选中的值
 * @returns
 */
function initWarehouseSignSelect(warehouseSelectId, permission, selectValue) {
    var params = {};
    var contextPath = config.basicInfoConfig.contextPath
    var option = {
        url: contextPath + 'warehouse/initWarehouseSelect',
        type: "post",
        data: JSON.stringify(params),
        contentType: 'application/json',
        success: function (result) {
            if (result.code == 0) {
                var warehouseList = result.data;
                if (warehouseList.length > 0) {
                    $("#" + warehouseSelectId).attr("lay-filter", "warehouseFilter");
                    if ($("#" + warehouseSelectId).find("option").length == 1) {
                        for (var i = 0; i < warehouseList.length; i++) {
                            $("#" + warehouseSelectId).append("<option value=" + warehouseList[i].warehouseCode + " orgcode=" + warehouseList[i].warehouseCode + " >" + warehouseList[i].warehouseName + "</option>");
                        }
                        $("#" + warehouseSelectId).val(selectValue);
                        layui.form.render("select");
                    }
                }
            }
        }
    }
    $.ajax(option);
}
