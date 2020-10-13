package com.james.LifeTracker.web.controller;

import com.james.LifeTracker.dto.binding.CommentInputBindingModel;
import com.james.LifeTracker.service.CommentService;
import com.james.LifeTracker.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final NoteService noteService;

    public CommentController(CommentService commentService, NoteService noteService) {
        this.commentService = commentService;
        this.noteService = noteService;
    }

    @GetMapping("/categories/notes/{id}/comment/edit/{commentId}")
    public String getEditComment(@PathVariable("id") Long noteId,
                              @PathVariable("commentId") Long commentId,
                              Model model) {

        model.addAttribute("commentEditForm", this.commentService.findCommentModelById(commentId));
        model.addAttribute("note", this.noteService.findNoteDetailsById(noteId));
        return "comment/editCommentNote";
    }

    @PostMapping("/categories/notes/{id}/comment/save")
    public String saveComment(@Valid @ModelAttribute("commentModel") CommentInputBindingModel commentModel,
                              @PathVariable("id") Long noteId,
                              BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "redirect:/categories/notes/details/" + noteId;
        }
        this.commentService.saveNoteComment(commentModel, noteId);
        return "redirect:/categories/notes/details/" + noteId;
    }

    @PostMapping("/categories/notes/{id}/comment/edit/{editedId}")
    public String setEditComment(@Valid @ModelAttribute("commentEditForm") CommentInputBindingModel commentModel,
                                 @PathVariable("id") Long noteId,
                                 @PathVariable("editedId") Long commentId,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "comment/editCommentNote";
        }
        this.commentService.editComment(commentModel, commentId);
        return "redirect:/categories/notes/details/" + noteId;
    }

    @PostMapping("/comments/delete")
    public String hardDelete(@ModelAttribute(name="deleteId") Long deleteId,
                             @ModelAttribute(name = "noteId") Long noteId) {
        this.commentService.hardDelete(deleteId, noteId);
        return "redirect:/categories/notes/details/" + noteId;
    }
}
