package com.stackroute.chatservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String messageId = UUID.randomUUID().toString();;
    private String senderId;
    private String receiverId;
    private String content;
    private String timestamp;
}
