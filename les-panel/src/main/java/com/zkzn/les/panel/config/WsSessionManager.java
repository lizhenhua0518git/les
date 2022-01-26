package com.zkzn.les.panel.config;

import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName WsSessionManager
 * @Description websocket session 管理类
 * @Author zhanglei
 * Date 2021/1/6 15:23
 * @Version 1.0
 **/
public class WsSessionManager {
    /**
     * websocketSession 保存
     * session 是websocket 随机生成的
     */
    private static ConcurrentHashMap<String, WebSocketSession> sessionCache = new ConcurrentHashMap<>();

    /**
     * 添加websocketSession
     * @param cacheKey
     * @param webSocketSession
     */
    public static void addSession(String cacheKey,WebSocketSession webSocketSession){
        sessionCache.put(cacheKey,webSocketSession);
    }

    /**
     * 删除sessin缓存
     * @param cacheKey
     * @return
     */
    public static WebSocketSession removeSession(String cacheKey) {
       return sessionCache.remove(cacheKey);
    }

    /**
     * 删除缓存key，并关闭连接
     * @param cacheKey
     */
    public static void removeAndClose(String cacheKey){
        WebSocketSession webSocketSession = removeSession(cacheKey);
        if (webSocketSession != null ) {
            try {
                webSocketSession.close();
            } catch (IOException e) {
               //TODO 错误日志记录
            }
        }
    }

    /**
     * 缓存session 获取
     * @param cacheKey
     * @return
     */
    public static WebSocketSession getSeesion(String cacheKey){
        //获取缓存session
        return  sessionCache.get(cacheKey);
    }

    /**
     * 获取所用的session
     * @return
     */
    public static Map<String,WebSocketSession> getAllCacheSession(){
        return sessionCache;
    }
    /**
     * 清楚所有的缓存
     */
    public static void removeAllSession() {
        sessionCache.clear();
    }
}