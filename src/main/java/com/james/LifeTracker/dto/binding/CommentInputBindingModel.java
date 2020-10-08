package com.james.LifeTracker.dto.binding;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentInputBindingModel {

    @NotEmpty(message = "Comment section could not be posted empty.")
    @Size(min = 1, max = 500, message = "Comment should be between 1 and 500 characters")
    private String commentText;

    @NotNull(message = "Priority cannot be null")
    @Range(min = 1, max = 5, message = "Size must be between 1 and 5")
    private Integer priority;

    //getters and setters
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
}
