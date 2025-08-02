package com.feedback.anonymousfeedback.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime timestamp;

    // Constructors
    public Feedback() {
        this.timestamp = LocalDateTime.now();
    }

    public Feedback(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getSubmittedAt() { return timestamp; }
    public void setSubmittedAt(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        return "Feedback{id=" + id + ", feedback='" + message + "', submittedAt=" + timestamp + '}';
    }


}
