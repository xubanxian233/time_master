package com.example.team.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="usertodoset")
public class UserTodoSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_todo_set_id")
    private int userTodoSetId;
    private String name;
    @Column(name = "create_date")
    private Date create;
    @Column(name="user")
    private int userId;

    public int getUserTodoSetId() {
        return userTodoSetId;
    }

    public void setUserTodoSetId(int userTodoSetId) {
        this.userTodoSetId = userTodoSetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
