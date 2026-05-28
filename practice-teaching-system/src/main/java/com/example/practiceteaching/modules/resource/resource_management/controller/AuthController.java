package com.example.practiceteaching.modules.resource.resource_management.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practiceteaching.common.result.Result;
import com.example.practiceteaching.modules.resource.resource_management.entity.User;
import com.example.practiceteaching.modules.resource.resource_management.mapper.UserMapper;
import com.example.practiceteaching.modules.resource.resource_management.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        if(username == null || password == null) {
            return Result.error("用户名或密码不能为空");
        }

        // 注意：实际项目中应对密码解密或比对Hash
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null || !user.getPassword().equals(password)) {
            return Result.error("用户名或密码错误");
        }

        String token = jwtUtils.generateToken(user.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("username", user.getUsername());

        return Result.success(map);
    }
}

