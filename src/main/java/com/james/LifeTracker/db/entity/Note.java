package com.james.LifeTracker.db.entity;

import com.james.LifeTracker.db.entity.common.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "notes")
public class Note extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "note", orphanRemoval = true)
    private List<Comment> comments;

    public Note() {
    }

    //getters and setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
