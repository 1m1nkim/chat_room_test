package com.chatroom_test.service;

import com.chatroom_test.entity.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {

    // <채팅방ID, 메시지 목록>
    private final Map<String, List<ChatMessage>> chatRooms = new ConcurrentHashMap<>();

    // 두 사용자 간 채팅방 ID 생성 (sender와 receiver의 알파벳 순으로)
    public String getRoomId(String sender, String receiver) {
        if (sender.compareTo(receiver) < 0) {
            return sender + ":" + receiver;
        } else {
            return receiver + ":" + sender;
        }
    }

    // 메시지 저장
    public void saveMessage(ChatMessage message) {
        String roomId = getRoomId(message.getSender(), message.getReceiver());
        chatRooms.putIfAbsent(roomId, new ArrayList<>());
        chatRooms.get(roomId).add(message);
    }

    // 채팅방 메시지 목록 조회
    public List<ChatMessage> getMessages(String sender, String receiver) {
        String roomId = getRoomId(sender, receiver);
        return chatRooms.getOrDefault(roomId, Collections.emptyList());
    }
}
