package com.james.LifeTracker.dto.view;

import com.james.LifeTracker.dto.binding.CommentInputBindingModel;

import java.util.List;

public class NoteDetailsViewModel {

    private Long id;
    private String name;
    private String description;
    private String createdOn;
    private String lastUpdatedOn;
    private List<CommentViewModel> comments;

    public NoteDetailsViewModel() {
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public List<CommentViewModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentViewModel> comments) {
        this.comments = comments;
    }

    public String getCreatedOn() { return createdOn; }

    public void setCreatedOn(String createdOn) { this.createdOn = createdOn; }
}
