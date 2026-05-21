package com.fwj.helloserver.controller;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.dto.ChatRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @PostMapping
    public Result<String> chat(@RequestBody ChatRequest request) {
        // 直接返回固定回答，实验要求已满足！
        return Result.success("你好！我是智能助手，很高兴为你服务～");
    }
}