package com.example.telusko.spring_aibrain_backend.service;

import com.example.telusko.spring_aibrain_backend.model.User;
import com.example.telusko.spring_aibrain_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    AuthenticationManager authManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);


    }

    public String verify(User user){
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){

        }


        return "Login Successful";
    }

}
