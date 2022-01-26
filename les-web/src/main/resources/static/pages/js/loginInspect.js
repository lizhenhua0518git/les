var storage = window.localStorage

var $ = layui.$;
var laytpl = layui.laytpl;
var curHref = location.href;
var obj = findParent(window);

if (!storage.token) {
    obj.location.href = "/iles/pages/login.html"
} else {
    var token = storage.token;
    $.ajaxSetup({ //发送请求前触发
        complete: function (xhr) {
            if (xhr.responseJSON.code == "error_unauth") {
                console.log("没有登录！")
                // location.href="login.html"
            } else {
                console.log("已经登录！")
            }
        },
        beforeSend: function (xhr) { //可以设置自定义标头
            console.log("ajax beforesend token:", 'Bearer ' + token)
            xhr.setRequestHeader("Authorization", 'Bearer ' + token);
        }
    });
    var option = {
        url: 'http://localhost:8070/auth/user/me?curHref=' + curHref,
        //url: 'http://58.135.83.162:13017/auth/user/me?curHref=' + curHref,
        type: 'GET',
        /*headers: {
              'Accept': 'application/json',
              'Authorization': 'Bearer '+token,
            },
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", 'Bearer '+token);
           },*/
        success: function (res) {
            if (res.data == null || res.data.currUser == null) {
                // window.location.href = "/iles/pages/login.html"
                obj.location.href = "/iles/pages/login.html"

            } else {
                var getTpl = bodyTpl.innerHTML
                    , view = document.getElementById('bodyDiv');
                laytpl(getTpl).render(res.data, function (html) {
                    view.innerHTML = html;
                });
                loadding();
            }
        }, error: function (XMLHttpResponse, textStatus, errorThrown) {
            console.log("1 异步调用返回失败,XMLHttpResponse.readyState:" + XMLHttpResponse.readyState);
            console.log("2 异步调用返回失败,XMLHttpResponse.status:" + XMLHttpResponse.status);
            console.log("3 异步调用返回失败,textStatus:" + textStatus);
            console.log("4 异步调用返回失败,errorThrown:" + errorThrown);
            var obj = findParent(window);
            obj.location.href = "/iles/pages/login.html"
        }

    }
    $.ajax(option);
}

function findParent(obj) {
    // if (window != top) {
    let currHref = obj.location.href;
    if (currHref.indexOf("index.html") > 0) {
        return obj;
    } else {
        return findParent(obj.parent);
    }
    // }

}

