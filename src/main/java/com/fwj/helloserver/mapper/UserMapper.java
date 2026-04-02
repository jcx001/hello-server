package com.fwj.helloserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fwj.helloserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}