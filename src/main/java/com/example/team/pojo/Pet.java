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

    private int level;


    @OneToOne(mappedBy = "pet",cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
