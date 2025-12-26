package com.example.arquitecturahexagonal.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.arquitecturahexagonal.domain.model.User;
import com.example.arquitecturahexagonal.domain.port.in.GetUsersUseCase;
import com.example.arquitecturahexagonal.domain.port.out.UserRepositoryPort;

@Service
public class GetUsersService implements GetUsersUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public GetUsersService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }

}
