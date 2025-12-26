package com.example.arquitecturahexagonal.domain.port.in;

import java.util.List;

import com.example.arquitecturahexagonal.domain.model.User;

public interface GetUsersUseCase {
    
    List<User> getAllUsers();

}
