package com.example.arquitecturahexagonal.infrastructure.adapter.persistence;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.arquitecturahexagonal.domain.model.User;
import com.example.arquitecturahexagonal.domain.port.out.UserRepositoryPort;

@Repository
public class InMemoryUserRepository implements UserRepositoryPort {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            Long newId = idCounter.getAndIncrement();
            user.setId(newId);
        }
        
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

}
