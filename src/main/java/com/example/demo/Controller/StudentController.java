package com.example.demo.Controller;

import com.example.demo.Mapper.UserMapper;
import com.example.demo.entity.Form;
import com.example.demo.entity.isChecked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/ischeck")
    public ResponseEntity<Map<String, Object>> ischeck(@RequestParam String stuId) {
        isChecked ok = userMapper.isCheckByStuId(stuId);
        Form f1 = userMapper.findUserByStuId(stuId);
        Map<String, Object> response = new HashMap<>();
        if (ok!=null) {
            response.put("code", HttpStatus.OK.value());
            response.put("message", "查询成功");
            response.put("ischeck", ok);
            response.put("info", f1);
        } else {
            response.put("code", HttpStatus.UNAUTHORIZED.value());
            response.put("message", "查询失败");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/pass")
    public ResponseEntity<Map<String, Object>> pass(@RequestParam("stuId") String stuId, @RequestParam("isCheck") int isCheck, @RequestParam("reason") String reason) {
        int ok = userMapper.updateCheck(stuId, isCheck,reason);
        Map<String, Object> response = new HashMap<>();
        if (ok!=0) {
            response.put("code", HttpStatus.OK.value());
            response.put("message", "批改成功");
        } else {
            response.put("code", HttpStatus.UNAUTHORIZED.value());
            response.put("message", "批改失败");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/return/ischeck")
    public ResponseEntity<Map<String, Object>> returnIsCheck() {
        List<String> s1=userMapper.findIsCheck();
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.OK.value());
        response.put("message", "查询成功");
        response.put("ischeck", s1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
