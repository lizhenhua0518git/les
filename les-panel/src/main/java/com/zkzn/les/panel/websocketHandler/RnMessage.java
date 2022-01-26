package com.zkzn.les.panel.websocketHandler;

import lombok.Data;
import org.springframework.web.socket.TextMessage;

import java.io.Serializable;

/**
 * @ClassName RnMessage
 * @Description TODO
 * @Author zhanglei
 * Date 2021/1/8 17:46
 * @Version 1.0
 **/

public class RnMessage  extends WsMessage {
    //序列化id
   // private static final long serialVersionUID = 4359709211352400087L;

    public RnMessage(Object data) {
        this.data = data;
    }

}