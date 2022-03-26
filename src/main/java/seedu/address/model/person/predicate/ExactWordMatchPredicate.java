package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * A class that encapsulates the logic of exact word match between a person's attribute
 * and the description for that attribute (supplied by the user).
 */
public class ExactWordMatchPredicate implements Predicate<Person> {
    private final String description;
    private final Prefix prefix;

    /**
     * Constructor for ExactWordMatchPredicate
     * @param prefix use to retrieve the corresponding {@link Person} attribute.
     * @param description description that is tested against.
     */
    public ExactWordMatchPredicate(Prefix prefix, String description) {
        this.description = description;
        this.prefix = prefix;
    }

    /**
     * Conducts a case-insensitive check on the {@link seedu.address.model.person.Person Person}.
     * Checks if the Person's attribute (the attribute that corresponds to {@link #prefix})
     * has any word that matches exactly to any word in the given description.
     *
     * @param person person to be tested.
     * @return true if person contains the word, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        if (PREFIX_TAG.equals(prefix)) {
            return person.getTags().stream()
                    .anyMatch(tag -> StringUtil.containsWordIgnoreCase(description, tag.toString()));
        } else if (PREFIX_MEMO.equals(prefix)) {
            return StringUtil.containsWordIgnoreCaseForTwoSentence(description, person.getMemo().toString());
        } else if (PREFIX_ADDRESS.equals(prefix)) {
            return StringUtil.containsWordIgnoreCaseForTwoSentence(description, person.getAddress().toString());
        } else if (PREFIX_NAME.equals(prefix)) {
            return StringUtil.containsWordIgnoreCaseForTwoSentence(description, person.getName().toString());
        } else if (PREFIX_EMAIL.equals(prefix)) {
            return StringUtil.containsWordIgnoreCase(description, person.getEmail().toString());
        } else { // for phone prefix
            return StringUtil.containsWordIgnoreCase(description, person.getPhone().toString());
        }
    }
}