package com.example.team.dao;

import com.example.team.pojo.Team;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("teamDAO")
@Transactional(rollbackFor = Exception.class)
public class TeamDAOImpl extends BaseDAOImpl<Team> implements TeamDAO {

}
