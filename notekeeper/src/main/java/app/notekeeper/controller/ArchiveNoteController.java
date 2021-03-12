package app.notekeeper.controller;

import app.notekeeper.model.ArchiveNote;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArchiveNoteController {
    private final ArchiveNoteService archiveNoteService;

    public ArchiveNoteController(ArchiveNoteService archiveNoteService) {
        this.archiveNoteService = archiveNoteService;
    }

    @GetMapping("/archivelist/{id}")
    List<ArchiveNote> findAllById(@PathVariable Long id) {
        return archiveNoteService.findArchiveNotesById(id);
    }

    @PostMapping("/archivelist")
    void addNote(@RequestBody ArchiveNote note) {
        archiveNoteService.save(note);
    }

}
