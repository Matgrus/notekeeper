package app.notekeeper.controller;

import app.notekeeper.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note save(Note note) {
        noteRepository.save(note);
        return note;
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }


}
