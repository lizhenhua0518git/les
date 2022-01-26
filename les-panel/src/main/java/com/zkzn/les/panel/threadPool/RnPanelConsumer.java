package com.zkzn.les.panel.threadPool;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zkzn.les.panel.websocketHandler.RnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 * @ClassName RnPanelConsumer
 * @Description 任务具体的消费者
 * @Author zhanglei
 * Date 2021/1/8 17:06
 * @Version 1.0
 **/
public class RnPanelConsumer implements Runnable{

    private final Logger LOGGER = LoggerFactory.getLogger(RnPanelConsumer.class);

    private List<String> rabbitMqResult;

    private WebSocketSession webSocketSession;

    public RnPanelConsumer(List<String> rabbitMqResult,WebSocketSession webSocketSession) {
        this.rabbitMqResult = rabbitMqResult;
        this.webSocketSession = webSocketSession;
    }
    @Override
    public void run() {
        if (!rabbitMqResult.isEmpty()) {
           //解析json，数据处理
           //要求时间的发生时间要在当天
            String dayTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            List<Map<String,Object>> result = Lists.newArrayList();
            JSONArray resultArray = new JSONArray();
            for (String res : rabbitMqResult) {
                //解析时间,时间不是当天的过滤
                try {
                    JSONObject json = JSONObject.parseObject(res);
                    String processTime = (String) json.get("processTime");
                    int busiType = (Integer) json.get("busiType");
                    if (dayTime.equals(processTime) && busiType == 1) {
                        resultArray.add(json);
                    }
                } catch (Exception exception) {
                        LOGGER.error("大屏排号信息处理失败:{}", exception.getMessage(),exception);
                }
            }
            if (!resultArray.isEmpty()) {
                try {
                    String s = JSONArray.toJSONString(new RnMessage(resultArray),SerializerFeature.WriteNullStringAsEmpty);
                    webSocketSession.sendMessage(new TextMessage(s));
                } catch (IOException e) {
                    LOGGER.error("websocket 消息推送错误：[{}]",e.getMessage(),e);
                }
            }
        }
    }
}