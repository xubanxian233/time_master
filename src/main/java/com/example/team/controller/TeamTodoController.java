package com.example.team.controller;

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

    @RequestMapping(value = "/listByTeamId",method = RequestMethod.POST)
    @ResponseBody
    private List<TeamTodo> listByTeamId(@RequestBody Map<String,Object> param){
        Integer teamId = Integer.valueOf(param.get("teamId").toString());
        return teamTodoService.listTeamTodo(teamId);
    }

    @RequestMapping(value = "/listById",method = RequestMethod.POST)
    @ResponseBody
    private List<TeamTodo> listById(@RequestBody Map<String,Object> param){
        Integer teamId = Integer.valueOf(param.get("teamId").toString());
        Integer teamTodoSetId = Integer.valueOf(param.get("teamTodoSetId").toString());
        return teamTodoService.listTeamTodo(teamTodoSetId,teamId);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    private TeamTodo getTeamTodo(@RequestBody Map<String,Object> param){
        Integer teamTodoId = Integer.valueOf(param.get("teamTodoId").toString());
        return teamTodoService.getById(teamTodoId);
    }

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
            return "create-success";
        }
        return "create-fail";
    }

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

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    private String deleteTeamTodo(@RequestBody Map<String,Object> param){
        Integer teamTodoSetId = Integer.valueOf(param.get("teamTodoSetId").toString());
        teamTodoService.deleteTeamTodo(teamTodoSetId);
        return "delete-success";
    }

}
