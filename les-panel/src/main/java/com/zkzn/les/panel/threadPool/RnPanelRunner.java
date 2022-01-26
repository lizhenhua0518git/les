package com.zkzn.les.panel.threadPool;
import com.zkzn.les.panel.config.WsSessionManager;
import com.zkzn.les.panel.util.RabbitMqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * @ClassName RnPanelRunner
 * @Description 大屏显示任务，负责消费rabbitMq中的消息 推送到前端
 * @Author zhanglei
 * Date 2021/1/7 15:59
 * @Version 1.0
 **/
public class RnPanelRunner extends Thread {

    private Logger LOGGER = LoggerFactory.getLogger(RnPanelRunner.class);

    /**
     * 监听队列 TODO
     *
     * @param webSocketSession
     */
    private static final String QUEUE = "rnPanelQueue";

    /**
     * 一次拉取的数据 ,这个参数根据实际业务场景的数据量修改
     */
    private static final int COUNT = 50;

    public RnPanelRunner() {
    }

    /**
     * 单线程启动监听队列，接受消息推送到消费线程
     */
    @Override
    public void run() {
        //监听rabbitMq
        try {
            //获取到消息后 读取SessionManager 中的信息，推送到所有的websocket
//            Map<String, WebSocketSession> allCacheSession = WsSessionManager.getAllCacheSession();
//            if (allCacheSession.values().isEmpty()) {
//                //如果是空则说明 没有websocket建立了连接 抛出异常 线程中断
//                throw new Exception("websocket 未建立连接");
//            }
            for (; ; ) {
                List<String> rabbitMqResult = RabbitMqUtil.INSTANCE.receiveMessages(QUEUE, COUNT);
                if (rabbitMqResult.isEmpty()) {
                    TimeUnit.SECONDS.sleep(10L);
                    continue;
                }
                try {
                    for (WebSocketSession webSocketSession : WsSessionManager.getAllCacheSession().values()) {
                        ProcessThreadPool.init().submit(new RnPanelConsumer(rabbitMqResult, webSocketSession));
                    }
                } catch (Exception e) {
                    LOGGER.error("排号大屏数据处理异常：{}",e.getMessage(),e);
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error("线程中断异常：{}",e.getMessage(),e);
        } catch (Exception exception) {
            LOGGER.error("rabbitMq 读取信息异常:{}", exception.getMessage(), exception);
        }
    }
}