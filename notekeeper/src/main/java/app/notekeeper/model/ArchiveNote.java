package app.notekeeper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class ArchiveNote {

    @Id
    @GeneratedValue
    private Long id;
    private Long noteId;
    private String title;
    private String content;
    private Timestamp modified;
    private int version;

    public ArchiveNote() {
    }

    public ArchiveNote(Note note) {
        noteId = note.getId();
        title = note.getTitle();
        content = note.getContent();
        modified = note.getModified();
        version = note.getVersion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
