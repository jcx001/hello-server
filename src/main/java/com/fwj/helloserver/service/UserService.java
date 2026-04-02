package com.fwj.helloserver.service;

import com.fwj.helloserver.dto.UserDTO;
import com.fwj.helloserver.common.Result;

public interface UserService {
    Result<String> register(UserDTO userDTO);
    Result<String> login(UserDTO userDTO);
    Result<String> getUserById(Long id);
}