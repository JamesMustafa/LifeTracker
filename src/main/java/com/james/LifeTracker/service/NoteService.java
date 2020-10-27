package com.james.LifeTracker.service;

import com.james.LifeTracker.db.entity.Comment;
import com.james.LifeTracker.db.entity.Note;
import com.james.LifeTracker.db.repository.NoteRepository;
import com.james.LifeTracker.dto.binding.CommentInputBindingModel;
import com.james.LifeTracker.dto.binding.NoteInputBindingModel;
import com.james.LifeTracker.dto.view.*;
import com.james.LifeTracker.util.DateFormat;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Transactional
    public void hardDelete(Long noteId) {
        this.noteRepository.deleteById(noteId);
    }

    public NoteDetailsViewModel findNoteDetailsById(Long noteId){
        Note note = this.findNoteById(noteId);
        NoteDetailsViewModel noteModel = this.modelMapper.map(this.findNoteById(noteId), NoteDetailsViewModel.class);
        noteModel.setCreatedOn(DateFormat.getDateString(note.getCreatedOn()));
        noteModel.setLastUpdatedOn(DateFormat.getTimeAgo(note.getLastUpdatedOn()));
        noteModel.setComments(note.getComments()
                .stream()
                .map(c -> this.modelMapper.map(c, CommentViewModel.class))
                .collect(Collectors.toList()));
        return noteModel;
    }

    public Note findNoteById(Long noteId){ return this.noteRepository.findById(noteId).orElseThrow(); }

    public NoteInputBindingModel findNoteModelById(Long noteId) {
        return this.modelMapper.map(this.findNoteById(noteId), NoteInputBindingModel.class);
    }

    public List<NoteViewModel> findAll(Integer noteSortId){
        List<NoteViewModel> findAllNotes = getNotesSorted(noteSortId)
                .stream()
                .map(o -> this.modelMapper.map(o, NoteViewModel.class))
                .collect(Collectors.toList());

        return mapToNoteViewModel(findAllNotes);
    }

    public List<NoteSmallViewModel> findAllNotesSmall(Integer noteSortId){
        return getNotesSorted(noteSortId)
                .stream()
                .map(o -> this.modelMapper.map(o, NoteSmallViewModel.class))
                .collect(Collectors.toList());
    }

    public List<NoteViewModel> findNotesByCategoryId(Long categoryId, Integer noteSortId){
        List<NoteViewModel> readyNotes = getNotesSorted(noteSortId)
                .stream()
                .filter(o -> o.getCategory().getId().equals(categoryId))
                .map(o -> this.modelMapper.map(o, NoteViewModel.class))
                .collect(Collectors.toList());

        return mapToNoteViewModel(readyNotes);

    }

    public List<NameAndIdViewModel> findAllCategories(){
        return this.categoryService.findAllCategories()
                .stream()
                .map(o -> this.modelMapper.map(o, NameAndIdViewModel.class))
                .collect(Collectors.toList());
    }

    //Manual mappings for the harder stuff
    private List<NoteViewModel> mapToNoteViewModel(List<NoteViewModel> notes){
        for (var note: notes) {
            note.setCreatedOn(DateFormat.getDateString(this.findNoteById(note.getId()).getCreatedOn()));
            note.setLastUpdatedOn(DateFormat.getTimeAgo(this.findNoteById(note.getId()).getLastUpdatedOn()));
            note.setCommentCount(this.findNoteById(note.getId()).getComments().size());
            note.setCategoryName(this.findNoteById(note.getId()).getCategory().getName());
        }
        return notes;
    }

    private List<Note> getNotesSorted(Integer noteSortId){
        List<Note> allNotes = new ArrayList<>();
        switch (noteSortId){
            case 0:
                allNotes = this.noteRepository.findByOrderByIdDesc();
                break;
            case 1:
                allNotes = this.noteRepository.findByOrderByPriorityDesc();
                break;
            case 2:
                allNotes = this.noteRepository.findByOrderByCreatedOnAsc();
                break;
            case 3:
                allNotes = this.noteRepository.findByOrderByLastUpdatedOnDesc();
                break;
        }
        return allNotes;
    }
}
