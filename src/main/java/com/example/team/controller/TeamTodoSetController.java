package com.example.team.controller;

import com.example.team.pojo.TeamTodoSet;
import com.example.team.service.TeamTodoSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teamTodoSet")
public class TeamTodoSetController {

    @Autowired
    private TeamTodoSetService teamTodoSetService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    private List<TeamTodoSet> list(@RequestBody Map<String,Object> param){
        Integer teamId = Integer.valueOf(param.get("teamId").toString());
        return teamTodoSetService.listByTeamId(teamId);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    private TeamTodoSet getTeamTodoSet(@RequestBody Map<String,Object> param){
        Integer teamTodoSetId = Integer.valueOf(param.get("teamTodoSetId").toString());
        return teamTodoSetService.getById(teamTodoSetId);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    private String createTeamTodoSet(@RequestBody Map<String,Object> param){
        TeamTodoSet teamTodoSet = new TeamTodoSet();
        teamTodoSet.setName(param.get("name").toString());
        teamTodoSet.setTeamId(Integer.valueOf(param.get("teamId").toString()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date create = new java.util.Date();
        teamTodoSet.setCreate(Date.valueOf(df.format(create)));
        if (teamTodoSetService.createTeamTodoSet(teamTodoSet)){
            return "create-success";
        }
        return "create-fail";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    private String updateTeamTodoSet(@RequestBody Map<String,Object> param){
        TeamTodoSet teamTodoSet = new TeamTodoSet();
        teamTodoSet.setName(param.get("name").toString());
        teamTodoSet.setTeamId(Integer.valueOf(param.get("teamId").toString()));
        teamTodoSet.setTeamTodoSetId(Integer.valueOf(param.get("teamTodoSetId").toString()));
        teamTodoSet.setCreate(Date.valueOf(param.get("create").toString()));
        if (teamTodoSetService.updateTeamTodoSet(teamTodoSet)){
            return "update-success";
        }
        return "update-fail";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    private String deleteTeamTodoSet(@RequestBody Map<String,Object> param){
        Integer teamTodoSetId = Integer.valueOf(param.get("teamTodoSetId").toString());
        teamTodoSetService.deleteTeamTodoSet(teamTodoSetId);
        return "delete-success";
    }
}


