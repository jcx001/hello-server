package com.fwj.helloserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fwj.helloserver.dto.UserDetailDTO;
import com.fwj.helloserver.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("""
            SELECT
                u.id AS userId,
                u.username,
                i.real_name AS realName,
                i.phone,
                i.address
            FROM sys_user u
            LEFT JOIN user_info i ON u.id = i.user_id
            WHERE u.id = #{userId}
            """)
    UserDetailDTO getUserDetail(@Param("userId") Long userId);
}