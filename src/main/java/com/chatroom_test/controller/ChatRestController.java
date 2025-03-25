package com.chatroom_test.controller;

import com.chatroom_test.entity.ChatMessage;
import com.chatroom_test.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatRestController {

    private final ChatService chatService;

    @Autowired
    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/history")
    public List<ChatMessage> getHistory(@RequestParam String sender, @RequestParam String receiver) {
        return chatService.getMessages(sender, receiver);
    }
}
