package com.example.team.controller;

import com.example.team.pojo.UserTodo;
import com.example.team.service.UserTodoService;
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

    /**
     * listById 列出某一用户待办集中所有用户待办
     *
     * @param param 用户待办集ID
     * @return List<UserTodo> 用户待办
     **/
    @RequestMapping(value = "/listById", method = RequestMethod.POST)
    @ResponseBody
    public List<UserTodo> listById(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        int userTodoSetId = Integer.parseInt(param.get("userTodoSetId").toString());
        return userTodoService.listUserTodo(userTodoSetId, userId);
    }

    /**
     * listByUserId 列出所有用户待办
     *
     * @param
     * @return List<UserTodo> 用户待办
     **/
    @RequestMapping(value = "/listByUserId", method = RequestMethod.GET)
    @ResponseBody
    public List<UserTodo> listByUserId(@RequestHeader("id") int userId) {
        return userTodoService.listUserTodo(userId);
    }

    /**
     * get 获取某一用户待办
     *
     * @param param 用户待办ID
     * @return UserTodo 用户待办
     **/
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public UserTodo get(@RequestBody Map<String, Object> param) {
        Integer userTodoId = Integer.valueOf(param.get("userTodoId").toString());
        return userTodoService.getById(userTodoId);
    }

    /**
     * create 创建用户待办
     *
     * @param param 用户待办名，用户待办集ID(可以不填，默认为0），时长
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
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

    /**
     * delete 删除用户待办
     *
     * @param param 用户待办ID
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestBody Map<String, Object> param) {
        int userTodoId = Integer.parseInt(param.get("userTodoId").toString());
        userTodoService.deleteUserTodo(userTodoId);
        return "delete-success";
    }

    /**
     * update 更新用户待办
     *
     * @param param 用户待办名，用户待办集ID，用户待办ID，时长，创建时间，状态ID，类型ID
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
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

    /**
     * updateState 更新用户待办状态
     *
     * @param userTodoId,todoStatusId 状态ID，用户待办ID
     * @return String 成功或失败
     **/
    @RequestMapping("/updateState")
    @ResponseBody
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
