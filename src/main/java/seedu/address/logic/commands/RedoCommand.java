package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.StateAddressBook;

/**
 * Restores the {@code Model}'s address book to its previous undid state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Restores the address book to its previous undid state. "
            + "(Up to " + StateAddressBook.UNDO_REDO_CAPACITY + " redo)\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_REDO_SUCCESS = "Redo success";
    public static final String MESSAGE_REDO_EMPTY = "There is nothing to redo (Max "
            + StateAddressBook.UNDO_REDO_CAPACITY + " redoable actions)";;

    private static final Logger LOGGER = Logger.getLogger(RedoCommand.class.getName());

    /**
     * Executes the redo command and returns the result message.
     * Restores the {@code Model}'s address book to its previous undid state.
     *
     * @param model {@code Model} which the redo command should operate on.
     * @return feedback message of the redo operation result for display.
     * @throws CommandException If an error occurs during redo command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        LOGGER.log(Level.INFO, "Executing RedoCommand#execute(Model)");
        requireNonNull(model);

        if (!model.canRedoAddressBook()) {
            LOGGER.log(Level.INFO, "Model's stateAddressBook is not redoable");
            throw new CommandException(MESSAGE_REDO_EMPTY);
        }

        model.redoAddressBook();
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        LOGGER.log(Level.INFO, "RedoCommand#execute(Model) success");
        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }

    /**
     * Checks if two {@code RedoCommand} is equal.
     *
     * @param other the other {@code RedoCommand} object.
     * @return true if equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof RedoCommand; // instanceof handles nulls
    }
}
