package com.example.team.pojo;


import javax.persistence.*;

@Entity
@Table(name="petstatus")
public class PetStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pet_status_id")
    private int petStatusId;
    private String status;
}
