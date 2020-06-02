package com.example.team.pojo;

import javax.persistence.*;

@Entity
@Table(name = "skin")
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skin_id")
    private int skinId;

    public int getSkinId() {
        return skinId;
    }

    public void setSkinId(int skinId) {
        this.skinId = skinId;
    }
}
