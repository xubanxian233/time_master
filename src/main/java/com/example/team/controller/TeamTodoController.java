package com.example.team.controller;

import com.example.team.dao.TeamTodoDAO;
import com.example.team.pojo.Team;
import com.example.team.pojo.TeamTodo;
import com.example.team.pojo.User;
import com.example.team.service.TeamTodoService;
import com.example.team.service.TeamTodoSetService;
import com.example.team.service.UserService;
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

    @Autowired
    private TeamTodoSetService teamTodoSetService;

    /**
     * listByTeamId 通过团队ID获取团队所有的待办事项
     *
     * @param param 团队ID
     * @return List<TeamTodo> 团队待办事项
     **/
    @RequestMapping(value = "/listByTeamId", method = RequestMethod.POST)
    @ResponseBody
    private List<TeamTodo> listByTeamId(@RequestBody Map<String,Object> param,@RequestHeader("id") int userId){
        int teamId = Integer.parseInt(param.get("teamId").toString());
        return teamTodoService.listByUser(teamId,userId);
    }

    /**
     * listById 通过团队待办集ID获取具体的团队待办集中的事项
     *
     * @param param 团队ID和团队待办集ID
     * @return List<TeamTodo> 某一团队待办集中的事项
     **/
    @RequestMapping(value = "/listById", method = RequestMethod.POST)
    @ResponseBody
    private List<TeamTodo> listById(@RequestBody Map<String,Object> param,@RequestHeader("id") int userId){
        int teamId = Integer.parseInt(param.get("teamId").toString());
        int teamTodoSetId = Integer.parseInt(param.get("teamTodoSetId").toString());
        return teamTodoService.listTeamTodo(teamTodoSetId,teamId,userId);
    }

    /**
     * get 通过团队ID获取具体的团队待办事项
     *
     * @param param 团队待办ID
     * @return List<TeamTodo> 某一团队待办事项
     **/
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    private TeamTodo getTeamTodo(@RequestBody Map<String,Object> param,@RequestHeader("id") int userId){
        int teamTodoId = Integer.parseInt(param.get("teamTodoId").toString());
        return teamTodoService.getById(teamTodoId);
    }

    /**
     * create 创建团队待办
     *
     * @param param 团队待办名，团队ID，团队待办集ID，时长
     * @return String 功或失败
     **/
    @RequestMapping(value = "/create", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
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
            if (teamTodoSetService.getById(teamTodoSetId)==null){
                return "create-fail:团队待办集不存在";
            }
            teamTodo.setName(param.get("name").toString());
            teamTodo.setTeamId(Integer.parseInt(param.get("teamId").toString()));
            teamTodo.setTime(Long.parseLong(param.get("time").toString()));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date create = new Date();
            teamTodo.setCreate(java.sql.Date.valueOf(df.format(create)));
            teamTodo.setTodoStatusId(1);
            teamTodo.setUserId(user.getUserId());
            if (teamTodoService.createTeamTodo(teamTodo)) {
                result = "create-success";
            }
        }
        return result;
    }

    /**
     * update 更新团队待办
     *
     * @param param 原团队待办名，改后的团队待办名，团队待办集ID，时长，团队待办ID，待办状态ID，创建时间
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
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
            if (teamTodoSetService.getById(teamTodoSetId)==null){
                return "update-fail:团队待办集ID不存在";
            }
            else if (todoStatusId<1||todoStatusId>3){
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

    /**
     * delete 删除团队待办
     *
     * @param param 团队待办名称，团队ID
     * @return String 成功
     **/
    @RequestMapping(value = "/delete", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    private String deleteTeamTodo(@RequestBody Map<String,Object> param){
        String name = param.get("name").toString();
        Set<User> set = userService.getMembers(Integer.parseInt(param.get("teamId").toString()));
        for (User user : set){
            teamTodoService.deleteByUser(name,user.getUserId());
        }
        return "delete-success";
    }

    /**
     * updateState 更新团队待办状态
     *
     * @param teamTodoId,todoStatusId, 状态ID，团队待办ID
     * @return String 成功或失败
     **/
    @RequestMapping("/updateState")
    @ResponseBody
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
