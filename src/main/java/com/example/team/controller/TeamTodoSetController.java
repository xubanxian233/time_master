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

    /**
     * list 列出所有的团队待办集
     *
     * @param param 团队ID
     * @return List<TeamTodoSet> 团队待办集
     **/
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    private List<TeamTodoSet> list(@RequestBody Map<String, Object> param) {
        int teamId = Integer.parseInt(param.get("teamId").toString());
        return teamTodoSetService.listByTeamId(teamId);
    }

    /**
     * get 获取某一团队待办集
     *
     * @param param 团队待办集ID
     * @return TeamTodoSet 获取具体团队待办集
     **/
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    private TeamTodoSet getTeamTodoSet(@RequestBody Map<String, Object> param) {
        int teamTodoSetId = Integer.parseInt(param.get("teamTodoSetId").toString());
        return teamTodoSetService.getById(teamTodoSetId);
    }

    /**
     * createTeamTodoSet 创建团队待办集
     *
     * @param param 团队待办集名，团队ID
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
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

    /**
     * update 更新团队待办集
     *
     * @param param 团队待办集名，团队ID，团队待办集ID，创建时间
     * @return String 成功或失败
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
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

    /**
     * delete 删除团队待办集
     *
     * @param param 团队待办集ID
     * @return String 成功
     **/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    private String deleteTeamTodoSet(@RequestBody Map<String, Object> param) {
        int teamTodoSetId = Integer.parseInt(param.get("teamTodoSetId").toString());
        teamTodoSetService.deleteTeamTodoSet(teamTodoSetId);
        return "delete-success";
    }
}


