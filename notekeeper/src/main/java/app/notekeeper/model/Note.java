package app.notekeeper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Timestamp created;
    private Timestamp modified;
    private int version;

    public Note() {
    }

    public Note(String title, String content) {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());

        this.title = title;
        this.content = content;
        created = currentTimeStamp;
        modified = currentTimeStamp;
        version = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified() {
        modified = new Timestamp(System.currentTimeMillis());
    }

    public int getVersion() {
        return version;
    }

    public void setVersion() {
        version += 1;
    }

}
