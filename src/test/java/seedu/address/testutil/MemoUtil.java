package seedu.address.testutil;

import seedu.address.model.person.Memo;

/**
 * A utility class for Memo.
 */
public class MemoUtil {

    /** A short string. */
    public static final String SHORT_LENGTH_STRING = "hello world";

    /** A long string that is equal to the maximum allowed characters of {@code Memo}. */
    public static final String MAXIMUM_LENGTH_STRING = getStringOfLength(Memo.MAXIMUM_CHARACTERS);

    /** A long string that is more than the maximum allowed characters of {@code Memo}. */
    public static final String LONGER_THAN_MAXIMUM_LENGTH_STRING = getStringOfLength(Memo.MAXIMUM_CHARACTERS + 1);

    /**
     * Returns a string for that of a specified length.
     *
     * @param length Length of the string to be created.
     * @return String of a specified length.
     */
    private static String getStringOfLength(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append('a');
        }
        return sb.toString();
    }
}
