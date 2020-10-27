package com.james.LifeTracker.dto.view;

public class EventViewModel {
    private Long id;
    private String name;
    private String categoryName;
    private String description;
    private Integer priority;
    private Integer commentCount;
    private String eventTime;
    private String lastUpdatedOn;

    //TODO: When creating a category, it could ask you what do u want : Notes, Events, Problems and with checkboxes to mark it
    // bc, for example in my q&a section, I do not need any events ;)

    // I can also make urls like /categories/inspiration/notes/details/{id}
    // I can give the category name and search with it in the controller
    // Makes the url prettier, and also I should be allowed to read only notes, that belong to this category
}
