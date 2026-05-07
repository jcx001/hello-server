package com.fwj.helloserver.service.impl;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.common.ResultCode;
import com.fwj.helloserver.dto.UserDetailDTO;
import com.fwj.helloserver.entity.User;
import com.fwj.helloserver.mapper.UserMapper;
import com.fwj.helloserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // 只保留 Mapper，完全去掉 Redis
    private final UserMapper userMapper;

    @Override
    public Result<UserDetailDTO> getUserDetail(Long userId) {
        // 直接查数据库，不经过任何缓存
        UserDetailDTO detail = userMapper.getUserDetail(userId);
        if (detail == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        return Result.success(detail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> updateUserInfo(User user) {
        if (user == null || user.getId() == null) {
            return Result.error("参数不能为空");
        }

        int rows = userMapper.updateById(user);
        if (rows <= 0) {
            return Result.error("更新失败");
        }

        return Result.success("更新成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> deleteUser(Long userId) {
        int rows = userMapper.deleteById(userId);
        if (rows <= 0) {
            return Result.error("删除失败");
        }

        return Result.success("删除成功");
    }
}