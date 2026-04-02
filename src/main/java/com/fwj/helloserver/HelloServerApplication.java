package com.fwj.helloserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fwj.helloserver.mapper")
public class HelloServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloServerApplication.class, args);
    }
}