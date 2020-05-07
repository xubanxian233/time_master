package com.example.team.service;

import com.example.team.dao.TeamTodoDAO;
import com.example.team.pojo.TeamTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("TeamTodoService")
@Transactional(rollbackFor = Exception.class)
public class TeamTodoServiceImpl implements TeamTodoService {
    @Autowired
    private TeamTodoDAO teamTodoDAO;

    @Override
    public boolean createTeamTodo(TeamTodo teamTodo) {
        TeamTodo teamTodo1 = teamTodoDAO.getByName(teamTodo.getName());
        if (teamTodo1==null){
            teamTodoDAO.add(teamTodo);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeamTodo(TeamTodo teamTodo) {
        TeamTodo teamTodo1 = teamTodoDAO.getByName(teamTodo.getName());
        if (teamTodo1==null){
            teamTodoDAO.update(teamTodo);
            return true;
        }
        return false;
    }

    @Override
    public void deleteTeamTodo(int teamTodoId) {
        teamTodoDAO.delete(teamTodoId);
    }

    @Override
    public TeamTodo getById(int teamTodoId) {
        return teamTodoDAO.getById(teamTodoId);
    }

    @Override
    public List<TeamTodo> listTeamTodo(int teamId) {
        return teamTodoDAO.list(teamId);
    }

    @Override
    public List<TeamTodo> listTeamTodo(int teamTodoSetId, int teamId) {
        return teamTodoDAO.list(teamId,teamTodoSetId);
    }
}
