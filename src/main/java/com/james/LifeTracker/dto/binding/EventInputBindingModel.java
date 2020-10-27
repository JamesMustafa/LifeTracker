package com.james.LifeTracker.dto.binding;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventInputBindingModel {
    Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3,max = 80, message = "Name must be more than 3 and less than 80 characters")
    private String name;

    @Size(min = 10, message = "Your event cannot be less than 10 characters.")
    private String description;

    @NotNull(message = "Priority cannot be null")
    @Range(min = 1, max = 5, message = "Size must be between 1 and 5")
    private Integer priority;

    @NotNull(message = "Date cannot be null")
    private String eventTime;

    @NotNull
    private Long categoryId;

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
