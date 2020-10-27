package com.james.LifeTracker.web.controller;

import com.james.LifeTracker.dto.binding.EventInputBindingModel;
import com.james.LifeTracker.dto.binding.NoteInputBindingModel;
import com.james.LifeTracker.service.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/categories/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = {"/{id}", "/{id}/{eventSortId}"})
    public String getEvents(@PathVariable("id") Long categoryId,
                           @PathVariable(value = "eventSortId", required = false) Optional<Integer> eventSortId,
                           Model model){
        Integer newSortId = 0;
        if (eventSortId.isPresent()) {
            newSortId = eventSortId.get(); //returns the id
            model.addAttribute("sortId", newSortId);
        }
        model.addAttribute("categoryID", categoryId);
        model.addAttribute("allEvents", this.eventService.getEventsByCategoryId(categoryId, newSortId));
        return "event/index";
    }

    @GetMapping("/create/{id}")
    public String getCreateEvent(@PathVariable("id") Long categoryId, Model model){
        model.addAttribute("categoryID", categoryId);
        model.addAttribute("createEvent", new EventInputBindingModel());
        return "event/createEvent";
    }

    @PostMapping("/create")
    public String postCreatedEvent(@Valid @ModelAttribute("createEvent") EventInputBindingModel eventModel,
                                  BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "event/createEvent";
        }
        this.eventService.createEvent(eventModel);
        return "redirect:/categories/events/" + eventModel.getCategoryId();
    }
}
