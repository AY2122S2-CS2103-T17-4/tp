package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class ExactWordMatchPredicateTest {

    @Test
    void test_exactMatchBetweenWords_returnsTrue() {
        ExactWordMatchPredicate tagPredicate = new ExactWordMatchPredicate(PREFIX_TAG, "colleagues");
        ExactWordMatchPredicate memoPredicate = new ExactWordMatchPredicate(PREFIX_MEMO, "hello");

        // case-insensitive search -> returns true
        assertTrue(tagPredicate.test(new PersonBuilder().withTags("Colleagues").build()));
        assertTrue(memoPredicate.test(new PersonBuilder().withMemo("Hello").build()));

        // same word -> returns true
        assertTrue(tagPredicate.test(new PersonBuilder().withTags("colleagues").build()));
        assertTrue(memoPredicate.test(new PersonBuilder().withMemo("hello").build()));
    }

    @Test
    void test_exactWordMatchBetweenTwoSentences_returnsTrue() {
        ExactWordMatchPredicate addressPredicate = new ExactWordMatchPredicate(PREFIX_ADDRESS, "QueensTown street 10");
        ExactWordMatchPredicate memoPredicate = new ExactWordMatchPredicate(PREFIX_MEMO, "hello my memo");

        // case-insensitive search -> returns true
        assertTrue(addressPredicate.test(new PersonBuilder().withAddress("Brisbane Street").build()));
        assertTrue(memoPredicate.test(new PersonBuilder().withMemo("Hello world").build()));

        // same word -> returns true
        assertTrue(addressPredicate.test(new PersonBuilder().withAddress("QueensTown street 10").build()));
        assertTrue(memoPredicate.test(new PersonBuilder().withMemo("hello my memo").build()));
    }

    @Test
    void test_partialWordMatch_returnsFalse() {
        ExactWordMatchPredicate tagPredicate = new ExactWordMatchPredicate(PREFIX_TAG, "fam");
        ExactWordMatchPredicate addressPredicate = new ExactWordMatchPredicate(PREFIX_ADDRESS, "Covington LA");

        // Partial word match -> returns false
        assertFalse(tagPredicate.test(new PersonBuilder().withTags("family").build()));
        assertFalse(tagPredicate.test(new PersonBuilder().withTags("famous").build()));
        assertFalse(addressPredicate.test(new PersonBuilder().withAddress("CovidTon Los Angelos").build()));
        assertFalse(addressPredicate.test(new PersonBuilder().withAddress("Laos").build()));
    }

    @Test
    void test_emptyWhiteSpaceAttribute_returnsFalse() {
        ExactWordMatchPredicate addressPredicate = new ExactWordMatchPredicate(PREFIX_ADDRESS, "Some test value");
        assertFalse(addressPredicate.test(new PersonBuilder().withAddress("address").build()));
    }
}
