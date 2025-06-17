package com.spring.User_Management_API.controller;

import com.spring.User_Management_API.model.User;
import com.spring.User_Management_API.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Open to all for registration
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    // Open to all for login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.authenticate(user));
    }

    // Any authenticated user can get their own profile
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public User getProfile(Authentication authentication) {
        String username = authentication.getName();
        return userService.findByUsername(username);
    }

    // Admin-only delete user by id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Access user by id — only admin OR the user themselves
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isSelf(authentication, #id)")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }
}