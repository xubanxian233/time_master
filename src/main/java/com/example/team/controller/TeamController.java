package com.example.team.controller;

import com.example.team.pojo.Team;
import com.example.team.pojo.User;
import com.example.team.pojo.UserTodo;
import com.example.team.service.TeamService;
import com.example.team.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/team")
public class TeamController extends BaseController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;

    /**
     * createTeam 创建团队
     *
     * @param param
     * @return String
     */
    @RequestMapping(value = "/createTeam", method = RequestMethod.POST)
    @ResponseBody
    public String createTeam(@RequestBody Map<String, Object> param) {
        String name = param.get("name").toString();
        int userId = Integer.parseInt(request.getHeader("id"));
        Team team = new Team();
        User user = userService.getById(userId);
        team.setName(name);
        team.setCreateDate(new Date(new java.util.Date().getTime()));
        team.setLeader(user);
        teamService.createTeam(user, team);
        return "create-success";
    }

    /**
     * deleteTeam 解散团队
     *
     * @param param
     * @return String
     */
    @RequestMapping(value = "/deleteTeam", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTeam(@RequestBody Map<String, Object> param) {
        int teamId = Integer.parseInt(param.get("teamId").toString());
        teamService.deleteTeam(teamId);
        return "delete-success";
    }

    /**
     * joinTeam 加入团队
     *
     * @param param
     * @return String
     */
    @RequestMapping(value = "/joinTeam", method = RequestMethod.POST)
    @ResponseBody
    public String joinTeam(@RequestBody Map<String, Object> param) {
        int teamId = Integer.parseInt(param.get("teamId").toString());
        int userId = Integer.parseInt(request.getHeader("id"));
        if (userService.joinTeam(teamId, userId)) {
            return "join-success";
        }
        return "join-fail";
    }

    /**
     * quitTeam 退出团队
     *
     * @param param
     * @return String
     */
    @RequestMapping(value = "/quitTeam", method = RequestMethod.POST)
    @ResponseBody
    public String quitTeam(@RequestBody Map<String, Object> param) {
        int teamId = Integer.parseInt(param.get("teamId").toString());
        int userId = Integer.parseInt(request.getHeader("id"));
        if (userService.quitTeam(teamId, userId)) {
            return "quit-success";
        }
        return "quit-fail";
    }

    /**
     * inviteMember 邀请成员
     *
     * @param param
     * @return String
     */
    @RequestMapping(value = "/inviteMember", method = RequestMethod.POST)
    @ResponseBody
    public String inviteMember(@RequestBody Map<String, Object> param) {
        int teamId = Integer.parseInt(param.get("teamId").toString());
        String email = param.get("email").toString();
        int userId = userService.getUserId("", email, "");
        if (userService.joinTeam(teamId, userId)) {
            return "invite-success";
        }
        return "invite-fail";
    }

    /**
     * outMember 踢出成员
     *
     * @param param
     * @return String
     */
    @RequestMapping(value = "/outMember", method = RequestMethod.POST)
    @ResponseBody
    public String outMember(@RequestBody Map<String, Object> param) {
        int teamId = Integer.parseInt(param.get("teamId").toString());
        String email = param.get("email").toString();
        int userId = userService.getUserId("", email, "");
        if (userService.quitTeam(teamId, userId)) {
            return "out-success";
        }
        return "out-fail";
    }

    /**
     * getMembers 获取团队所有成员
     *
     * @param teamId
     * @return String
     */
    @RequestMapping(value = "/getMembers", method = RequestMethod.GET)
    @ResponseBody
    public Set<User> getMembers(@RequestParam String teamId) {
        int teamId1 = Integer.parseInt(teamId);
        return userService.getMembers(teamId1);
    }

    /**
     * getTeams 获取用户所有团队
     *
     * @param
     * @return String
     */
    @RequestMapping(value = "/getTeams",method = RequestMethod.GET)
    @ResponseBody
    public Set<Team> getTeams() {
        int userId = Integer.parseInt(request.getHeader("id"));
        return userService.getTeams(userId);
    }
}
