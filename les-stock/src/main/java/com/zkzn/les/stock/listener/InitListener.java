package com.zkzn.les.stock.listener;

import com.zkzn.les.stock.thread.RequestProcessThreadPool;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName InitListeren
 * @Description 初始化服务监听
 * @Author zhanglei
 * Date 2021/6/30 14:32
 * @Version 1.0
 **/
@Configuration
public class InitListener {

    //初始化变量
    @PostConstruct
    public void postConstruct(){
        RequestProcessThreadPool.init();
    }

}