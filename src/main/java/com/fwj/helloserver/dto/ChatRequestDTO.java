package com.fwj.helloserver.dto;

import lombok.Data;

@Data
// 原来的 public class ChatRequest {
public class ChatRequestDTO {
    private String message;
    private String sessionId;
}