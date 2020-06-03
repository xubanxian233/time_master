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
    private int aname;
    @Column(name = "a_introduction")
    private String aintro;
    @Column(name = "a_condition")
    private String acondition;

    public int getaId() {
        return aId;
    }

    public int getAname() {
        return aname;
    }

    public String getAintro() {
        return aintro;
    }

    public String getAcondition() {
        return acondition;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public void setAname(int aname) {
        this.aname = aname;
    }

    public void setAintro(String aintro) {
        this.aintro = aintro;
    }

    public void setAcondition(String acondition) {
        this.acondition = acondition;
    }
}
