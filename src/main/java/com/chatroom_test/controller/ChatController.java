package com.chatroom_test.controller;

import com.chatroom_test.entity.ChatMessage;
import com.chatroom_test.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessage chatMessage) {
        // 메시지 저장
        chatService.saveMessage(chatMessage);

        // 채팅방(topic)에 메시지 방송 (채팅방 ID는 sender, receiver의 알파벳 순으로 구성)
        String roomId = chatService.getRoomId(chatMessage.getSender(), chatMessage.getReceiver());
        messagingTemplate.convertAndSend("/topic/chat/" + roomId, chatMessage);
    }
}
