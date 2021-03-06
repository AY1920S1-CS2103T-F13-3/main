package seedu.moneygowhere.model.spending;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Spending's remark in the MoneyGoWhere list. Guarantees: immutable; is always valid
 */
public class Remark implements Comparable<Remark> {

    public static final String MESSAGE_CONSTRAINTS = "Remark should not be blank";

    public final String value;

    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Remark o) {
        return value.compareTo(o.value);
    }
}
