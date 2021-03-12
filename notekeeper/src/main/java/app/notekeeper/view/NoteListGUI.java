package app.notekeeper.view;

import app.notekeeper.client.ArchiveNoteClient;
import app.notekeeper.client.NoteClient;
import app.notekeeper.exception.EmptyTitleOrContentException;
import app.notekeeper.model.ArchiveNote;
import app.notekeeper.model.Note;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("main")
public class NoteListGUI extends VerticalLayout {
    private final NoteClient noteClient;
    private final ArchiveNoteClient archiveNoteClient;

    private TextField newNoteTitleText;
    private TextArea newNoteContentText;
    private Button newNoteAddButton;
    private HorizontalLayout addNoteBar;

    private Grid<Note> noteGrid;

    public NoteListGUI(NoteClient noteClient, ArchiveNoteClient archiveNoteClient) {
        this.noteClient = noteClient;
        this.archiveNoteClient = archiveNoteClient;

        configureAddNoteBar();
        add(addNoteBar);
        configureNoteList();
        add(noteGrid);

    }

    private void configureAddNoteBar() {
        addNoteBar = new HorizontalLayout();
        addNoteBar.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        newNoteTitleText = new TextField("Note title");
        newNoteTitleText.setPlaceholder("Title");

        newNoteContentText = new TextArea("Note content");
        newNoteContentText.setPlaceholder("Content");

        newNoteAddButton = new Button("Add", event ->
                addNote(newNoteTitleText.getValue().trim(), newNoteContentText.getValue().trim()));

        addNoteBar.add(newNoteTitleText, newNoteContentText, newNoteAddButton);

    }

    private void configureNoteList() {
        noteGrid = new Grid<>(Note.class);

        noteGrid.removeAllColumns();
        noteGrid.addComponentColumn(this::addContentButtonToGrid).setComparator((title1, title2) -> {
            return title1.getTitle().compareTo(title2.getTitle());
        }).setHeader("Title");
        noteGrid.addColumns("modified", "created", "id");
        noteGrid.addComponentColumn(this::addEditButtonToGrid);
        noteGrid.addComponentColumn(this::addDeleteButtonToGrid);

        loadItemsToGrid();
    }

    private void loadItemsToGrid() {
        noteGrid.setItems(noteClient.getAll());
    }

    private void addNote(String title, String content) {

        if (title.isEmpty() || content.isEmpty()) {
            throw new EmptyTitleOrContentException();
        }

        Note note = noteClient.addOrUpdateNote(new Note(title, content));

        archiveNoteClient.addArchiveNote(new ArchiveNote(note));

        loadItemsToGrid();
    }

    private Button addContentButtonToGrid(Note note) {
        return new Button(note.getTitle(), event -> showContentDialog(note));
    }

    private Button addEditButtonToGrid(Note note) {
        return new Button("Edit", event -> editNote(note));
    }

    private Button addDeleteButtonToGrid(Note note) {
        return new Button("Delete", event -> deleteNote(note));
    }

    private void editNote(Note note) {
        showEditDialog(note);
    }

    private void deleteNote(Note note) {
        noteClient.deleteNote(note.getId());

        loadItemsToGrid();
    }

    private void showContentDialog(Note note) {
        Dialog dialog = new Dialog();

        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(Alignment.CENTER);

        TextArea dialogText = new TextArea();
        dialogText.setValue(note.getContent());
        dialogText.setReadOnly(true);
        Button closeButton = new Button("Close", event -> dialog.close());

        layout.add(dialogText, closeButton);

        dialog.add(layout);

        dialog.open();
    }

    private void showEditDialog(Note note) {
        Dialog dialog = new Dialog();

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setAlignItems(Alignment.CENTER);

        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setAlignItems(Alignment.CENTER);

        TextField editTitle = new TextField();
        editTitle.setValue(note.getTitle());
        editTitle.setPlaceholder("Title");

        TextArea editContent = new TextArea();
        editContent.setValue(note.getContent());
        editContent.setPlaceholder("Content");

        Button confirmButton = new Button("Confirm", event -> {

            String newTitle = editTitle.getValue().trim();
            String newContent = editContent.getValue().trim();

            if (newTitle.isEmpty() || newContent.isEmpty()) {
                throw new EmptyTitleOrContentException();
            }

            note.setTitle(newTitle);
            note.setContent(newContent);
            note.setModified();
            note.setVersion();

            noteClient.addOrUpdateNote(note);

            archiveNoteClient.addArchiveNote(new ArchiveNote(note));

            loadItemsToGrid();

            dialog.close();
        });

        Button closeButton = new Button("Close", event -> dialog.close());

        hLayout.add(confirmButton, closeButton);
        vLayout.add(editTitle, editContent, hLayout);

        dialog.add(vLayout);
        dialog.open();
    }

}
