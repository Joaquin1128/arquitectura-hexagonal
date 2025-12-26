package com.example.arquitecturahexagonal.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.example.arquitecturahexagonal.domain.model.User;

public interface UserRepositoryPort {

    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

}
