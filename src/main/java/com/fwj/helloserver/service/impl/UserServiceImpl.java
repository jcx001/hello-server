package com.fwj.helloserver.service.impl;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.dto.UserDetailDTO;
import com.fwj.helloserver.entity.User;
import com.fwj.helloserver.mapper.UserMapper;
import com.fwj.helloserver.security.JwtUtil;
import com.fwj.helloserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Result<UserDetailDTO> getUserDetail(Long userId) {
        UserDetailDTO detail = userMapper.getUserDetail(userId);
        if (detail == null) {
            return Result.error("用户不存在");
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

    // ===================== 登录 =====================
    @Override
    public Result<String> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return Result.error("用户不存在");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return Result.error("密码错误");
        }

        String token = jwtUtil.generateToken(username);
        return Result.success(token);
    }

    // ===================== 注册（修复版！）=====================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> register(User user) {
        // 1. 查用户
        User exist = userMapper.selectByUsername(user.getUsername());
        if (exist != null) {
            return Result.error("用户名已存在");
        }

        // 2. 加密密码
        String encodePwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);

        // 3. 插入并判断是否成功！！！关键修复
        int rows = userMapper.insert(user);
        if (rows <= 0) {
            return Result.error("注册失败：数据库插入失败");
        }

        return Result.success("注册成功");
    }
}