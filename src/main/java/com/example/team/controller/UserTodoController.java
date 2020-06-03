package com.example.team.controller;

import com.example.team.pojo.UserTodo;
import com.example.team.service.UserTodoService;
import com.example.team.service.UserTodoSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userTodo")
public class UserTodoController extends BaseController {

    @Autowired
    private UserTodoService userTodoService;

    @RequestMapping(value = "/listById", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 获取对应待办集的待办集合
     * @Param: [param, userId]
     * @return: java.util.List<com.example.team.pojo.UserTodo> 待办集合
     * @update: time: 2020/6/3 9:01
     */
    public List<UserTodo> listById(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        int userTodoSetId = Integer.parseInt(param.get("userTodoSetId").toString());
        return userTodoService.listUserTodo(userTodoSetId, userId);
    }

    @RequestMapping(value = "/listByUserId", method = RequestMethod.GET)
    @ResponseBody
    /**
     * @description: 获取所有待办集合
     * @Param: [userId]
     * @return: java.util.List<com.example.team.pojo.UserTodo> 待办集合
     * @update: time: 2020/6/3 9:01
     */
    public List<UserTodo> listByUserId(@RequestHeader("id") int userId) {
        return userTodoService.listUserTodo(0, userId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description:
     * @Param: [param]
     * @return: com.example.team.pojo.UserTodo
     * @update: time: 2020/6/3 9:01
     */
    public UserTodo get(@RequestBody Map<String, Object> param) {
        Integer userTodoId = Integer.valueOf(param.get("userTodoId").toString());
        return userTodoService.getById(userTodoId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 创建待办
     * @Param: [param, userId]
     * @return: com.example.team.pojo.UserTodo 待办
     * @update: time: 2020/6/3 9:01
     */
    public UserTodo create(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        int userTodoSetId = 0;
        UserTodo userTodo = new UserTodo();
        userTodo.setName(param.get("name").toString());
        if(param.get("userTodoSetId")==null){
            userTodo.setUserTodoSetId(userTodoSetId);
        }
        else {
            userTodoSetId = Integer.parseInt(param.get("userTodoSetId").toString());
            userTodo.setUserTodoSetId(userTodoSetId);
        }
        userTodo.setUserId(userId);
        userTodo.setTime(Long.parseLong(param.get("time").toString()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date create = new Date();
        userTodo.setCreate(java.sql.Date.valueOf(df.format(create)));
        userTodo.setTodoStatusId(1);
        if (userTodoService.createUserTodo(userTodo)) {
            userTodo = userTodoService.getByName(userTodo.getName(),userId);
            return userTodo;
        }
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 删除待办
     * @Param: [param]
     * @return: java.lang.String 删除结果
     * @update: time: 2020/6/3 9:01
     */
    public String delete(@RequestBody Map<String, Object> param) {
        int userTodoId = Integer.parseInt(param.get("userTodoId").toString());
        userTodoService.deleteUserTodo(userTodoId);
        return "delete-success";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    /**
     * @description: 更新待办
     * @Param: [param, userId]
     * @return: java.lang.String 更新结果
     * @update: time: 2020/6/3 9:01
     */
    public String update(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        String result = "update-fail";
        int todoStatusId = Integer.parseInt(param.get("todoStatusId").toString());
        int userTodoSetId = Integer.parseInt(param.get("userTodoSetId").toString());
        UserTodo userTodo = new UserTodo();
        userTodo.setUserTodoId(Integer.parseInt(param.get("userTodoId").toString()));
        userTodo.setName(param.get("name").toString());
        userTodo.setUserTodoSetId(userTodoSetId);
        userTodo.setUserId(userId);
        userTodo.setTime(Long.parseLong(param.get("time").toString()));
        userTodo.setCreate(java.sql.Date.valueOf(param.get("create").toString()));
        userTodo.setTodoStatusId(todoStatusId);
        if (todoStatusId<1||todoStatusId>3){
            result = "update-fail:状态ID错误";
        }
        else if (userTodoService.updateUserTodo(userTodo)) {
            result = "update-success";
        }
        return result;
    }

    @RequestMapping("/updateState")
    @ResponseBody
    /**
     * @description: 更新状态
     * @Param: [userTodoId, todoStatusId, userId]
     * @return: java.lang.String 更新结果
     * @update: time: 2020/6/3 9:01
     */
    public String updateState(@RequestParam String userTodoId, @RequestParam String todoStatusId,
                              @RequestHeader("id") int userId) {
        String result = "updateState-fail";
        int userTodoId1 = Integer.parseInt(userTodoId);
        int todoStatusId1 = Integer.parseInt(todoStatusId);
        if (todoStatusId1<1||todoStatusId1>3){
            result = "updateState-fail:状态ID错误";
        }
        else if (userTodoService.updateState(userTodoId1, todoStatusId1)) {
            result = "updateState-success";
        }
        return result;
    }
}

