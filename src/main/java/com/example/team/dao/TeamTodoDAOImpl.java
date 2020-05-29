package com.example.team.dao;

import com.example.team.pojo.TeamTodo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository("teamTodoDAO")
@Transactional(rollbackFor = Exception.class)
public class TeamTodoDAOImpl implements TeamTodoDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(TeamTodo teamTodo) {
        getSession().save(teamTodo);
    }

    @Override
    public void delete(int teamTodoId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from TeamTodo where teamTodoId=:teamTodoId";
        TeamTodo teamTodo = (TeamTodo) session.createQuery(hql).setParameter("teamTodoId",teamTodoId).uniqueResult();
        session.delete(teamTodo);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteByUser(String name, int userId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from TeamTodo where name = :name and userId = :userId";
        TeamTodo teamTodo = (TeamTodo) session.createQuery(hql).setParameter("name",name).setParameter("userId",userId).uniqueResult();
        session.delete(teamTodo);
        tx.commit();
        session.close();
    }

    @Override
    public void update(TeamTodo teamTodo) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(teamTodo);
        tx.commit();
        session.close();
    }

    @Override
    public void updateState(int teamTodoId, int todoStatusId,int userId) {
        Session session=getSession();
        Transaction tx=session.beginTransaction();
        String hqlUpdate = "update TeamTodo as t set todoStatusId = :status where teamTodoId = :teamTodoId and userId = :userId";
        int updatedEntities = session.createQuery( hqlUpdate )
                .setParameter( "status", todoStatusId )
                .setParameter( "teamTodoId", teamTodoId )
                .setParameter("userId",userId)
                .executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public TeamTodo getById(int teamTodoId) {
        String hql = "from TeamTodo where teamTodoId=:teamTodoId";
        return (TeamTodo) getSession().createQuery(hql).setParameter("teamTodoId",teamTodoId).uniqueResult();
    }

    @Override
    public TeamTodo getByUser(String name,int userId) {
        String hql = "from TeamTodo where name=:name and userId=:userId";
        return (TeamTodo) getSession()
                .createQuery(hql)
                .setParameter("name",name)
                .setParameter("userId",userId)
                .uniqueResult();
    }

    @Override
    public List<TeamTodo> listByUser(int teamId,int userId) {
        String hql = "from TeamTodo where teamId=:teamId and userId=:userId";
        return (List<TeamTodo>) getSession().createQuery(hql).setParameter("teamId",teamId).setParameter("userId",userId).list();
    }

    @Override
    public List<TeamTodo> list(int teamId, int teamTodoSetId) {
        String hql = "from TeamTodo where teamId=:teamId and teamTodoSetId=:teamTodoSetId";
        return (List<TeamTodo>) getSession()
                .createQuery(hql)
                .setParameter("teamId",teamId)
                .setParameter("teamTodoSetId",teamTodoSetId)
                .list();
    }

    @Override
    public List<TeamTodo> list(int teamId, int teamTodoSetId,int userId) {
        String hql = "from TeamTodo where teamId=:teamId and teamTodoSetId=:teamTodoSetId and userId=:userId";
        return (List<TeamTodo>) getSession()
                .createQuery(hql)
                .setParameter("teamId",teamId)
                .setParameter("teamTodoSetId",teamTodoSetId)
                .setParameter("userId",userId)
                .list();
    }

    @Override
    public void updateSchedule() {
        Session session=getSession();
        Transaction tx=session.beginTransaction();
        String hqlUpdate = "update TeamTodo as t set todoStatusId = :status where todoStatusId != :oldStatus";
        int updatedEntities = session.createQuery( hqlUpdate )
                .setParameter( "status", 1 )
                .setParameter( "oldStatus", 1 )
                .executeUpdate();
        tx.commit();
        session.close();
    }
}
