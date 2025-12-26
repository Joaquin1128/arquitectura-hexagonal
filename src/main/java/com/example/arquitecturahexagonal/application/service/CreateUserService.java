package com.example.arquitecturahexagonal.application.service;

import org.springframework.stereotype.Service;

import com.example.arquitecturahexagonal.domain.model.User;
import com.example.arquitecturahexagonal.domain.port.in.CreateUserUseCase;
import com.example.arquitecturahexagonal.domain.port.out.UserRepositoryPort;

@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUser(String name, String email) {
        User user = new User(null, name, email);
        
        return userRepositoryPort.save(user);
    }
    
}
