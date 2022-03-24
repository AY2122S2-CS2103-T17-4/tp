package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.InvalidRedoException;
import seedu.address.model.exceptions.InvalidUndoException;
import seedu.address.testutil.AddressBookBuilder;

/**
 * Contains tests for {@code StateAddressBook}.
 */
public class StateAddressBookTest {

    private final ReadOnlyAddressBook emptyAddressBook = new AddressBookBuilder().build();
    private final ReadOnlyAddressBook aliceAddressBook = new AddressBookBuilder().withPerson(ALICE).build();
    private final ReadOnlyAddressBook bensonAddressBook = new AddressBookBuilder().withPerson(BENSON).build();
    private final ReadOnlyAddressBook carlAddressBook = new AddressBookBuilder().withPerson(CARL).build();
    private final List<ReadOnlyAddressBook> oneState = List.of(emptyAddressBook);
    private final List<ReadOnlyAddressBook> threeStates =
            Arrays.asList(emptyAddressBook, aliceAddressBook, bensonAddressBook);

    @Test
    public void isUndoable_oneAddressBookState_returnsFalse() {
        StateAddressBook stateAddressBook = setAddressBook(oneState);
        assertFalse(stateAddressBook.isUndoable());
    }

    @Test
    public void isRedoable_oneAddressBookState_returnsFalse() {
        StateAddressBook stateAddressBook = setAddressBook(oneState);
        assertFalse(stateAddressBook.isRedoable());
    }

