package app.notekeeper.exception;

public class IncorrectIdValueException extends RuntimeException{
    private static final String MESSAGE = "Incorrect id value";

    public IncorrectIdValueException() {
        super(MESSAGE);
    }

}
