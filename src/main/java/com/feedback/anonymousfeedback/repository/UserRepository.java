package com.feedback.anonymousfeedback.repository;

import com.feedback.anonymousfeedback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
