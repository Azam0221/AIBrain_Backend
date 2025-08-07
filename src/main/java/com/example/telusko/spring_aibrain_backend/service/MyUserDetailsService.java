package com.example.telusko.spring_aibrain_backend.service;

import com.example.telusko.spring_aibrain_backend.UserPrincipal;
import com.example.telusko.spring_aibrain_backend.model.User;
import com.example.telusko.spring_aibrain_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            System.out.println("User not found");
            throw  new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}
