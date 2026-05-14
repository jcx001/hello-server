package com.fwj.helloserver.controller;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.dto.UserDetailDTO;
import com.fwj.helloserver.entity.User;
import com.fwj.helloserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 登录
    @PostMapping("/login")
    public Result<String> login(@RequestParam String username,
                                @RequestParam String password) {
        return userService.login(username, password);
    }

    // 注册
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        return userService.register(user);
    }

    // 查询用户详情
    @GetMapping("/{userId}")
    public Result<UserDetailDTO> getUserDetail(@PathVariable Long userId) {
        return userService.getUserDetail(userId);
    }

    // 更新用户信息
    @PutMapping
    public Result<String> updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    // 删除用户
    @DeleteMapping("/{userId}")
    public Result<String> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}