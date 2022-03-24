package seedu.address.model.exceptions;

/**
 * Signals that the operation will fail as there is nothing to undo.
 */
public class InvalidUndoException extends RuntimeException {
    public InvalidUndoException() {
        super("There is nothing to undo.");
    }
}
