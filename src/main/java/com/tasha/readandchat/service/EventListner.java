package com.tasha.readandchat.service;

import com.tasha.readandchat.entity.ChatMessage;
import com.tasha.readandchat.entity.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventListner {
    private final SimpMessageSendingOperations messageOperations;

    public void handleSessionDisconnect(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null){
            log.info("user disconnected: {}", username);
            messageOperations.convertAndSend(
                    "/topic/chat",
                    ChatMessage.builder()
                            .sender(username)
                            .type(MessageType.LEAVE)
                            .build()
            );
        }
    }
}
