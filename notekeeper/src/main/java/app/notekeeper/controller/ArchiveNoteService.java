package app.notekeeper.controller;

import app.notekeeper.model.ArchiveNote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveNoteService {
    private final ArchiveNoteRepository archiveNoteRepository;

    public ArchiveNoteService(ArchiveNoteRepository archiveNoteRepository) {
        this.archiveNoteRepository = archiveNoteRepository;
    }

    public void save(ArchiveNote archiveNote) {
        archiveNoteRepository.save(archiveNote);
    }

    public List<ArchiveNote> findArchiveNotesById(Long id) {
        return archiveNoteRepository.findArchiveNotesByNoteIdEquals(id);
    }

}
