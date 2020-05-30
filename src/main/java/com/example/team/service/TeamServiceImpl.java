package com.example.team.service;

import com.example.team.dao.TeamDAO;
import com.example.team.dao.UserDAO;
import com.example.team.pojo.Team;
import com.example.team.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teamService")
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public Team createTeam(User user, Team team) {
        teamDAO.add(team);
        team.getUsers().add(user);
        teamDAO.update(team);
        return team;
    }

    @Override
    public void updateTeam(Team team) {
        Team team1=teamDAO.getByTeamId(team.getTeamId());
        team1.setName(team.getName());
        teamDAO.update(team1);
    }

    @Override
    public void deleteTeam(int teamId) {
        teamDAO.delete(teamId);
    }

}
