package com.james.LifeTracker.db.repository;

import com.james.LifeTracker.db.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByOrderByPriorityDesc();
    List<Note> findByOrderByIdDesc();
    List<Note> findByOrderByCreatedOnAsc();
    List<Note> findByOrderByLastUpdatedOnDesc();

}
