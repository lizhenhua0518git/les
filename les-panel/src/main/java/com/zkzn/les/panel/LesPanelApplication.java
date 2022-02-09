package com.zkzn.les.panel;

//import com.zkzn.les.panel.listener.RnPanelListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
