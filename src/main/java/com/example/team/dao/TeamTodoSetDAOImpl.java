package com.example.team.dao;

import com.example.team.pojo.TeamTodoSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("teamTodoSetDAO")
@Transactional(rollbackFor = Exception.class)
public class TeamTodoSetDAOImpl extends BaseDAOImpl<TeamTodoSet> implements TeamTodoSetDAO {

    @Override
    public TeamTodoSet getByName(String name) {
        String hql = "from TeamTodoSet where name=:name";
        return (TeamTodoSet) getSession().createQuery(hql).setParameter("name", name).uniqueResult();
    }

    @Override
    public List<TeamTodoSet> list(int teamId) {
        String hql = "from TeamTodoSet where teamId=:teamId";
        return (List<TeamTodoSet>) getSession().createQuery(hql).setParameter("teamId", teamId).list();
    }
}
