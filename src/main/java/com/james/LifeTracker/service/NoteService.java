package com.james.LifeTracker.service;

import com.james.LifeTracker.db.entity.Note;
import com.james.LifeTracker.db.repository.NoteRepository;
import com.james.LifeTracker.dto.binding.NoteInputBindingModel;
import com.james.LifeTracker.dto.view.NoteViewModel;
import com.james.LifeTracker.util.DateFormat;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public NoteService(NoteRepository noteRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.noteRepository = noteRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Note createNote(NoteInputBindingModel noteModel){
        Note note = this.modelMapper.map(noteModel, Note.class);
        note.setCategory(this.categoryService.findCategoryById(noteModel.getCategoryId()));
        note.setId((long)89);
        return this.noteRepository.save(note);
    }

    @Transactional
    public Note editNote(NoteInputBindingModel noteModel, Long noteId){
        Note note = this.noteRepository.findById(noteId)
                .orElseThrow();
        note.setName(noteModel.getName());
        note.setDescription(noteModel.getDescription());
        note.setPriority(noteModel.getPriority());
        note.setLastUpdatedOn(LocalDateTime.now());
        return this.noteRepository.save(note);
    }

    public Note findNoteById(Long noteId){ return this.noteRepository.findById(noteId).orElseThrow(); }

    public NoteInputBindingModel findNoteModelById(Long noteId) {
        return this.modelMapper.map(this.findNoteById(noteId), NoteInputBindingModel.class);
    }

    public List<NoteViewModel> getNotesByCategoryId(Long categoryId){
        List<NoteViewModel> readyNotes = this.noteRepository.findAll()
                .stream()
                .filter(o -> o.getCategory().getId().equals(categoryId))
                .map(o -> this.modelMapper.map(o, NoteViewModel.class))
                .collect(Collectors.toList());


        //Manual mappings for the harder stuff
        for (var note: readyNotes) {
            note.setCreatedOn(DateFormat.getDateString(this.findNoteById(note.getId()).getCreatedOn()));
            note.setLastUpdatedOn(DateFormat.getTimeAgo(this.findNoteById(note.getId()).getLastUpdatedOn()));
            note.setCommentCount(this.findNoteById(note.getId()).getComments().size());
            note.setCategoryName(this.findNoteById(note.getId()).getCategory().getName());
        }
        return readyNotes;
    }

}
