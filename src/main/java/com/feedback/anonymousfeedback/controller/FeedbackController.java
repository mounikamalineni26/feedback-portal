package com.feedback.anonymousfeedback.controller;

import com.feedback.anonymousfeedback.model.Feedback;
import com.feedback.anonymousfeedback.model.User;
import com.feedback.anonymousfeedback.service.FeedbackService;
import com.feedback.anonymousfeedback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitFeedback(@RequestBody Feedback feedback) {
        feedbackService.submitFeedback(feedback);
        return ResponseEntity.ok("Feedback submitted successfully");
    }

    @GetMapping("/all")
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    // ✅ Admin-only delete endpoint
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id, @RequestParam String username) {
        User user = userService.findByUsername(username);
        if (user != null && "ADMIN".equalsIgnoreCase(user.getRole())) {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.ok("Feedback deleted");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }
}
