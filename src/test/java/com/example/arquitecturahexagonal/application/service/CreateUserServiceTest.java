package com.example.arquitecturahexagonal.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.arquitecturahexagonal.domain.model.User;
import com.example.arquitecturahexagonal.domain.port.out.UserRepositoryPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserServiceTest {

    private UserRepositoryPort mockRepository;
    private CreateUserService createUserService;

    @BeforeEach
    void setUp() {
        mockRepository = mock(UserRepositoryPort.class);
        createUserService = new CreateUserService(mockRepository);
    }

    @Test
    void testCreateUser_Success() {
        User expectedUser = new User(1L, "Juan", "juan@example.com");
        when(mockRepository.save(any(User.class))).thenReturn(expectedUser);

        User result = createUserService.createUser("Juan", "juan@example.com");

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan", result.getName());
        assertEquals("juan@example.com", result.getEmail());
        
        verify(mockRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUser_RepositoryCalledWithCorrectUser() {
        User savedUser = new User(2L, "María", "maria@example.com");
        when(mockRepository.save(any(User.class))).thenReturn(savedUser);

        createUserService.createUser("María", "maria@example.com");

        verify(mockRepository).save(argThat(user -> 
            user.getName().equals("María") && 
            user.getEmail().equals("maria@example.com") &&
            user.getId() == null
        ));
    }

}
