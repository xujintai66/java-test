package com.example.demo.Controller;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.entity.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class FromController {
    @Autowired
    private UserMapper userMapper;

    //提交表单到数据库
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submit(@ModelAttribute Form form) {
        Form existingForm = userMapper.findUserByStuId(form.getStuId());
        int ok;
        if(existingForm==null) {
            ok=userMapper.InsertUser(form);
            userMapper.insertCheck(form.getStuId());
        }
        else {
            ok=userMapper.updateUser(form);
            userMapper.updateCheck(form.getStuId(),0,null);
        }
        Map<String, Object> response = new HashMap<>();
        if (ok!=0) {
            response.put("code", HttpStatus.OK.value());
            response.put("message", "插入成功");
        } else {
            response.put("code", HttpStatus.UNAUTHORIZED.value());
            response.put("message", "插入失败");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //返回目前数据库内所有的数据
    @GetMapping("/return")
    public List returnForm() {
        List<Form> f1=userMapper.findForm();
        return f1;
    }

    //删除数据
    @PostMapping("/delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody String stuId) {
        int ok=userMapper.deleteUserByStuId(stuId);
        Map<String, Object> response = new HashMap<>();
        if (ok!=0) {
            response.put("code", HttpStatus.OK.value());
            response.put("message", "删除成功");
        } else {
            response.put("code", HttpStatus.UNAUTHORIZED.value());
            response.put("message", "删除失败");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
