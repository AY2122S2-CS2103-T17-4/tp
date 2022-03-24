package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class FindPersonDescriptorTest {

    @Test
    void getAllAvailablePrefix() {
        List<Prefix> expectedPrefixList = Arrays.asList(PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE);
        FindPersonDescriptor descriptor = new FindPersonDescriptor(" e/test1 n/test2 p/test3");
        assertEquals(expectedPrefixList, descriptor.getAllAvailablePrefix());
    }

    @Test
    void isEmpty() {
        FindPersonDescriptor emptyDescriptor = new FindPersonDescriptor(" ");
        FindPersonDescriptor populatedDescriptor = new FindPersonDescriptor(" n/ test1 ");
        assertTrue(emptyDescriptor.isEmpty());
        assertFalse(populatedDescriptor.isEmpty());
    }

    @Test
    void getDescription() {
        FindPersonDescriptor dummyDescriptor = new FindPersonDescriptor(" n/ test statement ");
        assertEquals("test statement", dummyDescriptor.getDescription(PREFIX_NAME));
    }

    @Test
    void testEquals() {
        FindPersonDescriptor descriptor = new FindPersonDescriptor(" n/ test statement ");
        FindPersonDescriptor duplicatedDescriptor = new FindPersonDescriptor(" n/ test statement ");
        FindPersonDescriptor differentDescriptor = new FindPersonDescriptor(" n/ test statement 1");
        assertEquals(descriptor, duplicatedDescriptor);
        assertNotEquals(descriptor, differentDescriptor);
    }
}
