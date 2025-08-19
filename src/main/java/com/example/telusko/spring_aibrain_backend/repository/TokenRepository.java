package com.example.telusko.spring_aibrain_backend.repository;


import com.example.telusko.spring_aibrain_backend.model.Token;
import com.example.telusko.spring_aibrain_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {

    List<Token> findAllValidTokensByUser(User user);
}
