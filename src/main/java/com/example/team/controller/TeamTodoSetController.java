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
public class TeamTodoSetController extends BaseController {

    @Autowired
    private TeamTodoSetService teamTodoSetService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 获取团队待办集集合
     * @Param: [param]
     * @return: java.util.List<com.example.team.pojo.TeamTodoSet>
     * @update: time: 2020/6/3 9:25
     */
    private List<TeamTodoSet> list(@RequestBody Map<String, Object> param) {
        int teamId = Integer.parseInt(param.get("teamId").toString());
        return teamTodoSetService.listByTeamId(teamId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 获取团队待办集
     * @Param: [param]
     * @return: com.example.team.pojo.TeamTodoSet
     * @update: time: 2020/6/3 9:25
     */
    private TeamTodoSet getTeamTodoSet(@RequestBody Map<String, Object> param) {
        int teamTodoSetId = Integer.parseInt(param.get("teamTodoSetId").toString());
        return teamTodoSetService.getById(teamTodoSetId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 创建团队待办集
     * @Param: [param]
     * @return: java.lang.String
     * @update: time: 2020/6/3 9:25
     */
    private String createTeamTodoSet(@RequestBody Map<String, Object> param) {
        String name = param.get("name").toString();
        TeamTodoSet teamTodoSet = new TeamTodoSet();
        teamTodoSet.setName(name);
        teamTodoSet.setTeamId(Integer.parseInt(param.get("teamId").toString()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date create = new java.util.Date();
        teamTodoSet.setCreate(Date.valueOf(df.format(create)));
        if (teamTodoSetService.createTeamTodoSet(teamTodoSet)) {
            TeamTodoSet teamTodoSet1 = teamTodoSetService.getByName(name);
            return "create-success,teamTodoSetId:"+teamTodoSet1.getTeamTodoSetId();
        }
        return "create-fail";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 更新团队待办集
     * @Param: [param]
     * @return: java.lang.String
     * @update: time: 2020/6/3 9:25
     */
    private String updateTeamTodoSet(@RequestBody Map<String, Object> param) {
        TeamTodoSet teamTodoSet = new TeamTodoSet();
        teamTodoSet.setName(param.get("name").toString());
        teamTodoSet.setTeamId(Integer.parseInt(param.get("teamId").toString()));
        teamTodoSet.setTeamTodoSetId(Integer.parseInt(param.get("teamTodoSetId").toString()));
        teamTodoSet.setCreate(Date.valueOf(param.get("create").toString()));
        if (teamTodoSetService.updateTeamTodoSet(teamTodoSet)) {
            TeamTodoSet teamTodoSet1 = teamTodoSetService.getByName(teamTodoSet.getName());
            return "update-success,teamTodoSetId:" + teamTodoSet1.getTeamTodoSetId();
        }
        return "update-fail";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 删除团队待办集
     * @Param: [param]
     * @return: java.lang.String
     * @update: time: 2020/6/3 9:25
     */
    private String deleteTeamTodoSet(@RequestBody Map<String, Object> param) {
        int teamTodoSetId = Integer.parseInt(param.get("teamTodoSetId").toString());
        teamTodoSetService.deleteTeamTodoSet(teamTodoSetId);
        return "delete-success";
    }
}


