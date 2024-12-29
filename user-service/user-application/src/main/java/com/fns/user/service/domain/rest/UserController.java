package com.fns.user.service.domain.rest;

import com.fns.user.service.domain.dto.get.RoleResponse;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.dto.get.GetAllUserResponse;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<List<GetAllUserResponse>> getAllUsers() {
        log.info("Fetching al es.size()");
        List<GetAllUserResponse> userResponses = userApplicationService.getAllUsers();

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
        log.info("Fetching user with ID {}", id);
        UserResponse userResponse = userApplicationService.getUserById(id);
        log.info("User retrieved: {}", userResponse);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/profile-photo")
    public ResponseEntity<String> uploadProductPhoto(
            @RequestParam("file") MultipartFile file) {
        try {
            // Upload the photo and get the URL
            String photoUrl = userApplicationService.uploadProfilePhoto(file);
            log.info("Photo uploaded successfully for Product ID {}:", photoUrl);
            return ResponseEntity.ok(photoUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload photo: " + e.getMessage());
        }
    }

}
