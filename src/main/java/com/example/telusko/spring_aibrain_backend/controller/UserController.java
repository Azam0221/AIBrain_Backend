package com.example.telusko.spring_aibrain_backend.controller;


import com.example.telusko.spring_aibrain_backend.model.User;
import com.example.telusko.spring_aibrain_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserService userService;




    @PostMapping("/userLogin")
    public String userLogin(@RequestBody User user){
        return userService.verify(user);
    }

    @PostMapping("/userRegister")
    public User userRegister(@RequestBody User user){
        System.out.println("Registration....");
         return userService.register(user);
    }
}
