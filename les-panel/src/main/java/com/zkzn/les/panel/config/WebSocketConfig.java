package com.zkzn.les.panel.config;

import com.zkzn.les.panel.websocketHandler.WsRowNumberHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @ClassName WebSocketConfig
 * @Description websocket 配置类
 * @Author zhanglei
 * Date 2021/1/6 14:49
 * @Version 1.0
 **/

@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WsRowNumberHandler wsHandler;

    @Autowired
    private WebSocketInterceptor webSocketInterceptor;

    /**
     *
     * @param webSocketHandlerRegistry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                //设置handler
                .addHandler(wsHandler,"rowNumberPanel")
                .addInterceptors(webSocketInterceptor)
                //设置跨域请求，关闭
                .setAllowedOrigins("*");
    }
}