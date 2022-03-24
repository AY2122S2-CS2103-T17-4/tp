package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FindPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.predicate.PersonPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals_sameFindCommand() {
        String withAll = " n/Alex" + " p/90100102" + " e/tester@mail.com" + " a/QueensTown blk 200 singapore 123123"
                + " t/friends colleagues" + " m/Close contract today";
        FindPersonDescriptor withAllDescriptor = new FindPersonDescriptor(withAll);
        FindCommand withAllFindCommand = new FindCommand(new PersonPredicate(withAllDescriptor));

        String withName = " n/Alex bob";
        FindPersonDescriptor withNameDescriptor = new FindPersonDescriptor(withName);
        FindCommand withNameFindCommand = new FindCommand(new PersonPredicate(withNameDescriptor));

        // same object -> returns true
        assertEquals(withAllFindCommand, withAllFindCommand);
        assertEquals(withNameFindCommand, withNameFindCommand);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        String emptyInput = "n/ ";
        FindPersonDescriptor emptyDescriptor = new FindPersonDescriptor(emptyInput);
        FindCommand command = new FindCommand(new PersonPredicate(emptyDescriptor));
        expectedModel.updateFilteredPersonList(new PersonPredicate(emptyDescriptor));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // Since it is an empty predicate, it should return 0 person found
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonPredicate predicate = preparePredicate();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Prepares a PersonPredicate for testing.
     */
    private PersonPredicate preparePredicate() {
        FindPersonDescriptor descriptor = new FindPersonDescriptor(" n/ Kurz Elle Kunz");
        return new PersonPredicate(descriptor);
    }
}
