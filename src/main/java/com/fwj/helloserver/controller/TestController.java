package com.fwj.helloserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // 这个接口不在 /api/users/** 里，会被拦截
    @GetMapping("/api/test")
    public String test() {
        return "这个接口应该被拦截，不应该返回给你";
    }
}