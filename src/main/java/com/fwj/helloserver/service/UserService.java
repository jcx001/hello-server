package com.fwj.helloserver.service;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.dto.UserDTO;

public interface UserService {
    Result<String> register(UserDTO userDTO);
    Result<String> login(UserDTO userDTO);
}