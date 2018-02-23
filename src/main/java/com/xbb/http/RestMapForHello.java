package com.xbb.http;

import com.xbb.Bean.HttpMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class RestMapForHello {

//    private static BlockingDeque<HttpServletResponse> deque = new LinkedBlockingDeque();
    private static Queue<HttpMessage> queue = new LinkedList<>();

    private static ConcurrentHashMap<String, HttpMessage> concurrentHashMap = new ConcurrentHashMap<>();


    @RequestMapping("/hello")
    public void setHello(String id, HttpServletResponse response) {
        System.out.println("this requestId is "+id);
        if (concurrentHashMap.get(id) == null) {
            HttpMessage message =  new HttpMessage(response,id);
            concurrentHashMap.put(id,message);
            queue.add(message);
        } else
            return;
        System.out.println("set hello success !");
    }

    @RequestMapping("/write")
    public void getHello(String id) throws IOException {
        HttpMessage message;
        if (id == null) {
            message = queue.poll();
            if (message!=null)
                message = getAndRemoveMapSafety(message.getRequestId());
        } else {
            message = getAndRemoveMapSafety(id);
        }
        if (message == null) return;
        System.out.println("write message to client !");
        HttpServletResponse response = message.getResponse();
/*        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000/hello?id="+message.getRequestId());
//        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");*/
        System.out.println("write over! requestId - "+ message.getRequestId());
        PrintWriter writer = response.getWriter();
//        message.setMessage(message.getRequestId());
        writer.print("write hello! "+ message.getRequestId());
    }

    @Scheduled(cron = "*/60 * * * * ?") // 5秒发一次
    public void timeSendHello() throws IOException {
        System.out.println("scheduled time : " + LocalTime.now());
        getHello(null); // 异步返回需要浏览器长链接实现返回
    }

    private synchronized HttpMessage getAndRemoveMapSafety(String id) {
        HttpMessage response = concurrentHashMap.get(id);
        if (response == null) return null;
        System.out.println("this requestId removed is "+id);
        concurrentHashMap.remove(id);
        return response;
    }

}
