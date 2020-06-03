package com.example.team.dao;


import com.example.team.pojo.User;


public interface UserDAO extends BaseDAO<User>{

    User getByName(String name);

    User getByTel(String tel);

    User getByEmail(String email);
}
