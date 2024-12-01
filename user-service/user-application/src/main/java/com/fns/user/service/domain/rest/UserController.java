package com.fns.user.service.domain.rest;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.CreateUserResponse;
import com.fns.user.service.domain.dto.create.GetAllUserResponse;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Any;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user", produces = "application/vnd.api.v1+json")
public class UserController {
    private final UserApplicationService userApplicationService;
    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllUserResponse>> getAllUsers() {
        log.info("Fetching al es.size()");
        List<GetAllUserResponse> userResponses = userApplicationService.getAllUsers();

        return ResponseEntity.ok(userResponses);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
//        log.info("Fetching user with ID {}", id);
//        UserResponse userResponse = userApplicationService.getUserById(id);
//        log.info("User retrieved: {}", userResponse);
//        return ResponseEntity.ok(userResponse);
//    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserCommand createUserCommand) {
        log.info(createUserCommand.toString());
        log.info("Creating user {} at {}", createUserCommand.getUsername(), createUserCommand.getRole());
        CreateUserResponse createUserResponse = userApplicationService.createUser(createUserCommand);
        log.info("user created");
        return ResponseEntity.ok(createUserResponse);
    }

}
