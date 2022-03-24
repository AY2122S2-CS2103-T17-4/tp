package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.exceptions.InvalidRedoException;
import seedu.address.model.exceptions.InvalidUndoException;

/**
 * Address book that contains the state history, inherits from {@code AddressBook}.
 */
public class StateAddressBook extends AddressBook {

    /** Limits the number of undo and redo. */
    public static final int UNDO_REDO_CAPACITY = 20;

    /** Lists that contains the address book state history. */
    private final List<ReadOnlyAddressBook> stateHistory;

    /** Index of the current state in {@code stateHistory}. */
    private int currentStateIndex;

    /**
     * Creates a {@code StateAddressBook} with a given initial state.
     *
     * @param initialState initial state of the address book.
     */
    public StateAddressBook(ReadOnlyAddressBook initialState) {
        super(initialState);

        assert initialState != null : "initialState should not be null";

        stateHistory = new ArrayList<>();
        stateHistory.add(new AddressBook(initialState));
        currentStateIndex = 0;
    }

    /**
     * Restores address book state to its previous state.
     *
     * @throws InvalidUndoException If {@code stateHistory} is not undoable.
     */
    public void undo() throws InvalidUndoException {
        if (!isUndoable()) {
            throw new InvalidUndoException();
        }
        currentStateIndex--;
        resetData(stateHistory.get(currentStateIndex));
    }

    /**
     * Restores address book state to its previous undid state.
     *
     * @throws InvalidRedoException If {@code stateHistory} is not redoable.
     */
    public void redo() throws InvalidRedoException {
        if (!isRedoable()) {
            throw new InvalidRedoException();
        }
        currentStateIndex++;
        resetData(stateHistory.get(currentStateIndex));
    }

    /**
     * Returns true if {@code stateHistory} is undoable. Otherwise, returns false.
     *
     * @return true if undoable; false otherwise.
     */
    public boolean isUndoable() {
        return currentStateIndex > 0;
    }

    /**
     * Returns true if {@code stateHistory} is redoable. Otherwise, returns false.
     *
     * @return true if redoable; otherwise false.
     */
    public boolean isRedoable() {
        return currentStateIndex + 1 < stateHistory.size();
    }

    /**
     * Saves the current address book state.
     */
    public void saveState() {
        clearAfterCurrentStateIndex();
        if (isFull()) {
            stateHistory.remove(0);
            currentStateIndex--;
        }
        stateHistory.add(new AddressBook(this));
        currentStateIndex++;
    }

    /**
     * Clears the {@code stateHistory} after the current state index.
     */
    private void clearAfterCurrentStateIndex() {
        stateHistory.subList(currentStateIndex + 1, stateHistory.size()).clear();
    }

    /**
     * Returns true if {@code stateHistory} is full, i.e. equal to UNDO_REDO_CAPACITY + 1 to account for initial state,
     * otherwise returns false.
     *
     * @return true if full; otherwise false.
     */
    private boolean isFull() {
        return stateHistory.size() == UNDO_REDO_CAPACITY + 1;
    }

    /**
     * Checks if two {@code StateAddressBook} is equal.
     *
     * @param other the other {@code StateAddressBook} object.
     * @return true if equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StateAddressBook // instanceof handles nulls
                && super.equals(other)
                && stateHistory.equals(((StateAddressBook) other).stateHistory)
                && currentStateIndex == (((StateAddressBook) other).currentStateIndex));
    }
}
