package com.fwj.helloserver.service;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.dto.UserDetailDTO;
import com.fwj.helloserver.entity.User;

public interface UserService {

    // 你原来就有的方法
    Result<UserDetailDTO> getUserDetail(Long userId);
    Result<String> updateUserInfo(User user);
    Result<String> deleteUser(Long userId);

    // 加上这两个声明，两个报错立刻消失
    Result<String> login(String username, String password);
    Result<String> register(User user);
}