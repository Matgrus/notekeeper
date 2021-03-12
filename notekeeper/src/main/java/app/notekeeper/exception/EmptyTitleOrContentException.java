package app.notekeeper.exception;

public class EmptyTitleOrContentException extends RuntimeException{
    private static final String MESSAGE = "Title and Content fields must be filled";

    public EmptyTitleOrContentException() {
        super(MESSAGE);
    }

}
