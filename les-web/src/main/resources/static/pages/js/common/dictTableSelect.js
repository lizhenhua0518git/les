layui.config({
        base: '/iles/pages/layui/lay/modules/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });

function queryDictList(permission,selectValue,queryValue){
    var contextPath = config.basicInfoConfig.contextPath;

    //查询字典
    var option={
        url:contextPath+'dict/listDict',
        type:'POST',
        data:{dictType:queryValue},
        dataType:'json',

        success:function(res){
            if(res.code==0){
                dictItems = res.data
                defulatSelect(res.data,permission,selectValue);
            }
        }
    };
    $.ajax(option);
}

function defulatSelect(dictList,permission,selectValue){
    if(dictList && dictList.length>0){
        for(var i=0;i<dictList.length;i++){

            $("#"+permission).append("<option  value="+dictList[i].id+">"+dictList[i].dictName+"</option>");

        }
        if(selectValue !==''){
            $("#"+permission).val(selectValue);
        }
        layui.form.render("select");
    }
}




