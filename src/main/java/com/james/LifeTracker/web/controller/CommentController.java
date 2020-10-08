package com.james.LifeTracker.web.controller;

import com.james.LifeTracker.dto.binding.CommentInputBindingModel;
import com.james.LifeTracker.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/categories/notes/{id}/comment/save")
    public String saveComment(@Valid @ModelAttribute("commentModel") CommentInputBindingModel commentModel,
                              @PathVariable("id") Long noteId,
                              BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "note/details" + noteId;
        }
        this.commentService.saveNoteComment(commentModel, noteId);
        return "redirect:categories/notes/details/" + noteId;
    }
}
