package com.zkzn.les.panel;

//import com.zkzn.les.panel.listener.RnPanelListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRegistration;

@SpringBootApplication
public class LesPanelApplication {

    public static void main(String[] args) {
        SpringApplication.run(LesPanelApplication.class, args);
    }
    /**
     * 注册监听器
     */
//    @Bean
//   public ServletListenerRegistrationBean servletListenerRegistrationBean() {
//       ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
//       servletListenerRegistrationBean.setListener(new RnPanelListener());
//       return servletListenerRegistrationBean;
//   }

}
