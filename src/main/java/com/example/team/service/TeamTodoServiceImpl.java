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
        TeamTodo teamTodo1 = teamTodoDAO.getByUser(teamTodo.getName(), teamTodo.getUserId());
        if (teamTodo1 == null || teamTodo.getUserId() != teamTodo1.getUserId()) {
            teamTodoDAO.add(teamTodo);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTeamTodo(TeamTodo teamTodo) {
        TeamTodo teamTodo1 = teamTodoDAO.getByUser(teamTodo.getName(), teamTodo.getUserId());
        if (teamTodo1 == null || teamTodo.getUserId() != teamTodo1.getUserId()) {
            teamTodoDAO.update(teamTodo);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateState(int teamTodoId, int todoStatusId, int userId) {
        if (teamTodoDAO.get(TeamTodo.class,teamTodoId).getTodoStatus().getTodoStatusId() != 2) {
            teamTodoDAO.updateState(teamTodoId, todoStatusId, userId);
        }
        return true;
    }

    @Override
    public void deleteTeamTodo(int teamTodoId) {
        teamTodoDAO.delete(teamTodoId);
    }

    @Override
    public void deleteByUser(String name, int userId) {
        teamTodoDAO.deleteByUser(name, userId);
    }

    @Override
    public TeamTodo getById(int teamTodoId) {
        return teamTodoDAO.get(TeamTodo.class,teamTodoId);
    }

    @Override
    public TeamTodo getByUser(String name, int userId) {
        return teamTodoDAO.getByUser(name, userId);
    }

    @Override
    public List<TeamTodo> listByUser(int teamId, int userId) {
        return teamTodoDAO.listByUser(teamId, userId);
    }

    @Override
    public List<TeamTodo> listTeamTodo(int teamTodoSetId, int teamId) {
        return teamTodoDAO.list(teamId, teamTodoSetId);
    }

    @Override
    public List<TeamTodo> listTeamTodo(int teamTodoSetId, int teamId, int userId) {
        return teamTodoDAO.list(teamId, teamTodoSetId, userId);
    }

    @Override
    public void updateSchedule() {
        teamTodoDAO.updateSchedule();
    }
}
