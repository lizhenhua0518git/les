var $ = layui.$;
var element = layui.element;
var contextPath = config.uasConfig.contextPath;
var layer = layui.layer;

function loadding() {
    //加载菜单栏
    listMenus();
    //添加标签栏
    $(".layui-nav a").click(function () {
        var obj = $(this);
        var id = obj.attr("id");
        var url = obj.attr("url");
        var title = obj.html();
        if ("null" == url || "" == url || "undefined" == url) {
            return;
        }
        var currHeight = $(document).height();
        var contentHeight = currHeight - 260;
        element.tabDelete('indexTab', id);
        element.tabAdd('indexTab', {
            title: title //用于演示
            ,
            content: ' <iframe id="' + id + '" frameborder="0"  src="' + url + '" width="100%" height="' + contentHeight + 'px"></iframe>'
            ,
            id: id //实际使用一般是规定好的id，这里以时间戳模拟下
        });
        customRightClick();//绑定右键菜单
        element.tabChange('indexTab', id); //切换到：用户管理
        $(".nav-child-3").removeClass('layui-this');
    });
    //退出方法
    $("#logoutDiv").click(function () {
        $.ajax({
            url: contextPath + 'simpleLogout'
            , async: false
            , type: "GET"
            , dataType: 'json'
            , success: function (result) {
                if (result.code == 0||result.code == 401) {
                    var url = "/iles/pages/login.html";
                    window.location.href = url;
                    window.localStorage.removeItem("token");
                    window.event.returnValue = false;
                }
            }
        });
    });

    //右键功能开始
    $(".rightmenu li").on('click', function () {
        if ($(this).attr("data-type") == "closeothers") {
            var currentId = $(this).attr("data-id")
            var tabtitle = $(".layui-tab-title li");
            $.each(tabtitle, function (i) {
                if ($(this).attr("lay-id") != currentId) {
                    element.tabDelete("indexTab", $(this).attr("lay-id"));
                }
            })
        } else if ($(this).attr("data-type") == "closeall") {
            var tabtitle = $(".layui-tab-title li");
            var ids = new Array();
            $.each(tabtitle, function (i) {
                element.tabDelete("indexTab", $(this).attr("lay-id"));
            })
        } else if ($(this).attr("data-type") == "refreshFrame") {
            var iframe_id = $(this).attr("data-id");
			$('iframe#'+iframe_id).attr('src', $('iframe#'+iframe_id).attr('src'));
		}
        $('.rightmenu').hide();
    });
}

function listMenus() {
    $.ajax({
        url: contextPath + 'resource/getResoureList'
        , dataType: "json"
        , type: "GET"
        , async: false
        , success: addMenu
    });
}

function addMenu(result) {
    if (result.code == 0) {
        var data = $.parseJSON(result.data);
        var title;
        var html = '';
        $.each(data, function (i, item) {
            title = item.title;
            html = html + '<li class="layui-nav-item"><a href="javascript:;" id="' + item.id + '" url="' + item.url + '">'
                + title + '</a>  ';
            if (item.children !== undefined && item.children.length > 0) {

                html = html + '<div class="layui-nav-child">'
                $.each(item.children, function (j, item2) {
                    if (j != 0) {
                        html = html + '</div>'
                    }
                    html = html + '<div class="nav-child-2"><a href="javascript:;" id="' + item2.id + '" url="' + item2.url + '">'
                        + item2.title + '</a>  ';
                    if (item2.children !== undefined && item2.children.length > 0) {
                        $.each(item2.children, function (k, item3) {
                            if (k == 0) {
                                html = html + '<div class="nav-child-3">'
                            }
                            html = html + '<a href="javascript:;" id="' + item3.id + '" url="' + item3.url + '">'
                                + item3.title + '</a>'
                        })
                        html = html + '</div>';
                    }

                })
                html = html + '</div>';
                html = html + '</div>';
            }
            html = html + '</li>'
        });
        html = '<ul class="layui-nav index-nav" >' + html + '</ul>'
        $("#menuUl").append(html);
        var currWidth = $(document).width();
        $(".index-nav .layui-nav-item .layui-nav-child").attr("style", "min-width: " + currWidth + "px;");
        element.render();
    } else {
        layer.msg("获取菜单失败!");
    }
}

//tab右键开始
function customRightClick() {
    $('.layui-tab-title li').on('contextmenu', function () {
        return false;
    });
    $('.layui-tab-title,.layui-tab-title li').click(function () {
        $('.rightmenu').hide();
    });
    //桌面点击右击
    $('.layui-tab-title li').on('contextmenu', function (e) {
        var popupmenu = $(".rightmenu");
        popupmenu.find("li").attr("data-id", $(this).attr("lay-id"));
        l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
        t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
        popupmenu.css({left: l, top: t}).show();
        //alert("右键菜单")
        return false;
    });
}

//tab右键结束

//右键功能结束