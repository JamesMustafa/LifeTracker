package com.james.LifeTracker.service;

import com.james.LifeTracker.db.entity.Event;
import com.james.LifeTracker.db.repository.EventRepository;
import com.james.LifeTracker.dto.binding.EventInputBindingModel;
import com.james.LifeTracker.dto.view.EventViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public EventService(EventRepository eventRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Event createEvent(EventInputBindingModel eventModel){
        Event event = this.modelMapper.map(eventModel, Event.class);
        event.setCategory(this.categoryService.findCategoryById(eventModel.getCategoryId()));
        event.setId((long)89);
        return this.eventRepository.save(event);
    }

    public Event findEventById(Long eventId){ return this.eventRepository.findById(eventId).orElseThrow(); }

    public List<EventViewModel> getEventsByCategoryId(Long categoryId, Integer eventSortId){
        List<EventViewModel> readyEvents = getEventsSorted(eventSortId)
                .stream()
                .filter(o -> o.getCategory().getId().equals(categoryId))
                .map(o -> this.modelMapper.map(o, EventViewModel.class))
                .collect(Collectors.toList());


//        //Manual mappings for the harder stuff
//        for (var event: readyEvents) {
//            event.setCreatedOn(DateFormat.getDateString(this.findEventById(event.getId()).getCreatedOn()));
//            note.setLastUpdatedOn(DateFormat.getTimeAgo(this.findNoteById(note.getId()).getLastUpdatedOn()));
//            note.setCommentCount(this.findNoteById(note.getId()).getComments().size());
//            note.setCategoryName(this.findNoteById(note.getId()).getCategory().getName());
//        }
        return readyEvents;
    }

    private List<Event> getEventsSorted(Integer eventSortId){
        List<Event> allEvents = new ArrayList<>();
        switch (eventSortId){
            case 0:
                allEvents = this.eventRepository.findByOrderByPriorityDesc();
                break;
            case 1:
                allEvents = this.eventRepository.findByOrderByEventTimeDesc();
                break;
            case 2:
                allEvents = this.eventRepository.findByOrderByLastUpdatedOnDesc();
                break;
        }
        return allEvents;
    }
}
