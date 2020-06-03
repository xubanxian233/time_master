package com.example.team.pojo;

import javax.persistence.*;

@Entity
@Table(name = "achievement")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="achievement_id")
    private int aId;
    @Column(name="user_id")
    private int userId;
    @Column(name = "a_create")
    private String create;
    @Column(name = "a_status")
    private String status;

    public int getaId() {
        return aId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCreate() {
        return create;
    }

    public String getStatus() {
        return status;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
