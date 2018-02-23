package com.xbb.websocket.conf;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by xuebinmeng on 2017/12/10.
 */
@EnableWebSocketMessageBroker                                  //启用websocket的消息中间件
@Configuration                                                 //启用配置
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        super.configureMessageBroker(registry);
//        registry.addHandler(myHandler(), "/myHandler").addInterceptors(new WebSocketInterceptor());
        registry.enableSimpleBroker("/topic"); //接收所有需要返回给client的以/topic开头的前缀消息
        registry.setApplicationDestinationPrefixes("/app");     //绑定MessageMapping的/app的消息前缀
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/hello").withSockJS(); //注册endpoint 和 启用SockJS来访问endpoint  client需要使用SockJS来连接这个endpoint。
    }
}
