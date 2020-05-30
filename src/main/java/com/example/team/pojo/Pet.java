package com.example.team.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private int petId;
    private String name;

    private int sex;

    private Date birth;

    @Column(name = "status")
    @JsonIgnore
    private int petStatusId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status", insertable = false, updatable = false)
    private PetStatus petStatus;

    private int level;

    @Column(name = "user")
    @JsonIgnore
    private int userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @Column(name = "skin")
    @JsonIgnore
    private int skinId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skin", insertable = false, updatable = false)
    private Skin skin;

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getPetStatusId() {
        return petStatusId;
    }

    public void setPetStatusId(int petStatusId) {
        this.petStatusId = petStatusId;
    }

    public PetStatus getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(PetStatus petStatus) {
        this.petStatus = petStatus;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSkinId() {
        return skinId;
    }

    public void setSkinId(int skinId) {
        this.skinId = skinId;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }
}
