package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.predicate.PersonPredicate;

/**
 * Finds and lists all persons in address book whose {@link seedu.address.model.person.PersonAttribute Attribute}
 * contains any of the tokenized argument. Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose contact details matches any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: \n "
            + "KEYWORD" + "n/ [NAME_PARAMETER]...\n"
            + "p/ [PHONE_PARAMETER]" + "...\n"
            + "e/ [EMAIL_PARAMETER]...\n" + "a/ [ADDRESS_PARAMETER]...\n" + "t/ [TAG_PARAMETER]...\n"
            + "m/ [MEMO_PARAMETER]...\n" + "Example: " + COMMAND_WORD + " n/ alice bob charlie" + " e/ alice@gmail.com";
    public static final String NO_PREFIX_MESSAGE = "At least one field to find must be provided.";
    private final PersonPredicate predicate;

    public FindCommand(PersonPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the main logic of the FindCommand.
     * Filters for {@link seedu.address.model.person.Person Persons} that meets the
     * {@link #predicate} condition.
     *
     * @param model {@code Model} which the command should operate on.
     * @return message that should be reflected in the {@link seedu.address.ui.Ui}.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Checks if two {@code FindCommand} is equal.
     *
     * @param other the other {@code FindCommand} object.
     * @return true if equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
