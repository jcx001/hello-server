package com.fwj.helloserver.controller;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.dto.UserDTO;
import com.fwj.helloserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    @GetMapping("/{id}")
    public Result<String> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}