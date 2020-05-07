package com.example.team.controller;

import com.example.team.pojo.UserTodo;
import com.example.team.service.UserTodoService;
import com.example.team.service.UserTodoSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/UserTodo")
public class UserTodoController {

    @Autowired
    private UserTodoService userTodoService;

    @Autowired
    private UserTodoSetService userTodoSetService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<UserTodo> list(@RequestBody Map<String,Object> param) {
        Integer userId=Integer.valueOf(param.get("userId").toString());
        Integer userTodoSetId=Integer.valueOf(param.get("userTodoSetId").toString());
        if (userTodoSetId==null&&userId!=null){
            return userTodoService.listUserTodo(userId);
        }else if(userTodoSetId!=null&&userId!=null){
            return userTodoService.listUserTodo(userTodoSetId,userId);
        }
        return null;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Map<String,Object> param) {
        UserTodo userTodo = new UserTodo();
        userTodo.setName(param.get("name").toString());
        userTodo.setUserTodoSetId(Integer.valueOf(param.get("userTodoSetId").toString()));
        userTodo.setUserId(Integer.valueOf(param.get("userId").toString()));
        userTodo.setTime(Long.valueOf(param.get("time").toString()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date create = new Date();
        userTodo.setCreate(java.sql.Date.valueOf(df.format(create)));
        userTodo.setTodoStatusId(1);
        userTodo.setTypeId(0);
        if (userTodoService.createUserTodo(userTodo)){
            return "create-success";
        }
        return "create-fail";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestBody Map<String,Object> param){
        Integer userTodoId = Integer.valueOf(param.get("userTodoId").toString());
        userTodoService.deleteUserTodo(userTodoId);
        return "delete-success";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody Map<String,Object> param) {
        UserTodo userTodo = new UserTodo();
        userTodo.setUserTodoId(Integer.valueOf(param.get("userTodoId").toString()));
        userTodo.setName(param.get("name").toString());
        userTodo.setUserTodoSetId(Integer.valueOf(param.get("userTodoSetId").toString()));
        userTodo.setUserId(Integer.valueOf(param.get("userId").toString()));
        userTodo.setTime(Long.valueOf(param.get("time").toString()));
        userTodo.setCreate(java.sql.Date.valueOf(param.get("create").toString()));
        userTodo.setTodoStatusId(Integer.valueOf(param.get("TodoStatusId").toString()));
        userTodo.setTypeId(Integer.valueOf(param.get("TypeId").toString()));
        if (userTodoService.updateUserTodo(userTodo)){
            return "update-success";
        }
        return "update-fail";
    }
}
