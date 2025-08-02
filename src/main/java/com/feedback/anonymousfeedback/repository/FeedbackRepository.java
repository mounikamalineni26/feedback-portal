package com.feedback.anonymousfeedback.repository;

import com.feedback.anonymousfeedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {}
