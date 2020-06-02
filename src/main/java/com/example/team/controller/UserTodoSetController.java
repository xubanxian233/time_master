package com.example.team.controller;

import com.example.team.pojo.UserTodoSet;
import com.example.team.service.UserTodoSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userTodoSet")
public class UserTodoSetController extends BaseController {

    @Autowired
    private UserTodoSetService userTodoSetService;

    /**
     * list 列出用户待办集
     *
     * @param
     * @return List<UserTodoSet> 用户待办集
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<UserTodoSet> list(@RequestHeader("id") int userId) {
        return userTodoSetService.listByUserId(userId);
    }

    /**
     * get 获取某一具体的用户待办集
     *
     * @param param 用户待办集ID
     * @return UserTodoSet 用户待办集
     **/
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public UserTodoSet get(@RequestBody Map<String, Object> param) {
        Integer userTodoSetId = Integer.valueOf(param.get("userTodoSetId").toString());
        return userTodoSetService.getById(userTodoSetId);
    }

    /**
     * create 创建用户待办集
     *
     * @param param 用户待办集名称
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        UserTodoSet userTodoSet = new UserTodoSet();
        userTodoSet.setName(param.get("name").toString());
        userTodoSet.setUserId(userId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date create = new Date();
        userTodoSet.setCreate(java.sql.Date.valueOf(df.format(create)));
        if (userTodoSetService.createUserTodoSet(userTodoSet)) {
            UserTodoSet userTodoSet1 = userTodoSetService.getByName(userTodoSet.getName());
            return "create-success,userTodoSetId:" + userTodoSet1.getUserTodoSetId();
        }
        return "create-fail";
    }

    /**
     * update 更新用户待办集
     *
     * @param param 用户待办集名称，用户待办集ID，创建时间
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        UserTodoSet userTodoSet = new UserTodoSet();
        userTodoSet.setUserTodoSetId(Integer.valueOf(param.get("userTodoSetId").toString()));
        userTodoSet.setName(param.get("name").toString());
        userTodoSet.setUserId(userId);
        userTodoSet.setCreate(java.sql.Date.valueOf(param.get("create").toString()));
        if (userTodoSetService.updateUserTodoSet(userTodoSet)) {
            UserTodoSet userTodoSet1 = userTodoSetService.getByName(userTodoSet.getName());
            return "update-success,userTodoSetId:"+userTodoSet1.getUserTodoSetId();
        }
        return "update-fail";
    }

    /**
     * delete 删除用户待办集
     *
     * @param param 用户待办集ID
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestBody Map<String, Object> param) {
        Integer userTodoSetId = Integer.valueOf(param.get("userTodoSetId").toString());
        userTodoSetService.deleteUserTodoSet(userTodoSetId);
        return "delete-success";
    }
}
