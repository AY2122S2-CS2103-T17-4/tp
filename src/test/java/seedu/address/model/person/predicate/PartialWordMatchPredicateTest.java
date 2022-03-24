package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class PartialWordMatchPredicateTest {
    @Test
    void test_partialWordMatch_returnsTrue() {
        PartialWordMatchPredicate namePredicate = new PartialWordMatchPredicate(PREFIX_NAME, "ai");
        PartialWordMatchPredicate phonePredicate = new PartialWordMatchPredicate(PREFIX_PHONE, "9543");
        PartialWordMatchPredicate emailPredicate = new PartialWordMatchPredicate(PREFIX_EMAIL, "gmail");

        // Partial word match -> returns true
        assertTrue(namePredicate.test(new PersonBuilder().withName("Aidan").build()));
        assertTrue(namePredicate.test(new PersonBuilder().withName("Abigail").build()));
        assertTrue(namePredicate.test(new PersonBuilder().withName("ai").build()));
        assertTrue(phonePredicate.test(new PersonBuilder().withPhone("12349543").build()));
        assertTrue(phonePredicate.test(new PersonBuilder().withPhone("9543211").build()));
        assertTrue(phonePredicate.test(new PersonBuilder().withPhone("11954311").build()));
        assertTrue(emailPredicate.test(new PersonBuilder().withEmail("redherringmail@yahoo").build()));
    }

    @Test
    void test_partialWordMatchBetweenSentences_returnsTrue() {
        PartialWordMatchPredicate namePredicate = new PartialWordMatchPredicate(PREFIX_NAME, "Alex Yeoh");
        assertTrue(namePredicate.test(new PersonBuilder().withName("alex bin tan").build()));
        assertTrue(namePredicate.test(new PersonBuilder().withName("bin tan Yeoh").build()));
    }

    @Test
    void test_invalidCases_returnsFalse() {
        PartialWordMatchPredicate namePredicate = new PartialWordMatchPredicate(PREFIX_NAME, "Alex Yeoh");
        assertFalse(namePredicate.test(new PersonBuilder().withName("Aleen Tan").build()));
        assertFalse(namePredicate.test(new PersonBuilder().withName("Leo").build()));
    }
}
