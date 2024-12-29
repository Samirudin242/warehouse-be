package com.fns.user.service.domain.rest;

import com.fns.user.service.domain.dto.create.CreateUserCommand;
import com.fns.user.service.domain.dto.create.LoginUserCommand;
import com.fns.user.service.domain.dto.get.LoginResponse;
import com.fns.user.service.domain.dto.get.RoleResponse;
import com.fns.user.service.domain.dto.get.UserResponse;
import com.fns.user.service.domain.ports.input.service.UserApplicationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        // Set the token as a cookie
        ResponseCookie cookie = ResponseCookie.from("accessToken", loginResponse.getAccessToken())
                .httpOnly(true)  // Prevent JavaScript access
                .secure(true)    // Use only with HTTPS
                .path("/")       // Cookie available site-wide
                .maxAge(24 * 60 * 60)  // Expiry time in seconds
                .sameSite("Strict")    // Prevent CSRF
                .build();

        // Add the cookie to the response
        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/get-roles")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        List<RoleResponse> roleResponse = userApplicationService.getAllRoles();
        return ResponseEntity.ok(roleResponse);
    }
}
