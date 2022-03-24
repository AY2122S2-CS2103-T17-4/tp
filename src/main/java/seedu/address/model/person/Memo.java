package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's memo in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMemo(String)}
 */
public class Memo {

    /** Maximum characters allowed. */
    public static final int MAXIMUM_CHARACTERS = 1000;

    /** String message that represents message constraints. */
    public static final String MESSAGE_CONSTRAINTS = "Memo can take any values, up to a maximum of "
            + MAXIMUM_CHARACTERS + " characters";

    /** Every character is allowed, up to a maximum of MAXIMUM_CHARACTERS. */
    public static final String VALIDATION_REGEX = ".{0," + MAXIMUM_CHARACTERS + "}";

    /** An empty memo object. */
    public static final Memo EMPTY_MEMO = new Memo("");

    /** String representation of Memo. */
    public final String memo;

    /**
     * Constructs a {@code Memo}.
     *
     * @param memo A valid memo.
     */
    public Memo(String memo) {
        requireNonNull(memo);
        checkArgument(isValidMemo(memo), MESSAGE_CONSTRAINTS);
        this.memo = memo;
    }

    /**
     * Returns true if a given string is a valid memo.
     *
     * @param memo A memo to be checked for validity.
     * @return If valid true; otherwise false.
     */
    public static boolean isValidMemo(String memo) {
        return memo.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if {@code memo} is equal to {@code EMPTY_MEMO}, false otherwise.
     *
     * @return true if {@code memo} is equal to {@code EMPTY_MEMO}; otherwise false.
     */
    public boolean isEmpty() {
        return this.equals(EMPTY_MEMO);
    }

    /**
     * Returns string representation of {@code Memo}.
     *
     * @return String representation of {@code Memo}.
     */
    @Override
    public String toString() {
        return memo;
    }

    /**
     * Checks if two {@code Memo} object is equal.
     *
     * @param other The other {@code Memo} object.
     * @return If equal true; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Memo // instanceof handles nulls
                && memo.equals(((Memo) other).memo)); // state check
    }

    /**
     * Returns hashcode of {@code Memo}.
     *
     * @return Hashcode of {@code Memo}.
     */
    @Override
    public int hashCode() {
        return memo.hashCode();
    }
}
