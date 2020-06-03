package com.example.team.pojo;


import javax.persistence.*;

@Entity
@Table(name = "achievementtype")
public class AchievementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="achievement_id")
    private int aId;
    @Column(name="achievement_name")
    private String  aname;
    @Column(name = "a_introduction")
    private String aintro;
    @Column(name = "a_status")
    private int astatus;

    public int getAId() {
        return aId;
    }

    public String getAname() {
        return aname;
    }

    public String getAintro() {
        return aintro;
    }

    public int getAstatus() {
        return astatus;
    }

    public void setAId(int aId) {
        this.aId = aId;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setAintro(String aintro) {
        this.aintro = aintro;
    }

    public void setAstatus(int astatus) {
        this.astatus = astatus;
    }
}
