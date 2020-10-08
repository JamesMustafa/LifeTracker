package com.james.LifeTracker.service;

import com.james.LifeTracker.db.entity.Comment;
import com.james.LifeTracker.db.entity.Note;
import com.james.LifeTracker.db.repository.CommentRepository;
import com.james.LifeTracker.dto.binding.CommentInputBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final NoteService noteService;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, NoteService noteService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.noteService = noteService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Comment saveNoteComment(CommentInputBindingModel commentModel, Long noteId){
        Comment comment = this.modelMapper.map(commentModel, Comment.class);
        Note note = this.noteService.findNoteById(noteId);
        note.getComments().add(comment);
        note.setLastUpdatedOn(LocalDateTime.now());
        return this.commentRepository.save(comment);
    }
}
