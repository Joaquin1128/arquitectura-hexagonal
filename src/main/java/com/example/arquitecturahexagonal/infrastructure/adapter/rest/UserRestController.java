package com.example.arquitecturahexagonal.infrastructure.adapter.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.arquitecturahexagonal.domain.model.User;
import com.example.arquitecturahexagonal.domain.port.in.CreateUserUseCase;
import com.example.arquitecturahexagonal.domain.port.in.GetUsersUseCase;
import com.example.arquitecturahexagonal.infrastructure.adapter.rest.dto.CreateUserRequest;
import com.example.arquitecturahexagonal.infrastructure.adapter.rest.dto.UserResponse;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUsersUseCase getUsersUseCase;

    public UserRestController(CreateUserUseCase createUserUseCase, GetUsersUseCase getUsersUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUsersUseCase = getUsersUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = createUserUseCase.createUser(request.getName(), request.getEmail()); 
        UserResponse response = toUserResponse(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = getUsersUseCase.getAllUsers();
        List<UserResponse> responses = users.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

}
