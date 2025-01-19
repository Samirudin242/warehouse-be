package com.fns.user.service.domain.rest;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.LoginUserCommand;
import com.fns.user.service.domain.dto.get.*;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/auth", produces = "application/vnd.api.v1+json")
public class AuthController {

    private final UserApplicationService userApplicationService;


    public AuthController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }


    @PutMapping("/edit-user/{id}")
    public ResponseEntity<UserResponse> editUser(
            @PathVariable UUID id,
            @RequestBody CreateUserCommand createUserCommand) {
        log.info(createUserCommand.toString());
        UserResponse editUserResponse = userApplicationService.editUser(id, createUserCommand);
        log.info("user edited");
        return ResponseEntity.ok(editUserResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserCommand createUserCommand) {
        log.info(createUserCommand.toString());
        UserResponse createUserResponse = userApplicationService.createUser(createUserCommand);
        log.info("user created");
        return ResponseEntity.ok(createUserResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserCommand loginUserCommand, HttpServletResponse response) {

        // Delegate the login process to the application service
        LoginResponse loginResponse = userApplicationService.login(loginUserCommand);

        // If login fails, return an unauthorized response
        if (loginResponse == null) {
            return ResponseEntity.status(401).body("Invalid username/email or password");
        }

        ResponseCookie cookie = ResponseCookie.from("accessToken", loginResponse.getAccessToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(24 * 60 * 60)
                .sameSite("None")
                .build();

        // Add the cookie to the response
        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/profile-photo")
    public ResponseEntity<ProfilePhotoResponse> uploadProductPhoto(
            @RequestParam("file") MultipartFile file) {
        try {
            // Upload the photo and get the URL
            ProfilePhotoResponse photoUrl = userApplicationService.uploadProfilePhoto(file);
            log.info("Photo uploaded successfully for Product ID {}:", photoUrl);
            return ResponseEntity.ok(photoUrl);
        } catch (IOException e) {
            log.error("Failed to upload photo: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProfilePhotoResponse("failed to get the url", "failed to upload"));
        }
    }

    @GetMapping("/get-roles")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        List<RoleResponse> roleResponse = userApplicationService.getAllRoles();
        return ResponseEntity.ok(roleResponse);
    }


}
