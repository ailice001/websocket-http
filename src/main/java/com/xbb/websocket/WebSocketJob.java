package com.xbb.websocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuebinmeng on 2017/12/11.
 */
@Component
public class WebSocketJob {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private int count=0;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 6000)   // 6 秒
    public void reportCurrentTime() {
        System.out.println("time ：" + dateFormat.format(new Date()));
        count++;
//        WebSocketMessage msg = new WebSocketMessage();
//        msg.setDistination("/user/1/testUser");
//        msg.setData("test....");
//        WebSocketMessageUtil.addMessage(msg);
        simpMessagingTemplate.convertAndSend("/user/1/testUser","服务端返回消息 + " +count);
    }

}