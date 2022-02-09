package com.zkzn.les.common.util.rabbitMq;
import com.google.common.collect.Lists;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName RabbitMqUtil
 * @Description rabbitmq 工具枚举类
 * @Author zhanglei
 * Date 2021/1/8 13:51
 * @Version 1.0
 **/
public enum RabbitMqUtil {
    INSTANCE;
    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMqUtil.class);

    //connection
    private  Connection connection = null;

    //初始化
    RabbitMqUtil(){
        try {
            //读取配置
            Properties props = new Properties();
            props.load(RabbitMqUtil.class.getClassLoader().getResourceAsStream("application.properties"));
            ConnectionFactory factory= new ConnectionFactory();
            factory.setHost(props.getProperty("rabbitMq.hosts"));
            factory.setPort(Integer.valueOf(props.getProperty("rabbitMq.port")));
            factory.setUsername(props.getProperty("rabbitMq.userName"));
            factory.setPassword(props.getProperty("rabbitMq.password"));
            factory.setVirtualHost(props.getProperty("rabbitMq.virtualHost"));
            factory.setConnectionTimeout(Integer.valueOf(props.getProperty("rabbitMq.connectionTimeOut")));
            connection = factory.newConnection();

        }catch (IOException e){
            LOGGER.error("rabbitmq配置文件读取错误".concat(e.getMessage()));
        }catch (TimeoutException timeoutException){
            LOGGER.error("rabbitMq 连接超时异常".concat(timeoutException.getMessage()));
        }
    }

    /**
     * 向指定的消息队列取出固定数量的数据
     *
     * @param queue 消息队列名
     * @param count 取出的消息数量
     * @return
     */
    public  List<String> receiveMessages(String queue, int count) throws Exception {
        List<String> list = Lists.newLinkedList();
        Channel channel = null;
        try {
            // 从队列中获取连接
            // 创建一个管道
            if (connection == null) {
                throw new Exception("rabbitMq 连接异常");
            }
              channel = connection.createChannel();
            // 将管道绑定到队列上
            channel.queueDeclare(queue, true, false, false, null);
            // 向指定的队列中获取数据，通过循环，每次循环获取一条数据，总共循环count次
            QueueingConsumer consumer = new QueueingConsumer(channel);

            channel.basicConsume(queue, true, consumer);
            for (int i = 0; i < count; i++) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery(300);
                if (delivery == null) {
                    break;
                }
                String message = new String(delivery.getBody());
                list.add(message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
            //关闭通道
        } catch (Exception e) {
            LOGGER.error("RabbitMQ connection fail, receive message fail!", e);
        } finally {
             if (channel != null) channel.close();
            return list;
        }
    }


    /**
     * 向指定的消息队列发送消息
     *
     * @param message 消息体
     * @param queue   队列名
     */
    public  void sendMessage(String message, String queue)  throws Exception{
        try {
            // 创建一个MQ的管道
            Channel channel = connection.createChannel();
            // 将管道绑定到一个队列上
            channel.queueDeclare(queue, true, false, false, null);
            // 向指定的队列发送消息
            channel.basicPublish("", queue, null, message.getBytes("UTF-8"));
            // 关闭管道
            channel.close();
        } catch (Exception e) {
            LOGGER.error("rabbitMq 异常 ：{}",e.getMessage());
            throw  new Exception("rabbitMq 信息发送异常");
        }
    }
}
