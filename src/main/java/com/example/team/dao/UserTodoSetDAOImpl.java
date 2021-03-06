package com.example.team.dao;

import com.example.team.pojo.UserTodoSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "UserTodoSetDAO")
@Transactional(rollbackFor = Exception.class)
public class UserTodoSetDAOImpl extends BaseDAOImpl<UserTodoSet> implements UserTodoSetDAO {

    @Override
    public UserTodoSet getByName(String name) {
        String hql = "from UserTodoSet where name=:name";
        return (UserTodoSet) getSession().createQuery(hql).setParameter("name", name).uniqueResult();
    }

    @Override
    public List<UserTodoSet> listById(int userId) {
        String hql = "from UserTodoSet where userId=:userId";
        return (List<UserTodoSet>) getSession().createQuery(hql).setParameter("userId", userId).list();
    }
}
