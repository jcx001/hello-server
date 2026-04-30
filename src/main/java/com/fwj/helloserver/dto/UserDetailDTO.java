package com.fwj.helloserver.dto;

import lombok.Data;

@Data
public class UserDetailDTO {
    private Long userId;
    private String username;
    private String realName;
    private String phone;
    private String address;
}