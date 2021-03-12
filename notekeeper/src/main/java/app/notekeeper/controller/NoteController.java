package app.notekeeper.controller;

import app.notekeeper.model.Note;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    List<Note> findAll() {
        return noteService.findAll();
    }

    @PostMapping("/notes")
    Note addOrUpdateNote(@RequestBody Note note) {
        return noteService.save(note);
    }

    @DeleteMapping("/notes/{id}")
    void deleteNote(@PathVariable Long id) {
        noteService.deleteById(id);
    }

}