    @Test
    public void isUndoable_threeAddressBookStatesPointerAtEnd_returnsTrue() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);
        assertTrue(stateAddressBook.isUndoable());
    }

    @Test
    public void isUndoable_threeAddressBookStatesPointerAtMiddle_returnsTrue() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);
        undoStates(stateAddressBook, 1);
        assertTrue(stateAddressBook.isUndoable());
    }

    @Test
    public void isUndoable_threeAddressBookStatesPointerAtStart_returnsFalse() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);
        undoStates(stateAddressBook, 2);
        assertFalse(stateAddressBook.isUndoable());
    }

    @Test
    public void isUndoable_maxCapacityAddressBookStatesPointerAtStart_returnsFalse() {
        StateAddressBook stateAddressBook =
                setAddressBookMaxCapacity(threeStates);
        stateAddressBook.resetData(carlAddressBook);
        stateAddressBook.saveState();
        undoStates(stateAddressBook, StateAddressBook.UNDO_REDO_CAPACITY);
        assertFalse(stateAddressBook.isUndoable());
    }

    @Test
    public void isRedoable_threeAddressBookStatesPointerAtStart_returnsTrue() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);
        undoStates(stateAddressBook, 2);
        assertTrue(stateAddressBook.isRedoable());
    }

    @Test
    public void isRedoable_threeAddressBookStatesPointerAtMiddle_returnsTrue() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);
        undoStates(stateAddressBook, 1);
        assertTrue(stateAddressBook.isRedoable());
    }

    @Test
    public void isRedoable_threeAddressBookStatesPointerAtEnd_returnsFalse() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);
        assertFalse(stateAddressBook.isRedoable());
    }

    @Test
    public void isRedoable_maxCapacityAddressBookStatesPointerAtEnd_returnsFalse() {
        StateAddressBook stateAddressBook =
                setAddressBookMaxCapacity(threeStates);
        stateAddressBook.resetData(carlAddressBook);
        stateAddressBook.saveState();
        assertFalse(stateAddressBook.isRedoable());
    }

    @Test
    public void undo_oneAddressBookState_throwsInvalidUndoException() {
        StateAddressBook stateAddressBook = setAddressBook(oneState);
        assertThrows(InvalidUndoException.class, stateAddressBook::undo);
    }

    @Test
    public void undo_threeAddressBookPointerStatesAtStart_throwsInvalidUndoException() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);
        undoStates(stateAddressBook, 2);
        assertThrows(InvalidUndoException.class, stateAddressBook::undo);
    }

    @Test
    public void undo_maxCapacityAddressBookStatesPointerAtStart_throwsInvalidUndoException() {
        StateAddressBook stateAddressBook =
                setAddressBookMaxCapacity(threeStates);
        stateAddressBook.resetData(carlAddressBook);
        stateAddressBook.saveState();
        undoStates(stateAddressBook, StateAddressBook.UNDO_REDO_CAPACITY);
        assertThrows(InvalidUndoException.class, stateAddressBook::undo);
    }

    @Test
    public void redo_oneAddressBookState_throwsInvalidRedoException() {
        StateAddressBook stateAddressBook = setAddressBook(oneState);
        assertThrows(InvalidRedoException.class, stateAddressBook::redo);
    }

    @Test
    public void redo_threeAddressBookStatesPointerAtEnd_throwsInvalidRedoException() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);
        assertThrows(InvalidRedoException.class, stateAddressBook::redo);
    }

    @Test
    public void redo_maxCapacityAddressBookStatesPointerAtEnd_throwsInvalidUndoException() {
        StateAddressBook stateAddressBook =
                setAddressBookMaxCapacity(threeStates);
        stateAddressBook.resetData(carlAddressBook);
        stateAddressBook.saveState();
        assertThrows(InvalidRedoException.class, stateAddressBook::redo);
    }

    @Test
    public void saveState_oneAddressBook_success() {
        StateAddressBook stateAddressBook = setAddressBook(oneState);

        assertEquals(new AddressBook(stateAddressBook), emptyAddressBook);
        assertFalse(stateAddressBook.isUndoable());
        assertFalse(stateAddressBook.isRedoable());
    }

    @Test
    public void saveState_threeAddressBook_success() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);

        assertEquals(new AddressBook(stateAddressBook), bensonAddressBook);

        undoToFirst(stateAddressBook);

        for (int i = 0; i < threeStates.size(); i++) {
            assertEquals(threeStates.get(i), new AddressBook(stateAddressBook));
            if (i != threeStates.size() - 1) {
                stateAddressBook.redo();
            }
        }

        assertFalse(stateAddressBook.isRedoable());
    }

    @Test
    public void saveState_threeAddressBookUndoRedoUndo_success() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);

        assertEquals(new AddressBook(stateAddressBook), bensonAddressBook);

        undoStates(stateAddressBook, 1);

        for (int i = 1; i < threeStates.size(); i++) {
            assertEquals(threeStates.get(i), new AddressBook(stateAddressBook));
            if (i != threeStates.size() - 1) {
                stateAddressBook.redo();
            }
        }

        assertFalse(stateAddressBook.isRedoable());
    }

    @Test
    public void saveState_threeAddressBookUndoUndoSaveState_success() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);

        assertEquals(new AddressBook(stateAddressBook), bensonAddressBook);

        undoStates(stateAddressBook, 2);

        stateAddressBook.resetData(carlAddressBook);
        stateAddressBook.saveState();

        assertEquals(new AddressBook(stateAddressBook), carlAddressBook);
        assertFalse(stateAddressBook.isRedoable());

        undoStates(stateAddressBook, 1);

        assertEquals(new AddressBook(stateAddressBook), threeStates.get(0));
        assertFalse(stateAddressBook.isUndoable());
    }

    @Test
    public void equals() {
        StateAddressBook stateAddressBook = setAddressBook(threeStates);

        // same instance -> returns true.
        assertEquals(stateAddressBook, stateAddressBook);

        // null -> returns false.
        assertFalse(stateAddressBook.equals(null));

        // different class -> returns false.
        assertFalse(stateAddressBook.equals(1));

        // different class -> returns false.
        assertFalse(stateAddressBook.equals(new ArrayList<Integer>()));

        // same states and pointer -> returns true.
        StateAddressBook sameStatesSamePointer = setAddressBook(threeStates);
        assertEquals(stateAddressBook, sameStatesSamePointer);

        // same states and different pointer index -> returns false.
        StateAddressBook sameStatesDifferentPointer = setAddressBook(threeStates);
        undoStates(sameStatesDifferentPointer, 1);
        assertNotEquals(stateAddressBook, sameStatesDifferentPointer);

        // different states and same pointer index -> returns false.
        StateAddressBook differentStatesSamePointer = setAddressBook(threeStates);
        differentStatesSamePointer.resetData(carlAddressBook);
        differentStatesSamePointer.saveState();
        undoStates(differentStatesSamePointer, 1);
        assertNotEquals(stateAddressBook, differentStatesSamePointer);

        // different states and different pointer index -> returns false.
        StateAddressBook differentStates = setAddressBook(oneState);
        assertNotEquals(stateAddressBook, differentStates);
    }

    /**
     * Returns a {@code StateAddressBook} with {@code addressBookStates} added to it.
     *
     * @param addressBookStates address book states to be added.
     * @return {@code StateAddressBook} with {@code addressBookStates} added to it.
     */
    private StateAddressBook setAddressBook(List<ReadOnlyAddressBook> addressBookStates) {
        assertNotEquals(0, addressBookStates.size());

        StateAddressBook stateAddressBook = new StateAddressBook(addressBookStates.get(0));
        for (int stateIndex = 1; stateIndex < addressBookStates.size(); stateIndex++) {
            stateAddressBook.resetData(addressBookStates.get(stateIndex));
            stateAddressBook.saveState();
        }
        return stateAddressBook;
    }

    /**
     * Returns a {@code StateAddressBook} with {@code addressBookStates}
     * added to it until the UNDO_REDO_CAPACITY is reached.
     *
     * @param addressBookStates address book states to be added.
     * @return {@code StateAddressBook} with max capacity.
     */
    private StateAddressBook setAddressBookMaxCapacity(List<ReadOnlyAddressBook> addressBookStates) {
        assertNotEquals(0, addressBookStates.size());

        StateAddressBook stateAddressBook = new StateAddressBook(addressBookStates.get(0));
        int stateIndex = 0;

        for (int i = 0; i < StateAddressBook.UNDO_REDO_CAPACITY; i++) {
            stateAddressBook.resetData(addressBookStates.get(stateIndex));
            stateAddressBook.saveState();
            stateIndex = (stateIndex + 1) % addressBookStates.size();
        }
        return stateAddressBook;
    }

    /**
     * Restores previous states of the address book by invoking undo repeatedly, specified by undoAmount.
     *
     * @param stateAddressBook state address book.
     * @param undoAmount number of times to undo.
     */
    private void undoStates(StateAddressBook stateAddressBook, int undoAmount) {
        for (; undoAmount > 0; undoAmount--) {
            stateAddressBook.undo();
        }
    }

    /**
     * Restores the address book to its first state,
     * i.e., the state at index 0 of {@code stateAddressBook}.
     *
     * @param stateAddressBook state address book.
     */
    private void undoToFirst(StateAddressBook stateAddressBook) {
        while (stateAddressBook.isUndoable()) {
            stateAddressBook.undo();
        }
    }
}
