package com.james.LifeTracker.dto.view;



import com.james.LifeTracker.db.entity.Event;
import com.james.LifeTracker.db.entity.Note;
import com.james.LifeTracker.db.entity.Problem;

import java.time.LocalDateTime;

//TODO: V TVA DTO SE CHUDA DALI DA SLOJA CELITE KLASOVE NOTES,
// PROBLEMS, ILI SAMO FIELDOVETE KOITO MI TRQBVAT OT TQH,
// PONEJE V MINALIQ PROEKT SYM SLAGAL DIREKTNO KLASOWETE,
// NO IDEQTA NA VIEW MODELITE NE E LI DA PRENASQT
// SAMO DANNI KOOITO SA NEOBHODIMI I DA
// SPESTQVAT IZLISHNI NATRUPVANIQ,
// I PO TOZI NACHIN DA POKACHWAT PERFORMANCE


//TODO: ZA SPRAVKA DA VIDA PRIMERA S PICARIYATA /DTO/VIEW/ORDERHISTORYVIEWMODEL
//TODO: TOQ PUT SH GO NAPRAVQ PO DRUGIQ NACHIN !

//TODO: TRQ izmislq oshte nqkw field za percentage ili drug metod
// ,za da pokaja razvitie s neshtata det si zapisvam v kategoriqta !!!
// Po tozi nachin shte mi e po lesno da razbiram na koe neshto trq oburna poveche vnimanie,
// last updated on time is for the same purpose there.


//TODO: Shte go napravq po stariq nachin shot inache mapvaniqta ne stavat i sh go ostaa si mislq koe e po pravilno !!!

public class CategoryViewModel {

    private Long id;
    private String name;
    private String description;
    private String createdOn;
    private String lastUpdatedOn;
    private Integer eventCount;
    private Integer problemCount;
    private Integer noteCount;

    //    private CountViewModel<Event> eventCount;
    //    private CountViewModel<Problem> problemCount;
    //    private CountViewModel<Note> noteCount;

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastUpdatedOn() { return lastUpdatedOn; }

    public void setLastUpdatedOn(String lastUpdatedOn) { this.lastUpdatedOn = lastUpdatedOn; }

    public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    public Integer getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(Integer problemCount) {
        this.problemCount = problemCount;
    }

    public Integer getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(Integer noteCount) {
        this.noteCount = noteCount;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
