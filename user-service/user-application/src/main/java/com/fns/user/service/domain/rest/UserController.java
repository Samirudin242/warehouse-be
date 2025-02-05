package com.fns.user.service.domain.rest;

import com.fns.user.service.domain.dto.create.EditUserCommand;
import com.fns.user.service.domain.dto.get.*;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/user", produces = "application/vnd.api.v1+json")
public class UserController {
    private final UserApplicationService userApplicationService;
    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping
    public ResponseEntity<Page<GetAllUserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "") UUID role,
            @RequestParam(required = false, defaultValue = "") String name
    ) {
        Page<GetAllUserResponse> userResponses = userApplicationService.getAllUsers(page, size, role, name);

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/get-all-users-percentage")
    public ResponseEntity<UserCount> getAllUserPercentage() {
        UserCount userCount = userApplicationService.getAllUser();
        return ResponseEntity.ok(userCount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> editUser(@RequestBody EditUserCommand editUserCommand) {
        UserResponse userResponse = userApplicationService.editUser(editUserCommand);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
        log.info("Fetching user with ID {}", id);
        UserResponse userResponse = userApplicationService.getUserById(id);
        log.info("User retrieved: {}", userResponse);
        return ResponseEntity.ok(userResponse);
    }

}
