package com.feedback.anonymousfeedback.repository;
import java.util.List;

import com.feedback.anonymousfeedback.model.Feedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    long countByDeletedFalse();

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.createdAt >= :startOfDay AND f.deleted = false")
    long countTodayActiveFeedback(@Param("startOfDay") LocalDateTime startOfDay);

    Page<Feedback> findByDeletedFalse(Pageable pageable);


    @Query("SELECT f FROM Feedback f WHERE f.category = :category AND f.deleted = false")
    List<Feedback> findByCategoryAndDeletedFalse(@Param("category") String category);


}