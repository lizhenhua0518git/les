package com.zkzn.les.panel.listener;

import com.zkzn.les.panel.config.WsSessionManager;
import com.zkzn.les.panel.threadPool.RnPanelRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName RnPanelListener
 * @Description tomcat启动注册监听器 全局唯一启动rabbitMq消费监听对象
 * @Author zhanglei
 * Date 2021/1/11 11:23
 * @Version 1.0
 **/
//public class RnPanelListener implements ServletContextListener {
//    /**
//     * 容器初始化完成
//     * @param sce
//     */
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        //初始化线程池对象
//
//        //注册spring容器
//        ServletContext servletContext = sce.getServletContext();
//        ApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        SpringContext.setApplicationContext(webApplicationContext);
//        //建立rabbitMq的监听
//        new RnPanelRunner().start();
//    }
//
//    /**
//     * 容器销毁
//     * @param sce
//     */
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        //当容器销毁清除所有session缓存
//        WsSessionManager.removeAllSession();
//    }
//}