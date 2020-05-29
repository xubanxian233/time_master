package com.example.team.service;

import com.example.team.dao.UserTodoDAO;
import com.example.team.pojo.UserTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userTodoService")
@Transactional(rollbackFor = Exception.class)
public class UserTodoServiceImpl implements UserTodoService {
    @Autowired
    private UserTodoDAO userTodoDAO;

    @Override
    public boolean createUserTodo(UserTodo userTodo) {
        UserTodo userTodo1 = userTodoDAO.getByName(userTodo.getName());
        if (userTodo1==null){
            userTodoDAO.add(userTodo);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserTodo(UserTodo userTodo) {
        UserTodo userTodo1 = userTodoDAO.getByName(userTodo.getName());
        if (userTodo1==null||userTodo1.getUserTodoId()==userTodo.getUserTodoId()){
            userTodoDAO.update(userTodo);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateState(int userTodoId, int todoStatusId) {
        userTodoDAO.updateState(userTodoId,todoStatusId);
        return true;
    }

    @Override
    public void deleteUserTodo(int userTodoId) {
        userTodoDAO.delete(userTodoId);
    }

    @Override
    public UserTodo getById(int userTodoId) {
        return userTodoDAO.getById(userTodoId);
    }

    @Override
    public UserTodo getByName(String name) {
        return userTodoDAO.getByName(name);
    }

    @Override
    public List<UserTodo> listUserTodo(int userId) {
        return userTodoDAO.listByUser(userId);
    }

    @Override
    public List<UserTodo> listUserTodo(int userTodoSetId, int userId) {
        return userTodoDAO.listByUser(userId,userTodoSetId);
    }

    @Override
    public void updateSchedule() {
        userTodoDAO.updateSchedule();
    }
}
