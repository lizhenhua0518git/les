var url = "/panel/inventory";
var updateInterval= 15000;


layui.use(['layer', 'jquery','util','element'], function(){
    var layer = layui.layer
        ,util = layui.util
        ,element = layui.element
        ,$ = layui.jquery;

    //获取当前时间
    currentTime();
    var timer = null;
    function currentTime(){
        timer && clearTimeout(timer);
        var time = new Date();
        var current = util.toDateString(time,'yyyy-MM-dd HH:mm:ss');
        $('#currentTime').html(current);
        timer = setTimeout(function () {
            currentTime();
        },1000);
    }
    listInventoryInfo();

});

function listInventoryInfo() {
    $.post(url, function (data) {
        console.log('更新数据');
        console.log(data);
        addData(data.data);
    }, 'json');
}

function addData(data){
    var length =data.length;

    var html ="";
  for(var i =1;i<length+1;i++){
      var item =data[i-1];
      var   tempHtml = ' <div class="layui-col-xs6">' +
          '        <div class="itemStore">' +
          '            <h4 class="title">库存总量：'+item.stockCount+'</h4>' +
          '            <div class="content">' +
          '                <span class="textLabel">'+item.warehouseName+'</span>' +
          '                <div class="layui-progress layui-progress-big">' +
          '                    <div class="layui-progress-bar "  lay-percent="'+item.rate+'" style="width: '+item.rate+'">' +
          '                        <span class="layui-progress-text">'+item.enableCount+'</span>' +
          '                    </div>' +
          '                    <div class="otherBar" style="min-width: '+item.otherRate+';">' +
          '                        <span class="layui-progress-text">'+item.preUseCount+'</span>' +
          '                    </div>' +
          '                </div>' +
          '            </div>' +
          '        </div>' +
          '    </div>';

      html=html+tempHtml;

      if(length>12){

          if(i==12){
              if(html==''){
                  $(".layui-row").html('<div class="itemStore"><h4 class="title">暂无数据</h4></div>');
              }else{
                  $(".layui-row").html(html);
              }
              html="";
          }else{
              if(  i%12==0){
                  setTimeout(function () {
                      $(".layui-row").html(html);

                  },5000);

              }
          }

          if(i==length){

              setTimeout(function () {
                  $(".layui-row").html(html);

              },5000);

          }
      }else{
          if(html==''){
              $(".layui-row").html('<div class="itemStore"><h4 class="title">暂无数据</h4></div>');
          }else{
              $(".layui-row").html(html);
          }
      }


  }



    setInterval(listInventoryInfo, updateInterval);
   
}
