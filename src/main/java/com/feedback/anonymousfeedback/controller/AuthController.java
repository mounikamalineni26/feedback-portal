package com.feedback.anonymousfeedback.controller;

import com.feedback.anonymousfeedback.model.User;
import com.feedback.anonymousfeedback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap; 
import java.util.Map;     

@RestController
@RequestMapping("/api/auth")
@CrossOrigin // Allow frontend access
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // 🔹 Register a new user
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }

    // 🔹 Login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
        }

        // Determine role based on username
        String role = user.getUsername().equalsIgnoreCase("admin") ? "admin" : "user";

        // Prepare response
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful!");
        response.put("role", role);

        return ResponseEntity.ok(response);
    }
}
