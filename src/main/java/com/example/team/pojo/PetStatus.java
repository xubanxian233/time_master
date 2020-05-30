package com.example.team.pojo;


import javax.persistence.*;

@Entity
@Table(name = "petstatus")
public class PetStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_status_id")
    private int petStatusId;
    private String status;

    public int getPetStatusId() {
        return petStatusId;
    }

    public void setPetStatusId(int petStatusId) {
        this.petStatusId = petStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
