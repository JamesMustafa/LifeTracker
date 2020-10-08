package com.james.LifeTracker.dto.view;

import java.time.LocalDateTime;

public class CommentViewModel {
    private Long id;
    private String commentText;
    private Integer priority;
    private LocalDateTime createdOn;

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedOn() { return createdOn; }

    public void setCreatedOn(LocalDateTime createdOn) { this.createdOn = createdOn; }
}
