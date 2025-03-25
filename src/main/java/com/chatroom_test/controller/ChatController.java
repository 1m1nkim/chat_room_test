package com.chatroom_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(ChatMessage message) {
        // 여기서 필요한 로직 처리 (예: DB 저장, Redis 캐싱 등)
        messagingTemplate.convertAndSendToUser(message.getReceiverId(), "/queue/messages", message);
    }
}
