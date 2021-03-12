package app.notekeeper.controller;

import app.notekeeper.model.ArchiveNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveNoteRepository extends JpaRepository<ArchiveNote, Long> {

    List<ArchiveNote> findArchiveNotesByNoteIdEquals(Long id);

}
