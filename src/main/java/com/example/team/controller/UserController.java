package com.example.team.controller;

import com.example.team.pojo.User;
import com.example.team.pojo.UserTodo;
import com.example.team.service.RecordService;
import com.example.team.service.UserService;
import com.example.team.service.UserTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private UserTodoService userTodoService;

    //登录
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    @ResponseBody
    public List<UserTodo> login(@RequestBody Map<String,Object> param) {
        String userName=param.get("userName").toString();
        String password=param.get("password").toString();
        if (userService.verify(userName, password)) {
            response.setHeader("Access-Control-Expose-Headers", "token");
            response.setHeader("Access-Control-Expose-Headers", "id");
            response.setHeader("token", userService.getToken());
            response.setHeader("id", String.valueOf(userService.getId()));
            return userTodoService.listUserTodo(0,userService.getId());
        }
        return null;
    }

    @RequestMapping(value = "/sign", method= RequestMethod.POST)
    @ResponseBody
    public String sign(@RequestBody Map<String,Object> param) {
        User user = new User();
        user.setEmail(param.get("email").toString());
        user.setName(param.get("userName").toString());
        user.setTel(param.get("tel").toString());
        user.setPassword(param.get("password").toString());
        user.setSex(param.get("sex").toString());
        user.setCreate(new Date(new java.util.Date().getTime()));
        if (userService.sign(user)) {
            return "sign-succees";
        }
        return "sign-fail";
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test-success";
    }
}
