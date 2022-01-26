if(!layui.formSelects){
    layui.config({
        base: '/iles/pages/layui/lay/modules/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });
    layui.use(['form','table','layer','laydate','formSelects'],function(){
        var formSelects = layui.formSelects;
    });
}

/**
 * 初始化人员信息（只初始化当前用户所属仓库人员信息）
 */
function initUserInfo(permission,selectValue){
    var contextPath = config.uasConfig.contextPath;
    var option = {
        url:contextPath + 'user/initUserInfo',
        type:"GET",
        success:function(result){
            if(result.code==0){
                var documentType=result.data;
                if (documentType.length>0){
                    if (documentType.length>0){
                        for(var i=0;i<documentType.length;i++){
                            $("#"+permission).append("<option value="+documentType[i].userId+">"+documentType[i].userName+"</option>");
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
