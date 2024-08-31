package com.example.Schemone.service;

import com.example.Schemone.domain.User;
import com.example.Schemone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Userを扱うサービスクラス.
 *
 * @author tuguk
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User load(Integer id){
        return userRepository.load(id);
    }
}
