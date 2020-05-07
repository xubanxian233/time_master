package com.example.team.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "usertodo")
public class UserTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_todo_id")
    private int userTodoId;
    private String name;
    private long time;
    @Column(name = "create_date")
    private Date create;

    @JsonIgnore
    @Column(name = "status")
    private int todoStatusId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status", insertable = false, updatable = false)
    private TodoStatus todoStatus;

    @Column(name = "todoset")
    private int userTodoSetId;
    @JsonIgnore
    @Column(name = "type")
    private int typeId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type", insertable = false, updatable = false)
    private Type type;

    @Column(name = "user")
    private int userId;

    public int getUserTodoId() {
        return userTodoId;
    }

    public void setUserTodoId(int userTodoId) {
        this.userTodoId = userTodoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public int getTodoStatusId() {
        return todoStatusId;
    }

    public void setTodoStatusId(int todoStatusId) {
        this.todoStatusId = todoStatusId;
    }

    public TodoStatus getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    public int getUserTodoSetId() {
        return userTodoSetId;
    }

    public void setUserTodoSetId(int userTodoSetId) {
        this.userTodoSetId = userTodoSetId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
