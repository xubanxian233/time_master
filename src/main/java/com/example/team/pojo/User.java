package com.example.team.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    private String tel;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private String sex;


    @Column(name = "create_date")
    private Date create;
    @JsonIgnore
    @Column(name = "pet")
    private int petId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet", insertable = false, updatable = false)
    @JsonIgnore
    private Pet Pet;

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public com.example.team.pojo.Pet getPet() {
        return Pet;
    }

    public void setPet(com.example.team.pojo.Pet pet) {
        Pet = pet;
    }
}
