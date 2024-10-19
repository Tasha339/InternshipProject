package com.tasha.readandchat.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ChatMessage {

    private String sender;
    private String content;
    private MessageType type;
}
