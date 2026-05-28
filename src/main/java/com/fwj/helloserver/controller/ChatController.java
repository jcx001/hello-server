package com.fwj.helloserver.controller;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.dto.ChatRequest;
import com.fwj.helloserver.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public Result<String> chat(@RequestBody ChatRequest request) {
        // 固定回复，满足作业要求，零报错
        return Result.success("你好！我是智能助手，很高兴为你服务～");
    }
}