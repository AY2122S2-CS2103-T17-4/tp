package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FindPersonDescriptor;
import seedu.address.testutil.PersonBuilder;

class PersonPredicateTest {

    @Test
    void test_validPerson_returnsTrue() {
        FindPersonDescriptor descriptor = new FindPersonDescriptor(" n/alex t/colleague p/9040");
        PersonPredicate predicate = new PersonPredicate(descriptor);
        assertTrue(predicate.test(new PersonBuilder().withName("a").withPhone("90400204").build()));
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Yeoh").withPhone("123").build()));
    }

    @Test
    void test_invalidCases_returnsFalse() {
        FindPersonDescriptor descriptor = new FindPersonDescriptor(" a/st t/colleague p/9040");
        PersonPredicate predicate = new PersonPredicate(descriptor);
        assertFalse(predicate.test(new PersonBuilder().withAddress("street").withPhone("904").build()));
        assertFalse(predicate.test(new PersonBuilder().withName("a").withPhone("123").withTags("colle").build()));
    }

    @Test
    void testEquals() {
        FindPersonDescriptor descriptor = new FindPersonDescriptor(" n/ test");
        FindPersonDescriptor duplicatedDescriptor = new FindPersonDescriptor(" n/ test");
        PersonPredicate predicate = new PersonPredicate(descriptor);
        assertEquals(predicate, new PersonPredicate(duplicatedDescriptor));
    }
}
