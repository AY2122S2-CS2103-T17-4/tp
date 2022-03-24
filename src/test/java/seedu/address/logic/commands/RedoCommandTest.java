package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.addDefaultPerson;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.clear;
import static seedu.address.logic.commands.CommandTestUtil.deleteFirstPerson;
import static seedu.address.logic.commands.CommandTestUtil.editFirstPerson;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains tests for {@code RedoCommand}.
 */
public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        editFirstPerson(model);
        deleteFirstPerson(model);
        addDefaultPerson(model);
        clear(model);
        undoToFirst(model);

        editFirstPerson(expectedModel);
        deleteFirstPerson(expectedModel);
        addDefaultPerson(expectedModel);
        clear(expectedModel);
        undoToFirst(expectedModel);
    }

    @Test
    public void execute_fourRedoableStates_success() {
        // four redoable states in model, redos editFirstPerson.
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);

        // three redoable states in model, redos deleteFirstPerson.
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);

        // two redoable states in model, redos addDefaultPerson.
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);

        // one redoable state in model, redos clear.
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_SUCCESS, expectedModel);

        // zero redoable states in model
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_REDO_EMPTY);
    }

    /**
     * Restores the {@code Model}'s {@code StateAddressBook} to its first state,
     * i.e., the state at index 0 of {@code stateAddressBook}.
     *
     * @param model model.
     */
    private void undoToFirst(Model model) {
        while (model.canUndoAddressBook()) {
            model.undoAddressBook();
        }
    }

}
