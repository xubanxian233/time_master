package com.example.team.controller;

import com.example.team.pojo.Team;
import com.example.team.pojo.TeamTodo;
import com.example.team.service.TeamTodoService;
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
@RequestMapping("/teamTodo")
public class TeamTodoController extends BaseController{

    @Autowired
    private TeamTodoService teamTodoService;

    /**
     * listByTeamId 通过团队ID获取团队所有的待办事项
     *
     * @param param 团队ID
     * @return List<TeamTodo> 团队待办事项
     **/
    @RequestMapping(value = "/listByTeamId",method = RequestMethod.POST)
    @ResponseBody
    private List<TeamTodo> listByTeamId(@RequestBody Map<String,Object> param){
        Integer teamId = Integer.valueOf(param.get("teamId").toString());
        return teamTodoService.listTeamTodo(teamId);
    }

    /**
     * listById 通过团队待办集ID获取具体的团队待办集中的事项
     *
     * @param param 团队ID和团队待办集ID
     * @return List<TeamTodo> 某一团队待办集中的事项
     **/
    @RequestMapping(value = "/listById",method = RequestMethod.POST)
    @ResponseBody
    private List<TeamTodo> listById(@RequestBody Map<String,Object> param){
        Integer teamId = Integer.valueOf(param.get("teamId").toString());
        Integer teamTodoSetId = Integer.valueOf(param.get("teamTodoSetId").toString());
        return teamTodoService.listTeamTodo(teamTodoSetId,teamId);
    }

    /**
     * get 通过团队ID获取具体的团队待办事项
     *
     * @param param 团队待办ID
     * @return List<TeamTodo> 某一团队待办事项
     **/
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    private TeamTodo getTeamTodo(@RequestBody Map<String,Object> param){
        Integer teamTodoId = Integer.valueOf(param.get("teamTodoId").toString());
        return teamTodoService.getById(teamTodoId);
    }

    /**
     * create 创建团队待办
     *
     * @param param 团队待办名，团队ID，团队待办集ID，时长
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    private String createTeamTodo(@RequestBody Map<String,Object> param){
        TeamTodo teamTodo = new TeamTodo();
        teamTodo.setName(param.get("name").toString());
        teamTodo.setTeamId(Integer.valueOf(param.get("teamId").toString()));
        teamTodo.setTeamTodoSetId(Integer.valueOf(param.get("teamTodoSetId").toString()));
        teamTodo.setTime(Long.valueOf(param.get("time").toString()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date create = new Date();
        teamTodo.setCreate(java.sql.Date.valueOf(df.format(create)));
        teamTodo.setTypeId(0);
        teamTodo.setTodoStatusId(1);
        if (teamTodoService.createTeamTodo(teamTodo)){
            TeamTodo teamTodo1 = teamTodoService.getByName(teamTodo.getName());
            return "create-success,teamTodoId:"+teamTodo1.getTeamTodoId();
        }
        return "create-fail";
    }

    /**
     * update 更新团队待办
     *
     * @param param 团队待办名，团队ID，团队待办集ID，时长，团队待办ID，待办状态ID，创建时间
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    private String updateTeamTodo(@RequestBody Map<String,Object> param){
        TeamTodo teamTodo = new TeamTodo();
        teamTodo.setName(param.get("name").toString());
        teamTodo.setTeamId(Integer.valueOf(param.get("teamId").toString()));
        teamTodo.setTeamTodoSetId(Integer.valueOf(param.get("teamTodoSetId").toString()));
        teamTodo.setTime(Long.valueOf(param.get("time").toString()));
        teamTodo.setTeamTodoId(Integer.valueOf(param.get("teamTodoId").toString()));
        teamTodo.setTypeId(Integer.valueOf(param.get("typeId").toString()));
        teamTodo.setTodoStatusId(Integer.valueOf(param.get("todoStatusId").toString()));
        teamTodo.setCreate(java.sql.Date.valueOf(param.get("create").toString()));
        if (teamTodoService.updateTeamTodo(teamTodo)){
            return "update-success";
        }
        return "update-fail";
    }

    /**
     * delete 删除团队待办
     *
     * @param param 团队待办ID
     * @return String 成功
     **/
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    private String deleteTeamTodo(@RequestBody Map<String,Object> param){
        Integer teamTodoId = Integer.valueOf(param.get("teamTodoId").toString());
        teamTodoService.deleteTeamTodo(teamTodoId);
        return "delete-success";
    }

    /**
     * updateState 更新团队待办状态
     *
     * @param param 状态ID，团队待办ID
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/updateState",method = RequestMethod.POST)
    @ResponseBody
    public String updateState(@RequestBody Map<String,Object> param){
        int teamTodoId = Integer.valueOf(param.get("teamTodoId").toString());
        int todoStatusId = Integer.valueOf(param.get("todoStatusId").toString());
        if (teamTodoService.updateState(teamTodoId,todoStatusId)){
            return "updateState-success";
        }
        return "updateState-fail";
    }
}
