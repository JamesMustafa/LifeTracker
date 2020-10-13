package com.james.LifeTracker.web.controller;

import com.james.LifeTracker.dto.binding.CommentInputBindingModel;
import com.james.LifeTracker.dto.binding.NoteInputBindingModel;
import com.james.LifeTracker.service.NoteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/categories/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(value = {"/{id}", "/{id}/{noteSortId}"})
    public String getNotes(@PathVariable("id") Long categoryId,
                           @PathVariable(value = "noteSortId", required = false) Optional<Integer> noteSortId,
                           Model model){
        Integer newSortId = 0;
        if (noteSortId.isPresent()) {
            newSortId = noteSortId.get(); //returns the id
            model.addAttribute("sortId", newSortId);
        }
        model.addAttribute("categoryID", categoryId);
        model.addAttribute("allNotes", this.noteService.getNotesByCategoryId(categoryId, newSortId));
        return "note/index";
    }

    @GetMapping("/create/{id}")
    public String getCreateNote(@PathVariable("id") Long categoryId, Model model){
        model.addAttribute("categoryID", categoryId);
        model.addAttribute("createNote", new NoteInputBindingModel());
        return "note/createNote";
    }

    @GetMapping("/edit/{id}")
    public String getEditNote(@PathVariable("id") Long noteId, Model model) {
        model.addAttribute("noteEditForm", this.noteService.findNoteModelById(noteId));
        return "note/editNote";
    }

    @GetMapping("/details/{id}")
    public String getNoteDetails(@PathVariable("id") Long noteId, Model model){
        model.addAttribute("note", this.noteService.findNoteDetailsById(noteId));
        model.addAttribute("commentModel", new CommentInputBindingModel());
        return "note/details";
    }

    @PostMapping("/create")
    public String postCreatedNote(@Valid @ModelAttribute("createNote") NoteInputBindingModel noteModel,
                                  BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "note/createNote";
        }
        this.noteService.createNote(noteModel);
        return "redirect:/categories/notes/" + noteModel.getCategoryId();
    }

    @PostMapping("/edit/{id}")
    public String postEditedNote(@Valid @ModelAttribute("noteEditForm") NoteInputBindingModel noteModel,
                                 @PathVariable("id") Long id,
                                 BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "note/editNote";
        }
        this.noteService.editNote(noteModel, id);
        return "redirect:/categories/notes/" + noteModel.getCategoryId();
    }

    @PostMapping("/delete")
    public String hardDelete(@ModelAttribute(name="deleteId") Long deleteId) {
        this.noteService.hardDelete(deleteId);
        return "redirect:/categories";
    }
}
