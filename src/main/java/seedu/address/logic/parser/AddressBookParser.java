package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CopyEmailsCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Logger LOGGER = Logger.getLogger(AddressBookParser.class.getName());

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        LOGGER.log(Level.INFO, "Executing AddressBookParser#parseCommand(String)");
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            LOGGER.log(Level.INFO, "Invalid command format");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to AddCommandParser");
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to EditCommandParser");
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to DeleteCommandParser");
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to ClearCommand");
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to FindCommandParser");
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to ListCommand");
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to ExitCommand");
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to HelpCommand");
            return new HelpCommand();

        case CopyEmailsCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to CopyEmailsCommand");
            return new CopyEmailsCommand();

        case HistoryCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to HistoryCommand");
            return new HistoryCommand();

        case UndoCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to UndoCommand");
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            LOGGER.log(Level.INFO, "Parsed to RedoCommand");
            return new RedoCommand();

        default:
            LOGGER.log(Level.INFO, "Unknown command");
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
