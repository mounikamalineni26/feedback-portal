package com.feedback.anonymousfeedback.controller;

import com.feedback.anonymousfeedback.model.Feedback;
import com.feedback.anonymousfeedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import com.feedback.anonymousfeedback.dto.AdminStatsResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Public API - no authentication required
    @PostMapping("/submit")
    public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.saveFeedback(feedback));
    }


    // Admin: Get paginated ACTIVE feedback
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public ResponseEntity<Page<Feedback>> getAllFeedback(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(feedbackService.getFeedbacks(pageable));
    }


    // Admin: soft delete
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        feedbackService.softDeleteFeedback(id);
        return ResponseEntity.ok("Feedback marked as deleted");
    }


    // Admin Stats
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/stats")
    public ResponseEntity<AdminStatsResponse> getAdminStats() {

        long total = feedbackService.getTotalFeedback();
        long today = feedbackService.getTodayFeedback();

        return ResponseEntity.ok(new AdminStatsResponse(total, today));
    }


    // Filter by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Feedback>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(feedbackService.getFeedbackByCategory(category));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/count")
    public ResponseEntity<Long> getTotalActiveFeedback() {
        return ResponseEntity.ok(feedbackService.getTotalFeedback());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics() {

        List<Feedback> list = feedbackService.getAllActiveFeedback();

        // ✅ Default categories
        Map<String, Long> counts = new HashMap<>();
        counts.put("Faculty", 0L);
        counts.put("Academic", 0L);
        counts.put("Infrastructure", 0L);
        counts.put("Others", 0L);

        // ✅ Count logic
        list.forEach(f -> {
            counts.put(
                    f.getCategory(),
                    counts.getOrDefault(f.getCategory(), 0L) + 1
            );
        });

        // ✅ Top category
        String topCategory = counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");

        // ✅ Final response
        Map<String, Object> response = new HashMap<>();
        response.put("counts", counts);
        response.put("topCategory", topCategory);

        return ResponseEntity.ok(response);
    }
}