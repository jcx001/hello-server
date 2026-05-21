//package com.fwj.helloserver.service.impl;
//
//import com.fwj.helloserver.service.ChatService;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ChatServiceImpl implements ChatService {
//
//    private final ChatClient chatClient;
//
//    public ChatServiceImpl(ChatClient.Builder chatClientBuilder) {
//        this.chatClient = chatClientBuilder.build();
//    }
//
//    @Override
//    public String chat(String message) {
//        return chatClient.prompt(message).call().content();
//    }
//}