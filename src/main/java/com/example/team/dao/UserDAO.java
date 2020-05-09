package com.example.team.dao;


import com.example.team.pojo.User;

public interface UserDAO {
    int add(User user);
    void delete(int userId);
    void update(User user);

    User getById(int userId);
    User getByName(String name);
    User getByTel(String tel);
    User getByEmail(String email);
}
