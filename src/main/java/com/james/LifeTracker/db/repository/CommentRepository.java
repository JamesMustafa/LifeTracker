package com.james.LifeTracker.db.repository;

import com.james.LifeTracker.db.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
