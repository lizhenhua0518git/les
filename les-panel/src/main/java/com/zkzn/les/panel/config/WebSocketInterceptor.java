package com.zkzn.les.panel.config;

import com.zkzn.les.panel.util.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WebSocketInterceptor
 * @Description TODO
 * @Author zhanglei
 * Date 2021/1/6 15:00
 * @Version 1.0
 **/
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {
    /***
     * @Discription: 握手前
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param attributes
     * @return boolean
     * @Author: zhanglei on 2021/1/6 15:00
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        //解析请求头
//        Map<String, String> paramMap = HttpUtil.decodeParamMap(serverHttpRequest.getURI().getQuery());
//        String uid = paramMap.get("token");
//        if (StringUtils.isNotBlank(uid)) {
//            // 放入属性域
//            attributes.put("token", uid);
//            return true;
//        }
        return true;
    }

    /***
     * @Discription: 握手后
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param e
     * @return void
     * @Author: zhanglei on 2021/1/6 15:01
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}