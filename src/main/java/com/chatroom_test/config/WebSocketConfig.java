package com.chatroom_test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 접속할 엔드포인트 (SockJS fallback 지원)
        registry.addEndpoint("/ws-chat").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 서버 내 브로커 설정: 간단한 메모리 기반 브로커 사용 (실무에서는 RabbitMQ, Redis 등 고려)
        registry.enableSimpleBroker("/queue", "/topic");
        // 클라이언트에서 메시지를 보낼 때 사용하는 prefix
        registry.setApplicationDestinationPrefixes("/app");
        // 1대1 메시지 전송을 위한 user destination prefix 설정
        registry.setUserDestinationPrefix("/user");
    }
}

