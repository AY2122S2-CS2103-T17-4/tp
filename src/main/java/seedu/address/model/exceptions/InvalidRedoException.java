package seedu.address.model.exceptions;

/**
 * Signals that the operation will fail as there is nothing to redo.
 */
public class InvalidRedoException extends RuntimeException {
    public InvalidRedoException() {
        super("There is nothing to redo.");
    }
}
