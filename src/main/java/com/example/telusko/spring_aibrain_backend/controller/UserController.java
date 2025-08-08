package com.example.telusko.spring_aibrain_backend.controller;


import com.example.telusko.spring_aibrain_backend.model.AuthResponse;
import com.example.telusko.spring_aibrain_backend.model.LoginRequest;
import com.example.telusko.spring_aibrain_backend.model.RegisterRequest;
import com.example.telusko.spring_aibrain_backend.model.User;
import com.example.telusko.spring_aibrain_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private AuthService authService;


    @PostMapping("/userLogin")
    public AuthResponse userLogin(@RequestBody LoginRequest loginRequest){
        return authService.loginVerify(loginRequest).getBody();
    }

    @PostMapping("/userRegister")
    public ResponseEntity<AuthResponse> userRegister(@RequestBody RegisterRequest registerRequest){
        System.out.println("Registration....");

         return authService.register(registerRequest);
    }
}
