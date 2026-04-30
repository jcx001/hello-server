package com.fwj.helloserver.service.impl;

import cn.hutool.json.JSONUtil;
import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.common.ResultCode;
import com.fwj.helloserver.dto.UserDetailDTO;
import com.fwj.helloserver.entity.User;
import com.fwj.helloserver.mapper.UserMapper;
import com.fwj.helloserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final StringRedisTemplate redisTemplate;

    private static final String CACHE_KEY_PREFIX = "user:detail:";

    @Override
    public Result<UserDetailDTO> getUserDetail(Long userId) {
        String key = CACHE_KEY_PREFIX + userId;

        // 1. 查Redis缓存
        String json = redisTemplate.opsForValue().get(key);
        if (json != null && !json.isBlank()) {
            try {
                UserDetailDTO cacheDTO = JSONUtil.toBean(json, UserDetailDTO.class);
                return Result.success(cacheDTO);
            } catch (Exception e) {
                redisTemplate.delete(key);
            }
        }

        // 2. 查数据库
        UserDetailDTO detail = userMapper.getUserDetail(userId);
        if (detail == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        // 3. 写入缓存
        redisTemplate.opsForValue().set(
                key,
                JSONUtil.toJsonStr(detail),
                10,
                TimeUnit.MINUTES
        );

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

        // 更新后删除缓存
        redisTemplate.delete(CACHE_KEY_PREFIX + user.getId());
        return Result.success("更新成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> deleteUser(Long userId) {
        int rows = userMapper.deleteById(userId);
        if (rows <= 0) {
            return Result.error("删除失败");
        }

        redisTemplate.delete(CACHE_KEY_PREFIX + userId);
        return Result.success("删除成功");
    }
}