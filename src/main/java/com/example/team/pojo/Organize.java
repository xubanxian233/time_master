package com.example.team.pojo;

import javax.persistence.*;

@Entity
@Table(name = "organize")
public class Organize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="organize_id")
    private int organizeId;

    @Column(name="user")
    private int userId;

    @Column(name="team")
    private int teamId;

    public int getOrganizeId(){
        return organizeId;
    }

    public void setOrganizeId(int organizeId){
        this.organizeId=organizeId;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId=userId;
    }

    public int getTeamId(){
        return teamId;
    }

    public void setTeamId(int teamId){
        this.teamId=teamId;
    }
}
