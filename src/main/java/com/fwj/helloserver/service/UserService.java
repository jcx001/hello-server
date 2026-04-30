package com.fwj.helloserver.service;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.dto.UserDetailDTO;
import com.fwj.helloserver.entity.User;

public interface UserService {
    Result<UserDetailDTO> getUserDetail(Long userId);
    Result<String> updateUserInfo(User user);
    Result<String> deleteUser(Long userId);
}