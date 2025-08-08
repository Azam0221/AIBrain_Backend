package com.example.telusko.spring_aibrain_backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
}
