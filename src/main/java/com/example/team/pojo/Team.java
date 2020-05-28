package com.example.team.pojo;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private int teamId;
    private String name;
    @Column(name="create_date")
    private Date createDate;
    @ManyToMany()
    @JoinTable(name="organize"
            ,joinColumns = {@JoinColumn(name="team",referencedColumnName = "team_id")}
            ,inverseJoinColumns = {@JoinColumn(name = "user",referencedColumnName = "user_id")}
    )
    private Set<User> users=new HashSet<User>();
    @ManyToOne()
    @JoinColumn(name="leader")
    private User leader;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
