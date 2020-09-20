package com.james.LifeTracker.db.entity;

import com.james.LifeTracker.db.entity.common.BaseEntity;
import com.james.LifeTracker.db.entity.common.PriorityEnum;

import javax.persistence.*;

@Entity
@Table(name = "problems")
public class Problem extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "priority", nullable = false)
    private PriorityEnum priority;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    public Problem() {
    }

    //getters and setters
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

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
