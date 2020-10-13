package com.james.LifeTracker.service;

import com.james.LifeTracker.db.entity.Comment;
import com.james.LifeTracker.db.entity.Note;
import com.james.LifeTracker.db.repository.CommentRepository;
import com.james.LifeTracker.dto.binding.CommentInputBindingModel;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
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
        comment.setNote(note);
        note.getComments().add(comment);
        note.setLastUpdatedOn(LocalDateTime.now());
        LOGGER.info("New comment was created in note with id: {}", noteId);
        return this.commentRepository.save(comment);
    }

    public Comment editComment(CommentInputBindingModel editedComment, Long commentId){
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow();
        comment.setCommentText(editedComment.getCommentText());
        comment.setPriority(editedComment.getPriority());
        return this.commentRepository.save(comment);
    }

    @Transactional
    public void hardDelete(Long commentId, Long noteId) {
        Note note = this.noteService.findNoteById(noteId);
        note.setLastUpdatedOn(LocalDateTime.now());
        this.commentRepository.deleteById(commentId);
        LOGGER.info("Comment with id: {}, was deleted by note with id: {}", commentId, noteId);
    }

    public CommentInputBindingModel findCommentModelById(Long commentId){
        return this.modelMapper.map(
                this.commentRepository.findById(commentId).orElseThrow(),
                CommentInputBindingModel.class);
    }
}
