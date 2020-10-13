package com.james.LifeTracker.db.entity;

import com.james.LifeTracker.db.entity.common.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column(name = "comment_text", columnDefinition = "TEXT", nullable = false)
    private String commentText;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @ManyToOne
    private Note note;

    public Comment() {
    }

    //getters and setters

    public String getCommentText() { return commentText; }

    public void setCommentText(String commentText) { this.commentText = commentText; }

    public Integer getPriority() { return priority; }

    public void setPriority(Integer priority) { this.priority = priority; }

    public Note getNote() { return note; }

    public void setNote(Note note) { this.note = note; }
}
