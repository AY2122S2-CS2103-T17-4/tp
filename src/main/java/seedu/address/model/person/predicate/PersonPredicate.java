package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.FindPersonDescriptor;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Tests if a {@link seedu.address.model.person.Person Person}
 * matches any of the description given.
 */
public class PersonPredicate implements Predicate<Person> {

    /**
     * Descriptor from {@link FindCommandParser}.
     */
    private final FindPersonDescriptor descriptor;

    /**
     * Constructor of Predicate function.
     *
     * @param descriptor description to search a person by.
     */
    public PersonPredicate(FindPersonDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Conducts a case-insensitive check on the {@link seedu.address.model.person.Person Person}.
     * Checks if the Person's attributes (the attribute that corresponds to {@link Prefix})
     * has any word that matches exactly to any word in the given description. Only three attributes
     * are allowed to have exact word checks, and they are address, memo and tags.
     *
     * @param person person to be tested.
     * @return true if person contains the word, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        List<Prefix> prefixes = descriptor.getAllAvailablePrefix();
        for (Prefix prefix : prefixes) {
            if (testPerson(person, prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tester to check if the attribute that corresponds with the prefix matches with the predicate.
     * @param person person to be tested.
     * @param prefix the single prefix that identifies which attribute of the person should the predicate be
     *               testing against.
     * @return true if the person's attribute passes the test, false otherwise.
     */
    private boolean testPerson(Person person, Prefix prefix) {
        if (prefix.equals(PREFIX_NAME) || prefix.equals(PREFIX_PHONE) || prefix.equals(PREFIX_EMAIL)) {
            PartialWordMatchPredicate predicate = new PartialWordMatchPredicate(prefix,
                    descriptor.getDescription(prefix));
            return predicate.test(person);
        } else {
            ExactWordMatchPredicate predicate = new ExactWordMatchPredicate(prefix,
                    descriptor.getDescription(prefix));
            return predicate.test(person);
        }
    }

    /**
     * Equal method to check if two {@link PersonPredicate} are equal.
     *
     * @param other other PersonContainsKeywordsPredicate object.
     * @return true if both {@link PersonPredicate} object are the same, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonPredicate // instanceof handles nulls
                && descriptor.equals(((PersonPredicate) other).descriptor)); // state check
    }
}
