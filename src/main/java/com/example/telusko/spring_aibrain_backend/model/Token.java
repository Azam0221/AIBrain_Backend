package com.example.telusko.spring_aibrain_backend.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    private boolean expired;
    private boolean revoked;

    @ManyToOne
    private User user;

}
