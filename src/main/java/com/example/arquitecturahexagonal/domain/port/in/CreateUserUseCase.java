package com.example.arquitecturahexagonal.domain.port.in;

import com.example.arquitecturahexagonal.domain.model.User;

public interface CreateUserUseCase {
    
    User createUser(String name, String email);

}
