package com.example.team.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "todostatus")
public class TodoStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="todo_status_id")
    private int todoStatusId;
    private String status;

    public int getTodoStatusId() {
        return todoStatusId;
    }

    public void setTodoStatusId(int todoStatusId) {
        this.todoStatusId = todoStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
