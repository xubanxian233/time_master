package com.example.team.controller;

import com.example.team.pojo.UserTodo;
import com.example.team.pojo.UserTodoSet;
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

    @Autowired
    private UserTodoSetService userTodoSetService;

    /**
     * listById 列出某一用户待办集中所有用户待办
     *
     * @param param 用户待办集ID
     * @return List<UserTodo> 用户待办
     **/
    @RequestMapping(value = "/listById", method = RequestMethod.POST)
    @ResponseBody
    public List<UserTodo> listById(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        Integer userTodoSetId = Integer.valueOf(param.get("userTodoSetId").toString());
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
     * @param param 用户待办名，用户待办集ID，时长
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public UserTodo create(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        int userTodoSetId = Integer.valueOf(param.get("userTodoSetId").toString());
        UserTodo userTodo = new UserTodo();
        userTodo.setName(param.get("name").toString());
        userTodo.setUserTodoSetId(userTodoSetId);
        userTodo.setUserId(userId);
        userTodo.setTime(Long.valueOf(param.get("time").toString()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date create = new Date();
        userTodo.setCreate(java.sql.Date.valueOf(df.format(create)));
        userTodo.setTodoStatusId(1);
        if (userTodoSetService.getById(userTodoSetId)==null){
            return null;
        }
        else if (userTodoService.createUserTodo(userTodo)) {
            UserTodo userTodo1 = userTodoService.getByName(userTodo.getName());
            return userTodo1;
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
        Integer userTodoId = Integer.valueOf(param.get("userTodoId").toString());
        userTodoService.deleteUserTodo(userTodoId);
        return "delete-success";
    }

    /**
     * update 更新用户待办
     *
     * @param param 用户待办名，用户待办集ID，用户待办ID，时长，创建时间，状态ID，类型ID
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        String result = "update-fail";
        int todoStatusId = Integer.valueOf(param.get("todoStatusId").toString());
        int userTodoSetId = Integer.valueOf(param.get("userTodoSetId").toString());
        UserTodo userTodo = new UserTodo();
        userTodo.setUserTodoId(Integer.valueOf(param.get("userTodoId").toString()));
        userTodo.setName(param.get("name").toString());
        userTodo.setUserTodoSetId(userTodoSetId);
        userTodo.setUserId(userId);
        userTodo.setTime(Long.valueOf(param.get("time").toString()));
        userTodo.setCreate(java.sql.Date.valueOf(param.get("create").toString()));
        userTodo.setTodoStatusId(todoStatusId);
        if (todoStatusId<1||todoStatusId>3){
            result = "update-fail:状态ID错误";
        }
        else if (userTodoService.getById(userTodoSetId)==null){
            result = "update-fail:用户待办集不存在";
        }
        else if (userTodoService.updateUserTodo(userTodo)) {
            result = "update-success";
        }
        return result;
    }

    /**
     * updateState 更新用户待办状态
     *
     * @param param 状态ID，用户待办ID
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    @ResponseBody
    public String updateState(@RequestBody Map<String, Object> param, @RequestHeader("id") int userId) {
        String result = "updateState-fail";
        int userTodoId = Integer.valueOf(param.get("userTodoId").toString());
        int todoStatusId = Integer.valueOf(param.get("todoStatusId").toString());
        if (todoStatusId<1||todoStatusId>3){
            result = "updateState-fail:状态ID错误";
        }
        else if (userTodoService.updateState(userTodoId, todoStatusId)) {
            result = "updateState-success";
        }
        return result;
    }
}
