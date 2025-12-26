package com.example.arquitecturahexagonal.infrastructure.adapter.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.example.arquitecturahexagonal.domain.model.User;
import com.example.arquitecturahexagonal.domain.port.out.UserRepositoryPort;

//@Repository
public class FileUserRepository implements UserRepositoryPort {

    private static final String FILE_PATH = "users.dat";
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public User save(User user) {
        List<User> users = loadUsers();
        
        if (user.getId() == null) {
            user.setId(idCounter.getAndIncrement());
        }
        
        users.removeIf(u -> u.getId().equals(user.getId()));
        users.add(user);
        
        saveUsers(users);
        return user;
    }

    @Override
    public List<User> findAll() {
        return loadUsers();
    }

    @Override
    public Optional<User> findById(Long id) {
        return loadUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @SuppressWarnings("unchecked")
    private List<User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar usuarios", e);
        }
    }

    private void saveUsers(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(users);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar usuarios", e);
        }
    }

}
