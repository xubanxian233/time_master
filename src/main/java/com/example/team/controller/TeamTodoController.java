package com.example.team.controller;

import com.example.team.pojo.TeamTodo;
import com.example.team.pojo.User;
import com.example.team.service.TeamTodoService;
import com.example.team.service.UserService;
import com.example.team.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/teamTodo")
public class TeamTodoController extends BaseController {

    @Autowired
    private TeamTodoService teamTodoService;

    @Autowired
    private UserService userService;

    /**
     * listByTeamId 通过团队ID获取团队所有的待办事项
     *
     * @param param 团队ID
     * @return List<TeamTodo> 团队待办事项
     **/
    @RequestMapping(value = "/listByTeamId", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 获取团队待办集合
     * @Param: [param, userId]
     * @return: java.util.List<com.example.team.pojo.TeamTodo>
     * @update: time: 2020/6/3 9:23
     */
    private List<TeamTodo> listByTeamId(@RequestBody Map<String,Object> param,@RequestHeader("id") int userId){
        int teamId = Integer.parseInt(param.get("teamId").toString());
        return teamTodoService.listByUser(teamId,userId);
    }


    @RequestMapping(value = "/listById", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 获取团队待办集的集合
     * @Param: [param, userId]
     * @return: java.util.List<com.example.team.pojo.TeamTodo>
     * @update: time: 2020/6/3 9:23
     */
    private List<TeamTodo> listById(@RequestBody Map<String,Object> param,@RequestHeader("id") int userId){
        int teamId = Integer.parseInt(param.get("teamId").toString());
        int teamTodoSetId = Integer.parseInt(param.get("teamTodoSetId").toString());
        return teamTodoService.listTeamTodo(teamTodoSetId,teamId,userId);
    }

 
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 获取团队待办
     * @Param: [param, userId]
     * @return: com.example.team.pojo.TeamTodo
     * @update: time: 2020/6/3 9:23
     */
    private TeamTodo getTeamTodo(@RequestBody Map<String,Object> param,@RequestHeader("id") int userId){
        int teamTodoId = Integer.parseInt(param.get("teamTodoId").toString());
        return teamTodoService.getById(teamTodoId);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    /**
     * @description: 创建团队待办
     * @Param: [param]
     * @return: java.lang.String
     * @update: time: 2020/6/3 9:23
     */
    private String createTeamTodo(@RequestBody Map<String,Object> param){
        String result = "create-fail";
        int teamTodoSetId = 1;
        Set<User> set = userService.getMembers(Integer.parseInt(param.get("teamId").toString()));
        for (User user : set) {
            TeamTodo teamTodo = new TeamTodo();
            if (param.get("teamTodoSetId") == null){
                teamTodo.setTeamTodoSetId(teamTodoSetId);
            }
            else {
                teamTodoSetId = Integer.parseInt(param.get("teamTodoSetId").toString());
                teamTodo.setTeamTodoSetId(teamTodoSetId);
            }
            teamTodo.setName(param.get("name").toString());
            teamTodo.setTeamId(Integer.parseInt(param.get("teamId").toString()));
            teamTodo.setTime(Long.parseLong(param.get("time").toString()));
            teamTodo.setCreate(DateUtil.getCurrentTime());
            teamTodo.setTodoStatusId(1);
            teamTodo.setUserId(user.getUserId());
            if (teamTodoService.createTeamTodo(teamTodo)) {
                result = "create-success";
            }
        }
        return result;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    /**
     * @description: 更新团队待办
     * @Param: [param]
     * @return: java.lang.String
     * @update: time: 2020/6/3 9:23
     */
    private String updateTeamTodo(@RequestBody Map<String,Object> param){
        String result = "update-fail";
        String name = param.get("name").toString();
        int teamTodoSetId = Integer.parseInt(param.get("teamTodoSetId").toString());
        int todoStatusId = Integer.parseInt(param.get("todoStatusId").toString());
        Set<User> set = userService.getMembers(Integer.parseInt(param.get("teamId").toString()));
        for (User user : set) {
            TeamTodo teamTodo = new TeamTodo();
            teamTodo.setName(param.get("changeName").toString());
            teamTodo.setTeamId(Integer.parseInt(param.get("teamId").toString()));
            teamTodo.setTeamTodoSetId(teamTodoSetId);
            teamTodo.setTime(Long.parseLong(param.get("time").toString()));
            teamTodo.setTodoStatusId(todoStatusId);
            teamTodo.setCreate(java.sql.Date.valueOf(param.get("create").toString()));
            if (todoStatusId<1||todoStatusId>3){
                return "update-fail：状态ID错误";
            }
            teamTodo.setUserId(user.getUserId());
            int teamTodoId = teamTodoService.getByUser(name,user.getUserId()).getTeamTodoId();
            teamTodo.setTeamTodoId(teamTodoId);
            if (teamTodoService.updateTeamTodo(teamTodo)) {
                result = "update-success";
            }
        }
        return result;
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    /**
     * @description: 删除团队待办
     * @Param: [param]
     * @return: java.lang.String
     * @update: time: 2020/6/3 9:23
     */
    private String deleteTeamTodo(@RequestBody Map<String,Object> param){
        String name = param.get("name").toString();
        Set<User> set = userService.getMembers(Integer.parseInt(param.get("teamId").toString()));
        for (User user : set){
            teamTodoService.deleteByUser(name,user.getUserId(),Integer.parseInt(param.get("teamId").toString()));
        }
        return "delete-success";
    }

    
    @RequestMapping("/updateState")
    @ResponseBody
    /**
     * @description: 更新团队待办状态
     * @Param: [teamTodoId, todoStatusId, userId]
     * @return: java.lang.String
     * @update: time: 2020/6/3 9:23
     */
    public String updateState(@RequestParam String teamTodoId, @RequestParam String todoStatusId,
                              @RequestHeader("id") int userId){
        String result = "updateState-fail";
        int teamTodoId1 = Integer.parseInt(teamTodoId);
        int todoStatusId1 = Integer.parseInt(todoStatusId);
        if (todoStatusId1<1||todoStatusId1>3){
             result = "updateState-fail：状态ID错误";
        }
        else if (teamTodoService.updateState(teamTodoId1,todoStatusId1,userId)){
            result = "updateState-success";
        }
        return result;
    }
}
