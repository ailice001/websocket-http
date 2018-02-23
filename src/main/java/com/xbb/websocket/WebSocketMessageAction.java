package com.xbb.websocket;


/**
 * Created by xuebinmeng on 2017/12/11.
 */
import com.xbb.websocket.others.StompMessageHandshakeHandler;
import com.xbb.websocket.others.WebSocketHandshakeInterceptor;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


@EnableWebSocketMessageBroker
@Controller
public class WebSocketMessageAction extends AbstractWebSocketMessageBrokerConfigurer{

    /**
     * 将"/webSocket"路径注册为STOMP端点，这个路径与发送和接收消息的目的路径有所不同，
     * 这是一个端点，客户端在订阅或发布消息到目的地址前，要连接该端点，
     * 即用户发送请求url="/applicationName/webSocket"与STOMP server进行连接。之后再转发到订阅url；
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册websocket，客户端用ws://host:port/项目名/webSocket
        registry.addEndpoint("/webSocket")  // 定义ws协议的访问地址
                .setHandshakeHandler(new StompMessageHandshakeHandler())
                .addInterceptors(new WebSocketHandshakeInterceptor())
                .withSockJS();//表示支持以SockJS方式连接服务器
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic","/user");//这句话表示在topic和user这两个域上服务端可以向客户端发消息
        registry.setApplicationDestinationPrefixes("/ws");//这句话表示客户端向服务器端发送时的主题上面需要加"/ws"作为前缀
        registry.setUserDestinationPrefix("/user");//这句话表示服务端给客户端指定用户发送一对一的主题，前缀是"/user"
    }
}