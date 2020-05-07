package com.example.team.service;

import com.example.team.dao.UserTodoDAO;
import com.example.team.dao.UserTodoSetDAO;
import com.example.team.pojo.UserTodo;
import com.example.team.pojo.UserTodoSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service("userTodoSetService")
@Transactional(rollbackFor = Exception.class)
public class UserTodoSetServiceImpl implements UserTodoSetService {

    @Autowired
    private UserTodoSetDAO userTodoSetDAO;
    @Autowired
    private UserTodoDAO userTodoDAO;

    @Override
    public void createUserTodoSet(UserTodoSet userTodoSet) {
        userTodoSetDAO.add(userTodoSet);
    }

    @Override
    public void updateUserTodoSet(UserTodoSet userTodoSet) {
        userTodoSetDAO.update(userTodoSet);
    }

    @Override
    public void deleteUserTodoSet(int userTodoSetId) {
        int userId = userTodoSetDAO.getById(userTodoSetId).getUserId();
        List<UserTodo> list = userTodoDAO.listByUser(userId,userTodoSetId);
        for (int i = 0; i < list.size(); i++) {
            UserTodo userTodo = list.get(i);
            userTodoDAO.delete(userTodo.getUserTodoId());
        }
        userTodoSetDAO.delete(userTodoSetId);
    }

    @Override
    public UserTodoSet getById(int userTodoSetId) {
        return userTodoSetDAO.getById(userTodoSetId);
    }

    @Override
    public List<UserTodoSet> listByUserId(int userTodoSetId) {
        return userTodoSetDAO.listById(userTodoSetId);
    }
}
