package com.fwj.helloserver.service.impl;

import com.fwj.helloserver.service.ChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Override
    public String chat(String message) {
        return "你好！我是智能助手，很高兴为你服务～";
    }
}