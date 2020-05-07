package com.example.team.controller;

import com.example.team.pojo.UserTodoSet;
import com.example.team.service.UserTodoSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userTodoSet")
public class UserTodoSetController {

    @Autowired
    private UserTodoSetService userTodoSetService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<UserTodoSet> list(@RequestBody Map<String,Object> param){
        Integer userId = Integer.valueOf(param.get("userId").toString());
        return userTodoSetService.listByUserId(userId);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public UserTodoSet get(@RequestBody Map<String,Object> param){
        Integer userTodoSetId = Integer.valueOf(param.get("userTodoSetId").toString());
        return userTodoSetService.getById(userTodoSetId);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Map<String,Object> param){
        UserTodoSet userTodoSet = new UserTodoSet();
        userTodoSet.setName(param.get("name").toString());
        userTodoSet.setUserId(Integer.valueOf(param.get("userId").toString()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date create = new Date();
        userTodoSet.setCreate(java.sql.Date.valueOf(df.format(create)));
        if (userTodoSetService.createUserTodoSet(userTodoSet)){
            return "create-success";
        }
        return "create-fail";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody Map<String,Object> param){
        UserTodoSet userTodoSet = new UserTodoSet();
        userTodoSet.setUserTodoSetId(Integer.valueOf(param.get("userTodoSetId").toString()));
        userTodoSet.setName(param.get("name").toString());
        userTodoSet.setUserId(Integer.valueOf(param.get("userId").toString()));
        userTodoSet.setCreate(java.sql.Date.valueOf(param.get("create").toString()));
        if (userTodoSetService.updateUserTodoSet(userTodoSet)){
            return "update-success";
        }
        return "update-fail";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestBody Map<String,Object> param){
        Integer userTodoSetId = Integer.valueOf(param.get("userTodoSetId").toString());
        userTodoSetService.deleteUserTodoSet(userTodoSetId);
        return "delete-success";
    }
}
