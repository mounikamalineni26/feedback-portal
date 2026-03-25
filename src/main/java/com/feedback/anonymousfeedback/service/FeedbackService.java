package com.feedback.anonymousfeedback.service;

import com.feedback.anonymousfeedback.model.Feedback;
import com.feedback.anonymousfeedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // 📊 Total count
    public long getTotalFeedback() {
        return feedbackRepository.countByDeletedFalse();
    }

    // 📅 Today's feedback count
    public long getTodayFeedback() {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        return feedbackRepository.countTodayActiveFeedback(startOfDay);
    }

    // 💾 Save feedback (ONLY ONE METHOD)
    public Feedback saveFeedback(Feedback feedback) {
        feedback.setDeleted(false);
        feedback.setCreatedAt(LocalDateTime.now());
        return feedbackRepository.save(feedback);
    }

    // 📄 Pagination
    public Page<Feedback> getFeedbacks(Pageable pageable) {
        return feedbackRepository.findByDeletedFalse(pageable);
    }


    // ✅ Get only ACTIVE feedback
    public List<Feedback> getAllActiveFeedback() {
        return feedbackRepository.findAll()
                .stream()
                .filter(f -> !f.isDeleted())
                .toList();
    }


    // 🔍 Filter by category
    public List<Feedback> getFeedbackByCategory(String category) {
        return feedbackRepository.findByCategoryAndDeletedFalse(category);
    }

    // 🔥 SOFT DELETE (USE THIS)
    public void softDeleteFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        feedback.setDeleted(true);
        feedbackRepository.save(feedback);
    }

}