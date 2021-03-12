package app.notekeeper.view;

import app.notekeeper.client.ArchiveNoteClient;
import app.notekeeper.exception.IncorrectIdValueException;
import app.notekeeper.model.ArchiveNote;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("archive")
public class ArchiveGUI extends VerticalLayout {
    private final ArchiveNoteClient archiveNoteClient;

    private TextField idTextField;
    private Button findButton;
    private HorizontalLayout findNoteBar;

    private Grid<ArchiveNote> archiveNoteGrid;

    ArchiveGUI(ArchiveNoteClient archiveNoteClient) {
        this.archiveNoteClient = archiveNoteClient;

        configureFindNoteBar();
        add(findNoteBar);
        configureArchiveNoteList();
        add(archiveNoteGrid);

    }

    private void configureFindNoteBar() {
        findNoteBar = new HorizontalLayout();
        findNoteBar.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        idTextField = new TextField("Find note by id");
        idTextField.setPlaceholder("Note id");

        findButton = new Button("Find", buttonClickEvent -> loadItemsToGrid());

        findNoteBar.add(idTextField, findButton);
    }

    private void configureArchiveNoteList() {
        archiveNoteGrid = new Grid<>(ArchiveNote.class);

        archiveNoteGrid.removeAllColumns();
        archiveNoteGrid.addComponentColumn(this::addContentButtonToGrid).setComparator((title1, title2) -> {
            return title1.getTitle().compareTo(title2.getTitle());
        }).setHeader("Title");
        archiveNoteGrid.addColumns("modified", "version");

    }

    private Button addContentButtonToGrid(ArchiveNote note) {
        return new Button(note.getTitle(), event -> showContentDialog(note));
    }

    private void showContentDialog(ArchiveNote note) {
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

    private void loadItemsToGrid() {

        Long idValue;
        try {
            idValue = Long.valueOf(idTextField.getValue());
        } catch (NumberFormatException ex) {
            throw new IncorrectIdValueException();
        }

        archiveNoteGrid.setItems(archiveNoteClient.getAllById(idValue));
    }

}
