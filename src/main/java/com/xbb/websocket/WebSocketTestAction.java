package com.xbb.websocket;


/**
 * Created by xuebinmeng on 2017/12/11.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class WebSocketTestAction {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/webSocket/testWithServer")   //与页面的stompClient.send("/ws/webSocket/testWithServer"
    public String send(String message,@Header("name")String name,
                       @Headers Map<String, Object> headers){
        System.out.println(message);
        System.out.println(name);
        System.out.println(headers);
        simpMessagingTemplate.convertAndSend("/user/1/testUser","服务端返回消息");// 发布消息
        return "hello";
    }
}