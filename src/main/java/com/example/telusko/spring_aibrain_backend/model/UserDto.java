package com.example.telusko.spring_aibrain_backend.model;


import lombok.Data;

@Data

public class UserDto {
    private int id;
    private String name;
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
