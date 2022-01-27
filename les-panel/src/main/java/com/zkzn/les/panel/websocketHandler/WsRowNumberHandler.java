package com.zkzn.les.panel.websocketHandler;

import com.zkzn.les.panel.config.WsSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/**
 * @ClassName WsRowNumberHandler
 * @Description 排号handler 消息处理类
 * @Author zhanglei
 * Date 2021/1/6 15:44
 * @Version 1.0
 **/
@Component
public class WsRowNumberHandler extends TextWebSocketHandler {

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private Logger LOGGER = LoggerFactory.getLogger(WsRowNumberHandler.class);
    /**
     * 连接简历，触发
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        //连接建立，获取session，将session 保存缓存
        Object token = session.getId();
        if (token == null) {
            // 服务台推送数据，必须需要session,否则无法推送数据
            LOGGER.info("websocket session  id is null");
            throw new Exception("websocket session 获取失败");
        }
        WsSessionManager.addSession(token.toString(),session);
    }

    /**
     * 接受消息触发
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        super.handleTextMessage(session, message);
        session.sendMessage(new TextMessage("服务端接受到客户端的数据:"+message.getPayload()));
    }

    /**
     * 连接关闭触发
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        super.afterConnectionClosed(session, status);
       //用户退出，删除缓存
        Object token = session.getId();
        if (token != null) {
            WsSessionManager.removeSession(token.toString());
            LOGGER.info("websocket 连接关闭，session 缓存清楚");
        }
    }
}