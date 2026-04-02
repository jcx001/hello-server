package com.fwj.helloserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 */
@RestController
public class HelloController {

    /**
     * 访问地址：http://localhost:8081/hello
     */
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Spring Boot! 编译成功啦！🎉";
    }
}