package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.NO_PREFIX_MESSAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicate.PersonPredicate;

public class FindCommandParserTest {

    private static final String POPULATED_TEST_USER_INPUT_WITH_NAME = " n/Alex bob";
    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_invalidArg_throwsParseException() {
        // Empty input -> should result in parse failure
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Invalid prefix -> should result in parse failure
        assertParseFailure(parser, "find z/ test1 s/test2", NO_PREFIX_MESSAGE);

        // Stand-alone command -> should result in pare failure
        assertParseFailure(parser, "find ", NO_PREFIX_MESSAGE);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindPersonDescriptor descriptor = new FindPersonDescriptor(POPULATED_TEST_USER_INPUT_WITH_NAME);
        FindCommand expectedFindCommand = new FindCommand(new PersonPredicate(descriptor));
        assertParseSuccess(parser, " n/Alex bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alex \n \t bob \t", expectedFindCommand);
    }
}
