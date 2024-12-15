package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/post/login")
    public ResponseEntity<Map<String, Object>> log(
            @RequestPart("username") String username,
            @RequestPart("password") String password
    )
    {
        List<User> users = userMapper.find(username, password);
        Map<String, Object> response = new HashMap<>();
        if (users!= null &&!users.isEmpty()) {
            response.put("code", HttpStatus.OK.value());
            response.put("message", "登录成功");
            response.put("data", users);
        } else {
            response.put("code", HttpStatus.UNAUTHORIZED.value()); // 401状态码表示未授权，符合登录失败场景
            response.put("message", "用户名或密码错误");
            response.put("data", null);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }

    }
}
