package com.example.team.controller;

import com.example.team.pojo.*;
import com.example.team.service.TeamService;
import com.example.team.service.TeamTodoService;
import com.example.team.service.TeamTodoSetService;
import com.example.team.service.UserService;
import com.example.team.util.DataVo;
import com.example.team.util.ExcelWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@Controller
@RequestMapping("/team")
public class TeamController extends BaseController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamTodoService teamTodoService;
    @Autowired
    private TeamTodoSetService teamTodoSetService;

    /**
     * createTeam 创建团队
     *
     * @param param
     * @return Team
     */
    @RequestMapping(value = "/createTeam", method = RequestMethod.POST)
    @ResponseBody
    public Team createTeam(@RequestBody Map<String, Object> param) {
        String name = param.get("name").toString();
        int userId = Integer.parseInt(request.getHeader("id"));
        Team team = new Team();
        User user = userService.getById(userId);
        team.setName(name);
        team.setCreateDate(new Date(new java.util.Date().getTime()));
        team.setLeader(user);
        return teamService.createTeam(user, team);
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
     * @return Team
     */
    @RequestMapping(value = "/joinTeam", method = RequestMethod.POST)
    @ResponseBody
    public Team joinTeam(@RequestBody Map<String, Object> param) {
        int teamId = Integer.parseInt(param.get("teamId").toString());
        int userId = Integer.parseInt(request.getHeader("id"));
        return userService.joinTeam(teamId, userId);
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
        Team team=userService.joinTeam(teamId, userId);
        if (team != null) {
            if(team.getTeamId()==-1){
                return "invited";
            }
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
    @RequestMapping(value = "/getTeams", method = RequestMethod.GET)
    @ResponseBody
    public Set<Team> getTeams() {
        int userId = Integer.parseInt(request.getHeader("id"));
        return userService.getTeams(userId);
    }

    /**
     * updateTeam 获取用户所有团队
     *
     * @param
     * @return String
     */
    @RequestMapping(value = "/updateTeam", method = RequestMethod.POST)
    @ResponseBody
    public String updateTeam(@RequestBody Map<String, Object> param) {
        String name = param.get("name").toString();
        int teamId = Integer.parseInt(param.get("teamId").toString());
        Team team = new Team();
        team.setName(name);
        team.setTeamId(teamId);
        teamService.updateTeam(team);
        return "update-success";
    }

    /**
     * getRecords 获取用户待办和待办集使用情况
     *
     * @param param 选择项（option），团队ID（teamId），地址（path）
     * @return String
     */
    @RequestMapping(value = "/getRecords", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getRecords(@RequestBody Map<String, Object> param,@RequestHeader("id") int userId){
        int todoNum = 0;
        int todoSetNum = 0;
        String records = "";
        int option = Integer.parseInt(param.get("option").toString());
        int teamId = Integer.parseInt(param.get("teamId").toString());
        String path = param.get("path").toString();
        List<TeamTodo> teamTodoList = teamTodoService.listByUser(teamId,userId);
        List<TeamTodoSet> teamTodoSetList = teamTodoSetService.listByTeamId(teamId);
        List<DataVo> dataVOList = new ArrayList<>();
        if (option == 1){
            DataVo dataVO = new DataVo();
            for (TeamTodo list : teamTodoList){
                if (list.getTodoStatusId()==2){
                    todoNum++;
                }
            }
            dataVO.setName("Todo");
            dataVO.setCompletion(todoNum + "/" + teamTodoList.size());
            //records += "Todo:" + todoNum + "/" + teamTodoList.size() + "\r\n";
            DataVo dataVO2 = new DataVo();
            for (TeamTodoSet set : teamTodoSetList){
                int i = 0;
                List<TeamTodo> todoList = teamTodoService.listTeamTodo(set.getTeamTodoSetId(),teamId,userId);
                for (TeamTodo list : todoList){
                    if (list.getTodoStatusId()==2){
                        i++;
                    }
                }
                if (i == todoList.size()){
                    todoSetNum++;
                }
            }
            dataVO2.setSName("TodoSet");
            dataVO2.setCompletion(todoSetNum + "/" + teamTodoSetList.size());
            //records += "TodoSet:" + todoSetNum + "/" + teamTodoSetList.size();
            dataVOList.add(dataVO2);
            dataVOList.add(dataVO);
            records = "操作1";
        }
        else if (option == 2){
            for (TeamTodoSet set : teamTodoSetList){
                DataVo dataVO = new DataVo();
                int i = 0;
                List<TeamTodo> todoList = teamTodoService.listTeamTodo(set.getTeamTodoSetId(),teamId,userId);
                for (TeamTodo list : todoList){
                    DataVo dataVO1 = new DataVo();
                    if (list.getTodoStatusId()==2){
                        dataVO1.setName(list.getName());
                        dataVO1.setSName(set.getName());
                        dataVO1.setRecord(1);
                        i++;
                    }
                    else {
                        dataVO1.setName(list.getName());
                        dataVO1.setSName(set.getName());
                        dataVO1.setRecord(0);
                    }
                    dataVOList.add(dataVO1);
                }
                if (i == todoList.size()){
                    dataVO.setSName(set.getName());
                    dataVO.setRecord(1);
                }
                else {
                    dataVO.setSName(set.getName());
                    dataVO.setRecord(0);
                }
                dataVOList.add(dataVO);
            }
            records = "操作2";
        }
        else {
            records = "option输入错误";
        }
        // 写入数据到工作簿对象内
        Workbook workbook = ExcelWriter.exportData(dataVOList);
        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            //E:\files\write-01.xlsx  不用创建，会自动生成的
            String exportFilePath = path + "\\write-01.xlsx";
            File exportFile = new File(exportFilePath);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }
            fileOut = new FileOutputStream(exportFilePath);
            workbook.write(fileOut);
            fileOut.flush();
        } catch (Exception e) {
            //logger.warn("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                //logger.warn("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
        }
        return records;
    }
}
