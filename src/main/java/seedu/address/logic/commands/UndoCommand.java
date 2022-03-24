package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.StateAddressBook;

/**
 * Restores the {@code Model}'s address book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Restores the address book to its previous state. "
            + "(Up to " + StateAddressBook.UNDO_REDO_CAPACITY + " undo)\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Undo success";
    public static final String MESSAGE_UNDO_EMPTY = "There is nothing to undo (Max "
            + StateAddressBook.UNDO_REDO_CAPACITY + " undoable actions)";

    private static final Logger LOGGER = Logger.getLogger(UndoCommand.class.getName());

    /**
     * Executes the undo command and returns the result message.
     * Restores the {@code Model}'s address book to its previous state.
     *
     * @param model {@code Model} which the undo command should operate on.
     * @return feedback message of the undo operation result for display.
     * @throws CommandException If an error occurs during undo command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        LOGGER.log(Level.INFO, "Executing UndoCommand#execute(Model)");
        requireNonNull(model);

        if (!model.canUndoAddressBook()) {
            LOGGER.log(Level.INFO, "Model's stateAddressBook is not undoable");
            throw new CommandException(MESSAGE_UNDO_EMPTY);
        }

        model.undoAddressBook();
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        LOGGER.log(Level.INFO, "UndoCommand#execute(Model) success");
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }

    /**
     * Checks if two {@code UndoCommand} is equal.
     *
     * @param other the other {@code UndoCommand} object.
     * @return true if equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof UndoCommand; // instanceof handles nulls
    }
}
