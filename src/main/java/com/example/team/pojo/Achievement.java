package com.example.team.pojo;

import javax.persistence.*;

@Entity
@Table(name = "achievement")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="type")
    private int type;
    @Column(name="user_id")
    private int userId;
    @Column(name = "a_create")
    private String create;
    @Column(name = "a_status")
    private int status;

    public int getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public String getCreate() {
        return create;
    }

    public int getStatus() {
        return status;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
