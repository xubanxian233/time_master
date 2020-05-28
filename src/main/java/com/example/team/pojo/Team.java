package com.example.team.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private int teamId;

    private String name;

    private int leader;

    private Date createDate;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getLeader(){
        return leader;
    }

    public void setLeader(int leader){
        this.leader=leader;
    }

    public Date getCreateDate(){
        return createDate;
    }

    public void setCreateDate(Date createDate){
        this.createDate=createDate;
    }
}
