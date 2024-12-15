package com.example.demo.Controller;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserMapper userMapper;
//    @GetMapping("/user")
//    public List query() {
//        List<User> list= userMapper.find();
//        return list;
//    }

}
