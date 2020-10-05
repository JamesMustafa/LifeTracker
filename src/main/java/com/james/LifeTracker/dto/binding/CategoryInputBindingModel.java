package com.james.LifeTracker.dto.binding;

import com.james.LifeTracker.db.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryInputBindingModel {

    @NotNull(message = "Name cannot be null")
    @Size(min = 3,max = 32, message = "Name must be more than 3 and less than 32 characters")
    private String name;

    @Size(max = 200, message = "Description length must be less than 200 characters")
    private String description;

    //getters and setters
    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
