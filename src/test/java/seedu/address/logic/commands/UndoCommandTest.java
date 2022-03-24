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
 * Contains tests for {@code UndoCommand}.
 */
public class UndoCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        editFirstPerson(model);
        deleteFirstPerson(model);
        addDefaultPerson(model);
        clear(model);

        editFirstPerson(expectedModel);
        deleteFirstPerson(expectedModel);
        addDefaultPerson(expectedModel);
        clear(expectedModel);
    }

    @Test
    public void execute_fourUndoableStates_success() {
        // four undoable states in model, undos clear.
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);

        // three undoable states in model, undos addDefaultPerson.
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);

        // two undoable states in model, undos deleteFirstPerson.
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);

        // one undoable state in model, undos editFirstPerson.
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_SUCCESS, expectedModel);

        // zero undoable states in model
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_UNDO_EMPTY);
    }

}
